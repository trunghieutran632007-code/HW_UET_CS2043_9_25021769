import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp main chạy demo hệ thống ngân hàng.
 *
 * <p>Kịch bản:
 * <ol>
 *   <li>Đọc một số khách hàng và tài khoản từ chuỗi mẫu.</li>
 *   <li>Thực hiện các giao dịch hợp lệ và không hợp lệ để minh họa logging.</li>
 *   <li>In thông tin khách hàng theo CMND và theo họ tên.</li>
 *   <li>In lịch sử giao dịch của một tài khoản.</li>
 * </ol>
 */
public final class Main {

  private static final Logger LOG = LoggerFactory.getLogger(Main.class);

  /** Chỉ số tài khoản đầu tiên trong danh sách của khách hàng. */
  private static final int FIRST_ACCOUNT_INDEX = 0;

  // ===== Hằng số cho các kịch bản demo trong runTransactions() =====
  // Tên hằng số mô tả ngữ nghĩa kịch bản, không phải giá trị → tránh magic number.

  /** Số tiền hợp lệ — nạp vào checking. */
  private static final double DEMO_CHECKING_DEPOSIT = 500.00;

  /** Số tiền hợp lệ — rút khỏi checking. */
  private static final double DEMO_CHECKING_WITHDRAW = 200.00;

  /** Số tiền hợp lệ — nạp vào savings. */
  private static final double DEMO_SAVINGS_DEPOSIT = 1500.00;

  /** Số tiền hợp lệ — rút khỏi savings (≤ MAX_WITHDRAW_AMOUNT). */
  private static final double DEMO_SAVINGS_WITHDRAW = 800.00;

  /** Kịch bản fail: số tiền âm. */
  private static final double DEMO_NEGATIVE_AMOUNT = -100.00;

  /** Kịch bản fail: vượt {@link SavingsAccount#MAX_WITHDRAW_AMOUNT}. */
  private static final double DEMO_EXCEEDS_MAX_WITHDRAW = 2000.00;

  /** Kịch bản fail: rút vượt số dư hiện có. */
  private static final double DEMO_EXCEEDS_BALANCE = 10000.00;

  /** Kịch bản fail: làm số dư sau rút thấp hơn MIN_BALANCE_AFTER_WITHDRAW. */
  private static final double DEMO_VIOLATES_MIN_BALANCE = 600.00;

  private Main() {
    // utility class
  }

  /**
   * Điểm vào chương trình.
   *
   * @param args không sử dụng
   */
  public static void main(String[] args) {
    LOG.info("BankSystem demo starting");

    Bank bank = new Bank();

    String sampleData = ""
            + "Nguyen Van An 123456789\n"
            + "1001 CHECKING 2000.00\n"
            + "1002 SAVINGS 10000.00\n"
            + "Tran Thi Binh 987654321\n"
            + "2001 CHECKING 500.00\n"
            + "Le Van Cuong 555666777\n"
            + "3001 SAVINGS 5500.00\n";

    try (InputStream in =
                 new ByteArrayInputStream(sampleData.getBytes(StandardCharsets.UTF_8))) {
      bank.readCustomerList(in);
    } catch (Exception e) {
      LOG.error("Unexpected error reading sample data", e);
      return;
    }

    runTransactions(bank);

    System.out.println();
    System.out.println("--- Khach hang sap xep theo CMND ---");
    System.out.println(bank.getCustomersInfoByIdOrder());

    System.out.println();
    System.out.println("--- Khach hang sap xep theo ho ten ---");
    System.out.println(bank.getCustomersInfoByNameOrder());

    System.out.println();
    System.out.println("--- Lich su giao dich tai khoan dau tien ---");
    Customer first = bank.getCustomerList().get(0);
    Account firstAccount = first.getAccountList().get(FIRST_ACCOUNT_INDEX);
    System.out.println(firstAccount.getTransactionHistory());

    LOG.info("BankSystem demo finished");
  }

  /**
   * Chạy chuỗi giao dịch demo: nạp/rút hợp lệ và không hợp lệ trên các tài khoản.
   *
   * @param bank đối tượng ngân hàng đã có dữ liệu khách hàng
   */
  private static void runTransactions(Bank bank) {
    if (bank.getCustomerList().isEmpty()) {
      LOG.warn("No customer loaded, skip transactions");
      return;
    }
    Customer customer1 = bank.getCustomerList().get(0);
    Account checking1 = customer1.getAccountList().get(0);
    Account savings1 = customer1.getAccountList().get(1);

    // Hợp lệ
    checking1.deposit(DEMO_CHECKING_DEPOSIT);
    checking1.withdraw(DEMO_CHECKING_WITHDRAW);
    savings1.deposit(DEMO_SAVINGS_DEPOSIT);
    savings1.withdraw(DEMO_SAVINGS_WITHDRAW);

    // Không hợp lệ — số tiền âm
    checking1.deposit(DEMO_NEGATIVE_AMOUNT);

    // Không hợp lệ — vượt giới hạn rút của savings (>1000)
    savings1.withdraw(DEMO_EXCEEDS_MAX_WITHDRAW);

    // Không hợp lệ — số dư không đủ
    Customer customer2 = bank.getCustomerList().get(1);
    Account checking2 = customer2.getAccountList().get(0);
    checking2.withdraw(DEMO_EXCEEDS_BALANCE);

    // Không hợp lệ — số dư sau khi rút sẽ thấp hơn MIN_BALANCE_AFTER_WITHDRAW
    // Tài khoản này có số dư 5500, rút 600 -> 4900 < 5000.
    Customer customer3 = bank.getCustomerList().get(2);
    Account savings3 = customer3.getAccountList().get(0);
    savings3.withdraw(DEMO_VIOLATES_MIN_BALANCE);
  }
}