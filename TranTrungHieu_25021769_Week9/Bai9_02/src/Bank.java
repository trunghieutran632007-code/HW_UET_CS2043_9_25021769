import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Pattern;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp đại diện cho một ngân hàng — quản lý danh sách khách hàng và
 * thao tác đọc dữ liệu khách hàng từ {@link InputStream}.
 */
public class Bank {

  private static final Logger LOG = LoggerFactory.getLogger(Bank.class);

  /** Mẫu nhận diện CMND: chuỗi gồm 9 chữ số. */
  private static final Pattern ID_PATTERN = Pattern.compile("\\d{9}");

  /** Mẫu tách các trường trên một dòng tài khoản. */
  private static final Pattern WHITESPACE = Pattern.compile("\\s+");

  /** Số trường tối thiểu trên một dòng tài khoản: accNumber, type, balance. */
  private static final int MIN_ACCOUNT_FIELDS = 3;

  private List<Customer> customerList;

  /** Khởi tạo ngân hàng với danh sách khách hàng rỗng. */
  public Bank() {
    this.customerList = new ArrayList<>();
  }

  /**
   * Lấy danh sách khách hàng.
   *
   * @return danh sách khách hàng (không null)
   */
  public List<Customer> getCustomerList() {
    return customerList;
  }

  /**
   * Cập nhật danh sách khách hàng. Tham số null sẽ thay bằng list rỗng.
   *
   * @param customerList danh sách khách hàng mới
   */
  public void setCustomerList(List<Customer> customerList) {
    if (customerList == null) {
      this.customerList = new ArrayList<>();
    } else {
      this.customerList = customerList;
    }
  }

  /**
   * Đọc danh sách khách hàng và tài khoản từ {@link InputStream}.
   *
   * <p>Định dạng dữ liệu:
   * <ul>
   *   <li>Dòng "tên đầy đủ" + " " + "CMND 9 chữ số": tạo khách hàng mới.</li>
   *   <li>Dòng "accountNumber TYPE balance" với TYPE là CHECKING hoặc SAVINGS:
   *       thêm tài khoản cho khách hàng hiện tại.</li>
   * </ul>
   *
   * @param inputStream nguồn dữ liệu (có thể null - sẽ bỏ qua)
   */
  public void readCustomerList(InputStream inputStream) {
    if (inputStream == null) {
      LOG.warn("readCustomerList called with null InputStream, skipping");
      return;
    }
    LOG.info("Start reading customer list");
    int customers = 0;
    int accounts = 0;
    try (BufferedReader reader =
        new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
      String line;
      Customer current = null;
      while ((line = reader.readLine()) != null) {
        String trimmed = line.trim();
        if (trimmed.isEmpty()) {
          continue;
        }
        if (isCustomerLine(trimmed)) {
          current = parseCustomer(trimmed);
          customerList.add(current);
          customers++;
          LOG.debug("Parsed customer: id={}, name={}", current.getIdNumber(),
              current.getFullName());
        } else if (current != null) {
          if (parseAccountInto(current, trimmed)) {
            accounts++;
          }
        } else {
          LOG.warn("Account-like line found before any customer: {}", trimmed);
        }
      }
    } catch (IOException e) {
      LOG.error("Failed to read customer list", e);
    }
    LOG.info("Finished reading: {} customers, {} accounts", customers, accounts);
  }

  /**
   * Kiểm tra một dòng có phải là dòng khách hàng (kết thúc bằng CMND 9 số) không.
   */
  private boolean isCustomerLine(String line) {
    int lastSpace = line.lastIndexOf(' ');
    if (lastSpace <= 0) {
      return false;
    }
    String tail = line.substring(lastSpace + 1).trim();
    return ID_PATTERN.matcher(tail).matches();
  }

  /**
   * Tách một dòng khách hàng thành đối tượng {@link Customer}.
   * Giả định {@link #isCustomerLine(String)} đã trả về true.
   */
  private Customer parseCustomer(String line) {
    int lastSpace = line.lastIndexOf(' ');
    String name = line.substring(0, lastSpace).trim();
    long id = Long.parseLong(line.substring(lastSpace + 1).trim());
    return new Customer(id, name);
  }

  /**
   * Tách dòng tài khoản và thêm vào khách hàng hiện tại. Trả về true nếu thành công.
   */
  private boolean parseAccountInto(Customer customer, String line) {
    String[] parts = WHITESPACE.split(line);
    if (parts.length < MIN_ACCOUNT_FIELDS) {
      LOG.warn("Skipping malformed account line: {}", line);
      return false;
    }
    try {
      long accountNumber = Long.parseLong(parts[0]);
      String type = parts[1];
      double balance = Double.parseDouble(parts[2]);
      Account account = createAccount(type, accountNumber, balance);
      if (account == null) {
        LOG.warn("Unknown account type '{}' on line: {}", type, line);
        return false;
      }
      customer.addAccount(account);
      return true;
    } catch (NumberFormatException e) {
      LOG.warn("Cannot parse numbers on line '{}': {}", line, e.getMessage());
      return false;
    }
  }

  /**
   * Factory tạo tài khoản đúng kiểu theo chuỗi {@code type}.
   *
   * @return null nếu type không nhận diện được
   */
  private Account createAccount(String type, long number, double balance) {
    if (Account.CHECKING_TYPE.equals(type)) {
      return new CheckingAccount(number, balance);
    }
    if (Account.SAVINGS_TYPE.equals(type)) {
      return new SavingsAccount(number, balance);
    }
    return null;
  }

  /**
   * Trả về thông tin các khách hàng, sắp xếp theo CMND tăng dần.
   *
   * @return chuỗi nhiều dòng, mỗi dòng một khách hàng
   */
  public String getCustomersInfoByIdOrder() {
    return getCustomersInfoSorted(Comparator.comparingLong(Customer::getIdNumber));
  }

  /**
   * Trả về thông tin các khách hàng, sắp xếp theo họ tên (tie-break bằng CMND).
   *
   * @return chuỗi nhiều dòng, mỗi dòng một khách hàng
   */
  public String getCustomersInfoByNameOrder() {
    Comparator<Customer> byName = Comparator
        .comparing(Customer::getFullName)
        .thenComparingLong(Customer::getIdNumber);
    return getCustomersInfoSorted(byName);
  }

  /**
   * Helper dùng chung: sắp xếp một bản sao và build chuỗi kết quả.
   */
  private String getCustomersInfoSorted(Comparator<Customer> comparator) {
    List<Customer> copy = new ArrayList<>(customerList);
    copy.sort(comparator);
    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < copy.size(); i++) {
      sb.append(copy.get(i).getCustomerInfo());
      if (i < copy.size() - 1) {
        sb.append('\n');
      }
    }
    return sb.toString();
  }
}
