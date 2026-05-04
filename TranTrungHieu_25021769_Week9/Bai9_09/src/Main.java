import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mo phong he thong xu ly giao dich da luong, dung SLF4J + Logback.
 * KHONG su dung System.out.println o bat ky dau.
 */
public class Main {
    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) throws Exception {
        log.info("=== Khoi dong he thong xu ly giao dich ===");

        BankAccount alice = new BankAccount("ALICE", 1000.0);
        BankAccount bob = new BankAccount("BOB", 500.0);
        BankAccount charlie = new BankAccount("CHARLIE", 200.0);

        // Thread pool co bounded queue + AbortPolicy (an toan cho production)
        int corePoolSize = 3;
        int queueCapacity = 10;
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            corePoolSize, corePoolSize,
            0L, TimeUnit.MILLISECONDS,
            new ArrayBlockingQueue<>(queueCapacity),
            new ThreadPoolExecutor.AbortPolicy()
        );
        log.info("Tao thread pool: corePoolSize={}, queueCapacity={}", corePoolSize, queueCapacity);

        List<TransferTask> tasks = List.of(
            new TransferTask("TX001", alice, bob, 200.0),
            new TransferTask("TX002", bob, charlie, 100.0),
            new TransferTask("TX003", charlie, alice, 50.0),
            new TransferTask("TX004", alice, bob, 300.0),
            new TransferTask("TX005", alice, charlie, -10.0),  // se loi: amount am
            new TransferTask("TX006", charlie, bob, 9_999.0)   // se loi: vuot so du
        );
        log.info("Nap {} giao dich vao hang doi", tasks.size());

        List<Future<Boolean>> futures = new ArrayList<>();
        for (TransferTask t : tasks) {
            futures.add(executor.submit(t));
        }

        int success = 0, fail = 0;
        for (Future<Boolean> f : futures) {
            if (Boolean.TRUE.equals(f.get())) success++;
            else fail++;
        }

        log.info("Tong ket: {} giao dich thanh cong, {} that bai", success, fail);
        log.info("So du cuoi cung: ALICE={}, BOB={}, CHARLIE={}",
                 alice.getBalance(), bob.getBalance(), charlie.getBalance());

        executor.shutdown();
        if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
            log.error("Thread pool khong tat trong 5s, ep dung");
            executor.shutdownNow();
        }
        log.info("=== Tat he thong ===");
    }
}
