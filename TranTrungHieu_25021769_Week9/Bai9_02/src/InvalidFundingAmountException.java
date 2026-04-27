import java.util.Locale;

/**
 * Ngoại lệ phát sinh khi số tiền giao dịch không hợp lệ
 * (âm, bằng 0, hoặc vượt giới hạn cho phép).
 */
public class InvalidFundingAmountException extends BankException {

  private static final long serialVersionUID = 1L;

  /**
   * Khởi tạo ngoại lệ với số tiền không hợp lệ.
   *
   * @param amount số tiền không hợp lệ
   */
  public InvalidFundingAmountException(double amount) {
    super("So tien khong hop le: $"
        + String.format(Locale.US, "%.2f", amount));
  }
}
