import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Lớp trừu tượng đại diện cho một tài khoản ngân hàng.
 *
 * <p>Cung cấp các thao tác chung: lưu trữ số dư, danh sách giao dịch và
 * các phương thức bảo vệ ({@code doDepositing}, {@code doWithdrawing})
 * để các lớp con triển khai chính sách nạp/rút riêng.
 */
public abstract class Account {

  /** Logger dùng chung cho mọi lớp con của {@link Account}. */
  private static final Logger LOG = LoggerFactory.getLogger(Account.class);

  /** Hằng số định danh tài khoản vãng lai. */
  public static final String CHECKING_TYPE = "CHECKING";

  /** Hằng số định danh tài khoản tiết kiệm. */
  public static final String SAVINGS_TYPE = "SAVINGS";

  private long accountNumber;
  private double balance;

  /** Danh sách giao dịch của tài khoản. Lớp con được phép truy cập trực tiếp. */
  protected List<Transaction> transactionList;

  /**
   * Khởi tạo một tài khoản mới với số tài khoản và số dư ban đầu.
   *
   * @param accountNumber số tài khoản
   * @param balance số dư ban đầu
   */
  public Account(long accountNumber, double balance) {
    this.accountNumber = accountNumber;
    this.balance = balance;
    this.transactionList = new ArrayList<>();
  }

  /**
   * Lấy số tài khoản.
   *
   * @return số tài khoản
   */
  public long getAccountNumber() {
    return accountNumber;
  }

  /**
   * Cập nhật số tài khoản.
   *
   * @param accountNumber số tài khoản mới
   */
  public void setAccountNumber(long accountNumber) {
    this.accountNumber = accountNumber;
  }

  /**
   * Lấy số dư hiện tại của tài khoản.
   *
   * @return số dư
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Cập nhật số dư.
   *
   * @param balance số dư mới
   */
  protected void setBalance(double balance) {
    this.balance = balance;
  }

  /**
   * Lấy danh sách giao dịch.
   *
   * @return danh sách giao dịch (không null)
   */
  public List<Transaction> getTransactionList() {
    return transactionList;
  }

  /**
   * Cập nhật danh sách giao dịch. Nếu tham số là {@code null}, sẽ thay bằng list rỗng.
   *
   * @param transactionList danh sách giao dịch mới
   */
  public void setTransactionList(List<Transaction> transactionList) {
    if (transactionList == null) {
      this.transactionList = new ArrayList<>();
    } else {
      this.transactionList = transactionList;
    }
  }

  /**
   * Nạp tiền vào tài khoản. Lớp con quyết định chính sách cụ thể.
   *
   * @param amount số tiền cần nạp
   */
  public abstract void deposit(double amount);

  /**
   * Rút tiền khỏi tài khoản. Lớp con quyết định chính sách cụ thể.
   *
   * @param amount số tiền cần rút
   */
  public abstract void withdraw(double amount);

  /**
   * Thực hiện thao tác nạp tiền chung (kiểm tra + cộng số dư).
   *
   * @param amount số tiền cần nạp (phải > 0)
   * @throws InvalidFundingAmountException nếu amount không hợp lệ
   */
  protected void doDepositing(double amount) throws InvalidFundingAmountException {
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    balance += amount;
  }

  /**
   * Thực hiện thao tác rút tiền chung (kiểm tra + trừ số dư).
   *
   * @param amount số tiền cần rút
   * @throws InvalidFundingAmountException nếu amount không hợp lệ
   * @throws InsufficientFundsException nếu số dư không đủ
   */
  protected void doWithdrawing(double amount)
      throws InvalidFundingAmountException, InsufficientFundsException {
    if (amount <= 0) {
      throw new InvalidFundingAmountException(amount);
    }
    if (amount > balance) {
      throw new InsufficientFundsException(amount);
    }
    balance -= amount;
  }

  /**
   * Thêm một giao dịch vào lịch sử (bỏ qua nếu null).
   *
   * @param transaction giao dịch cần thêm
   */
  public void addTransaction(Transaction transaction) {
    if (transaction != null) {
      transactionList.add(transaction);
    }
  }

  /**
   * Trả về chuỗi mô tả toàn bộ lịch sử giao dịch của tài khoản.
   *
   * @return chuỗi đa dòng, mỗi dòng một giao dịch
   */
  public String getTransactionHistory() {
    LOG.debug("Building transaction history for account {}", accountNumber);
    StringBuilder sb = new StringBuilder();
    sb.append("Lich su giao dich cua tai khoan ").append(accountNumber).append(":\n");
    for (int i = 0; i < transactionList.size(); i++) {
      sb.append(transactionList.get(i).getTransactionSummary());
      if (i < transactionList.size() - 1) {
        sb.append('\n');
      }
    }
    return sb.toString();
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!(obj instanceof Account)) {
      return false;
    }
    Account other = (Account) obj;
    return this.accountNumber == other.accountNumber;
  }

  @Override
  public int hashCode() {
    return (int) (accountNumber ^ (accountNumber >>> 32));
  }
}
