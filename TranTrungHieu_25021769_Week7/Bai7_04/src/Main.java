package Bai7_04.src;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        BookStore store = new BookStore();

        // Khởi tạo sách ban đầu
        store.addBook("Lap Trinh Java", 10);
        store.addBook("Cau Truc Du Lieu", 5);

        // 3 luồng ĐỌC
        Thread r1 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                store.getStock("Lap Trinh Java");
                sleep(100);
            }
        }, "Reader-1");

        Thread r2 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                store.getStock("Cau Truc Du Lieu");
                sleep(100);
            }
        }, "Reader-2");

        Thread r3 = new Thread(() -> {
            for (int i = 0; i < 3; i++) {
                store.getStock("Lap Trinh Java");
                sleep(150);
            }
        }, "Reader-3");

        // 2 luồng GHI
        Thread w1 = new Thread(() -> {
            store.borrow("Lap Trinh Java", 3);
            sleep(200);
            store.borrow("Lap Trinh Java", 2);
        }, "Writer-Muon");

        Thread w2 = new Thread(() -> {
            store.addBook("Cau Truc Du Lieu", 4);
            sleep(200);
            store.borrow("Cau Truc Du Lieu", 6);
        }, "Writer-Nhap");

        // Chạy tất cả đồng thời
        r1.start();
        r2.start();
        r3.start();
        w1.start();
        w2.start();

        r1.join();
        r2.join();
        r3.join();
        w1.join();
        w2.join();

        System.out.println("\n=== Ket qua cuoi ===");
        store.getStock("Lap Trinh Java"); 
        store.getStock("Cau Truc Du Lieu");

    }

    private static void sleep(long ms) {
        try {
            Thread.sleep(ms);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
