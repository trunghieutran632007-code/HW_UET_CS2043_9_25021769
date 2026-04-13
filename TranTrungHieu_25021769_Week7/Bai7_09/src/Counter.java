package Bai7_09.src;

import java.util.concurrent.locks.ReentrantLock;

public class Counter {
    private int value;
    private final ReentrantLock lock = new ReentrantLock();

    public Counter(int value) {
        this.value = value;
    }

    public void increment() {
        lock.lock();
        try {
            value++;
        } finally {
            lock.unlock();
        }
    }

    public void incrementWithTryLock() {
        // tryLock() se tra ve true neu lay duoc lock, false neu khong lay duoc
        if (lock.tryLock()) {
            try {
                value++;
            } finally {
                lock.unlock();
            }
        } else {
            // In thong bao neu khong lay duoc lock
            System.out.println("Khong the lay lock, bo qua lan dem nay!");
        }
    }

    public int getValue() {
        return value;
    }

}
