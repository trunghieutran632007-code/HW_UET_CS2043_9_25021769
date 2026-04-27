import java.util.Locale;

/**
 * Ngoại lệ phát sinh khi số dư không đủ cho thao tác rút tiền.
 */
public class InsufficientFundsException extends BankException {

  private static final long serialVersionUID = 1L;

  /**
   * Khởi tạo ngoại lệ với số tiền không thể rút.
   *
   * @param amount số tiền không thể rút
   */
  public InsufficientFundsException(double amount) {
    super("So du tai khoan khong du $"
        + String.format(Locale.US, "%.2f", amount)
        + " de thuc hien giao dich");
  }
}
