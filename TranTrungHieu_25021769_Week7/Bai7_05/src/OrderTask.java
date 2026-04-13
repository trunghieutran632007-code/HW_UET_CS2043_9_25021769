package Bai7_05.src;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public class OrderTask implements Callable<Boolean> {
    private String id;
    private long processMs;
    private List<String> logs;
    private AtomicInteger count;

    public OrderTask(String id, long processMs, List<String> logs, AtomicInteger count) {
        this.id = id;
        this.processMs = processMs;
        this.logs = logs;
        this.count = count;
    }

    @Override
    public Boolean call() throws Exception {
        // Gia lap thoi gian xu ly don hang
        Thread.sleep(processMs);

        boolean isSuccess = processMs <= 1500;
        String logMessage = (isSuccess ? "Done " : "False ") + id;

        synchronized (logs) {
            logs.add(logMessage);
        }

        if (isSuccess) {
            count.incrementAndGet();
        }
        return isSuccess;
    }

}
