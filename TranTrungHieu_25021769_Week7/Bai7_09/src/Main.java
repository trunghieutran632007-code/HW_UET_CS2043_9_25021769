package Bai7_09.src;

public class Main {
    public static void main(String[] args) {
        Counter counter = new Counter(0);
        Thread[] threads = new Thread[4];

        for (int i = 0; i < 4; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 10000; j++) {
                    //counter.increment();
                     counter.incrementWithTryLock();
                }
            });
            threads[i].start();
        }

        for (int i = 0; i < 4; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Luong bi gian doan!");
                e.printStackTrace();
            }
        }

        System.out.println("Gia tri cuoi cung cua counter la: " + counter.getValue());
    }

}
