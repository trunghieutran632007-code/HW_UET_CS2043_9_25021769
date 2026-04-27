/**
 * Ngoại lệ chung trong hệ thống ngân hàng — cha của mọi lỗi nghiệp vụ.
 */
public class BankException extends Exception {

  private static final long serialVersionUID = 1L;

  /**
   * Khởi tạo ngoại lệ với thông điệp.
   *
   * @param message thông điệp mô tả lỗi
   */
  public BankException(String message) {
    super(message);
  }
}
