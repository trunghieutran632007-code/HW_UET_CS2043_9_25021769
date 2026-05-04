import java.util.concurrent.Callable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Tac vu chuyen khoan giua hai tai khoan.
 * Khoa theo thu tu id (thu tu tu nhien) de tranh deadlock.
 */
public class TransferTask implements Callable<Boolean> {
    private static final Logger log = LoggerFactory.getLogger(TransferTask.class);

    private final BankAccount from;
    private final BankAccount to;
    private final double amount;
    private final String txId;

    public TransferTask(String txId, BankAccount from, BankAccount to, double amount) {
        this.txId = txId;
        this.from = from;
        this.to = to;
        this.amount = amount;
    }

    @Override
    public Boolean call() {
        log.info("[{}] Bat dau chuyen {} tu {} sang {}", txId, amount, from.getId(), to.getId());

        // Khoa theo thu tu id de tranh deadlock khi 2 luong chuyen nguoc chieu nhau
        BankAccount first = from.getId().compareTo(to.getId()) < 0 ? from : to;
        BankAccount second = (first == from) ? to : from;

        try {
            synchronized (first) {
                synchronized (second) {
                    from.withdraw(amount);
                    to.deposit(amount);
                }
            }
            log.info("[{}] Hoan tat chuyen {} tu {} sang {}", txId, amount, from.getId(), to.getId());
            return true;
        } catch (IllegalArgumentException | IllegalStateException e) {
            // Log loi nghiep vu o muc ERROR + truyen exception de in stack trace
            log.error("[{}] Loi khi chuyen {} tu {} sang {}: {}",
                      txId, amount, from.getId(), to.getId(), e.getMessage(), e);
            return false;
        }
    }
}
