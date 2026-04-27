import java.util.ArrayList;
import java.util.List;

/**
 * Đại diện cho một khách hàng của ngân hàng.
 *
 * <p>Mỗi khách hàng có CMND, họ tên và một danh sách tài khoản.
 */
public class Customer {

  private long idNumber;
  private String fullName;
  private List<Account> accountList;

  /**
   * Khởi tạo khách hàng rỗng (id = 0, tên rỗng). Phục vụ test/seed.
   */
  public Customer() {
    this(0L, "");
  }

  /**
   * Khởi tạo khách hàng với CMND và họ tên.
   *
   * @param idNumber số CMND
   * @param fullName họ tên đầy đủ
   */
  public Customer(long idNumber, String fullName) {
    this.idNumber = idNumber;
    this.fullName = fullName;
    this.accountList = new ArrayList<>();
  }

  /**
   * Lấy số CMND.
   *
   * @return số CMND
   */
  public long getIdNumber() {
    return idNumber;
  }

  /**
   * Cập nhật số CMND.
   *
   * @param idNumber số CMND mới
   */
  public void setIdNumber(long idNumber) {
    this.idNumber = idNumber;
  }

  /**
   * Lấy họ tên.
   *
   * @return họ tên
   */
  public String getFullName() {
    return fullName;
  }

  /**
   * Cập nhật họ tên.
   *
   * @param fullName họ tên mới
   */
  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  /**
   * Lấy danh sách tài khoản.
   *
   * @return danh sách tài khoản (không null)
   */
  public List<Account> getAccountList() {
    return accountList;
  }

  /**
   * Cập nhật danh sách tài khoản. Tham số null sẽ thay bằng list rỗng.
   *
   * @param accountList danh sách tài khoản mới
   */
  public void setAccountList(List<Account> accountList) {
    if (accountList == null) {
      this.accountList = new ArrayList<>();
    } else {
      this.accountList = accountList;
    }
  }

  /**
   * Thêm tài khoản (bỏ qua nếu null hoặc đã tồn tại).
   *
   * @param account tài khoản cần thêm
   */
  public void addAccount(Account account) {
    if (account == null) {
      return;
    }
    if (!accountList.contains(account)) {
      accountList.add(account);
    }
  }

  /**
   * Xóa tài khoản (bỏ qua nếu null).
   *
   * @param account tài khoản cần xóa
   */
  public void removeAccount(Account account) {
    if (account == null) {
      return;
    }
    accountList.remove(account);
  }

  /**
   * Trả về thông tin tóm tắt khách hàng dạng text.
   *
   * @return chuỗi mô tả khách hàng
   */
  public String getCustomerInfo() {
    return "So CMND: " + idNumber + ". Ho ten: " + fullName + ".";
  }
}
