import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Đại diện cho một giao dịch ngân hàng (nạp/rút trên tài khoản vãng lai/tiết kiệm).
 *
 * <p>Chứa thông tin: kiểu giao dịch, số tiền, số dư trước và sau giao dịch.
 */
public class Transaction {

  private static final Logger LOG = LoggerFactory.getLogger(Transaction.class);

  /** Định dạng số tiền dùng dấu chấm thập phân (en-US). */
  private static final Locale MONEY_LOCALE = Locale.US;

  /** Định dạng giá trị tiền: 2 chữ số sau dấu chấm. */
  private static final String MONEY_FORMAT = "%.2f";

  /** Mã giao dịch: nạp tiền tài khoản vãng lai. */
  public static final int TYPE_DEPOSIT_CHECKING = 1;

  /** Mã giao dịch: rút tiền tài khoản vãng lai. */
  public static final int TYPE_WITHDRAW_CHECKING = 2;

  /** Mã giao dịch: nạp tiền tài khoản tiết kiệm. */
  public static final int TYPE_DEPOSIT_SAVINGS = 3;

  /** Mã giao dịch: rút tiền tài khoản tiết kiệm. */
  public static final int TYPE_WITHDRAW_SAVINGS = 4;

  private int type;
  private double amount;
  private double initialBalance;
  private double finalBalance;

  /**
   * Khởi tạo giao dịch.
   *
   * @param type loại giao dịch (xem các hằng số TYPE_*)
   * @param amount số tiền giao dịch
   * @param initialBalance số dư trước giao dịch
   * @param finalBalance số dư sau giao dịch
   */
  public Transaction(int type, double amount, double initialBalance, double finalBalance) {
    this.type = type;
    this.amount = amount;
    this.initialBalance = initialBalance;
    this.finalBalance = finalBalance;
  }

  /**
   * Lấy loại giao dịch.
   *
   * @return mã loại giao dịch
   */
  public int getType() {
    return type;
  }

  /**
   * Cập nhật loại giao dịch.
   *
   * @param type mã loại giao dịch mới
   */
  public void setType(int type) {
    this.type = type;
  }

  /**
   * Lấy số tiền giao dịch.
   *
   * @return số tiền
   */
  public double getAmount() {
    return amount;
  }

  /**
   * Cập nhật số tiền giao dịch.
   *
   * @param amount số tiền mới
   */
  public void setAmount(double amount) {
    this.amount = amount;
  }

  /**
   * Lấy số dư trước giao dịch.
   *
   * @return số dư ban đầu
   */
  public double getInitialBalance() {
    return initialBalance;
  }

  /**
   * Cập nhật số dư trước giao dịch.
   *
   * @param initialBalance số dư ban đầu mới
   */
  public void setInitialBalance(double initialBalance) {
    this.initialBalance = initialBalance;
  }

  /**
   * Lấy số dư sau giao dịch.
   *
   * @return số dư cuối
   */
  public double getFinalBalance() {
    return finalBalance;
  }

  /**
   * Cập nhật số dư sau giao dịch.
   *
   * @param finalBalance số dư cuối mới
   */
  public void setFinalBalance(double finalBalance) {
    this.finalBalance = finalBalance;
  }

  /**
   * Chuyển mã loại giao dịch thành chuỗi mô tả tiếng Việt (không dấu).
   *
   * @param type mã loại giao dịch
   * @return chuỗi mô tả; "Khong ro" nếu mã không hợp lệ
   */
  public static String getTypeString(int type) {
    switch (type) {
      case TYPE_DEPOSIT_CHECKING:
        return "Nap tien vang lai";
      case TYPE_WITHDRAW_CHECKING:
        return "Rut tien vang lai";
      case TYPE_DEPOSIT_SAVINGS:
        return "Nap tien tiet kiem";
      case TYPE_WITHDRAW_SAVINGS:
        return "Rut tien tiet kiem";
      default:
        return "Khong ro";
    }
  }

  /**
   * Trả về chuỗi tóm tắt giao dịch.
   *
   * @return chuỗi mô tả giao dịch
   */
  public String getTransactionSummary() {
    LOG.trace("Building summary for transaction type {}", type);
    String typeText = getTypeString(type);
    String initialText = String.format(MONEY_LOCALE, MONEY_FORMAT, initialBalance);
    String amountText = String.format(MONEY_LOCALE, MONEY_FORMAT, amount);
    String finalText = String.format(MONEY_LOCALE, MONEY_FORMAT, finalBalance);
    return "- Kieu giao dich: " + typeText
        + ". So du ban dau: $" + initialText
        + ". So tien: $" + amountText
        + ". So du cuoi: $" + finalText + ".";
  }
}
