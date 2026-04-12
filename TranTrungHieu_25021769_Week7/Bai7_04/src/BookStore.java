package Bai7_04.src;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class BookStore {
    private final Map<String, Integer> stock = new HashMap<>();
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    
    public BookStore() {

    }

    // Nhiều luồng có thể đọc CÙNG LÚC
    public int getStock(String title) {
        lock.readLock().lock();

        try {
            int qty = stock.getOrDefault(title, 0);
            System.out.println("[" + Thread.currentThread().getName() + "] DOC \"" + title + "\": " + qty);
            return qty;
        } finally {
            lock.readLock().unlock();
        }
    }

    // Chỉ 1 luồng ghi tại một thời điểm
    public void addBook(String title, int qty) {
        lock.writeLock().lock();
        try {
            stock.merge(title, qty, Integer::sum);
            //Dòng trên tương đương với: stock.put(title, stock.getOrDefault(title, 0) + qty);
            System.out.println("[" + Thread.currentThread().getName() + "] NHAP \"" + title + "\" +" + qty
                    + " -> con lai: " + stock.get(title));
        } finally {
            lock.writeLock().unlock();
        }
    }

    public void borrow(String title, int qty) {
        lock.writeLock().lock();
        try {
            int current = stock.getOrDefault(title, 0);
            if (current < qty) {
                System.out.println("[" + Thread.currentThread().getName() + "] MUON \"" + title
                        + "\" that bai (chi con " + current + ")");
            } else {
                stock.put(title, current - qty);
                System.out.println("[" + Thread.currentThread().getName() + "] MUON \"" + title + "\" -" + qty
                        + " -> con lai: " + stock.get(title));
            }
        } finally {
            lock.writeLock().unlock();
        }
    }

}
