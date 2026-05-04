import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tai khoan ngan hang thread-safe.
 * Moi thao tac thay doi balance deu duoc dong bo.
 */
public class BankAccount {
    private static final Logger log = LoggerFactory.getLogger(BankAccount.class);

    private final String id;
    private double balance;

    public BankAccount(String id, double initialBalance) {
        if (initialBalance < 0) {
            throw new IllegalArgumentException("So du ban dau khong duoc am: " + initialBalance);
        }
        this.id = id;
        this.balance = initialBalance;
        log.info("Khoi tao tai khoan {} voi so du ban dau {}", id, initialBalance);
    }

    public synchronized void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("So tien gui phai > 0, nhan duoc: " + amount);
        }
        balance += amount;
        log.info("Tai khoan {} duoc gui {} -> so du moi {}", id, amount, balance);
    }

    public synchronized void withdraw(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("So tien rut phai > 0, nhan duoc: " + amount);
        }
        if (amount > balance) {
            throw new IllegalStateException(
                "So du khong du: yeu cau rut " + amount + " nhung chi co " + balance);
        }
        balance -= amount;
        log.info("Tai khoan {} bi rut {} -> so du moi {}", id, amount, balance);
    }

    public synchronized double getBalance() {
        return balance;
    }

    public String getId() {
        return id;
    }
}
