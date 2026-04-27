import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản tiết kiệm.
 *
 * <p>Áp dụng các quy định nghiệp vụ:
 * <ul>
 *   <li>Số tiền rút mỗi lần không vượt quá {@link #MAX_WITHDRAW_AMOUNT}.</li>
 *   <li>Số dư sau khi rút không được thấp hơn {@link #MIN_BALANCE_AFTER_WITHDRAW}.</li>
 * </ul>
 */
public class SavingsAccount extends Account {

  private static final Logger LOG = LoggerFactory.getLogger(SavingsAccount.class);

  /** Số tiền tối đa cho mỗi lần rút. */
  public static final double MAX_WITHDRAW_AMOUNT = 1000.0;

  /** Số dư tối thiểu phải duy trì sau khi rút. */
  public static final double MIN_BALANCE_AFTER_WITHDRAW = 5000.0;

  /**
   * Khởi tạo tài khoản tiết kiệm.
   *
   * @param accountNumber số tài khoản
   * @param balance số dư ban đầu
   */
  public SavingsAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    LOG.debug("Savings deposit attempt: account={}, amount={}", getAccountNumber(), amount);
    double initialBalance = getBalance();
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      Transaction transaction = new Transaction(
          Transaction.TYPE_DEPOSIT_SAVINGS, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      LOG.info("Savings deposit success: account={}, amount={}, balance={}",
          getAccountNumber(), amount, finalBalance);
    } catch (InvalidFundingAmountException e) {
      LOG.warn("Savings deposit rejected on account {}: {}",
          getAccountNumber(), e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    double initialBalance = getBalance();
    try {
      if (amount > MAX_WITHDRAW_AMOUNT) {
        throw new InvalidFundingAmountException(amount);
      }
      if (initialBalance - amount < MIN_BALANCE_AFTER_WITHDRAW) {
        throw new InsufficientFundsException(amount);
      }
      doWithdrawing(amount);
      double finalBalance = getBalance();
      Transaction transaction = new Transaction(
          Transaction.TYPE_WITHDRAW_SAVINGS, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      LOG.info("Savings withdraw success: account={}, amount={}, balance={}",
          getAccountNumber(), amount, finalBalance);
    } catch (BankException e) {
      LOG.warn("Savings withdraw rejected on account {}: {}",
          getAccountNumber(), e.getMessage());
    }
  }
}
