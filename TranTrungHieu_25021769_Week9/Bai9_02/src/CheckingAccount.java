import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tài khoản vãng lai (checking).
 *
 * <p>Cho phép nạp/rút tiền tự do, miễn là số tiền hợp lệ và số dư đủ.
 */
public class CheckingAccount extends Account {

  private static final Logger LOG = LoggerFactory.getLogger(CheckingAccount.class);

  /**
   * Khởi tạo tài khoản vãng lai.
   *
   * @param accountNumber số tài khoản
   * @param balance số dư ban đầu
   */
  public CheckingAccount(long accountNumber, double balance) {
    super(accountNumber, balance);
  }

  @Override
  public void deposit(double amount) {
    double initialBalance = getBalance();
    try {
      doDepositing(amount);
      double finalBalance = getBalance();
      Transaction transaction = new Transaction(
          Transaction.TYPE_DEPOSIT_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      LOG.info("Checking deposit success: account={}, amount={}, balance={}",
          getAccountNumber(), amount, finalBalance);
    } catch (InvalidFundingAmountException e) {
      LOG.warn("Checking deposit rejected on account {}: {}", getAccountNumber(), e.getMessage());
    }
  }

  @Override
  public void withdraw(double amount) {
    double initialBalance = getBalance();
    try {
      doWithdrawing(amount);
      double finalBalance = getBalance();
      Transaction transaction = new Transaction(
          Transaction.TYPE_WITHDRAW_CHECKING, amount, initialBalance, finalBalance);
      addTransaction(transaction);
      LOG.info("Checking withdraw success: account={}, amount={}, balance={}",
          getAccountNumber(), amount, finalBalance);
    } catch (BankException e) {
      LOG.warn("Checking withdraw rejected on account {}: {}",
          getAccountNumber(), e.getMessage());
    }
  }
}
