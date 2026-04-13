package Bai7_10.src;

class Worker implements Runnable {
    private volatile boolean running = true;

    public void stop() {
        running = false;
        System.out.println("Da goi stop(). Luong se som dung lai.");
    }

    @Override
    public void run() {
        // Lap while(running) va in "Working..."
        while (running) {
            System.out.println("Working...");
            try {
                // Tam dung 200ms de tranh in qua nhanh
                Thread.sleep(100);
            } catch (InterruptedException e) {
                System.out.println("Luong bi ngat quat.");
            }
        }
        System.out.println("Luong Worker da ket thuc an toan.");
    }
}

public class Main {
    public static void main(String[] args) {
        // Tao Worker va chay bang Thread
        Worker worker = new Worker();
        Thread thread = new Thread(worker);

        System.out.println("Bat dau khoi dong luong...");
        thread.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Goi stop() de dung luong
        worker.stop();

        try {
            thread.join();
            System.out.println("Da join() thanh cong. Chuong trinh chinh ket thuc.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
// Giai thich: Vi sao can volatile?
// Neu khong co tu khoa volatile, luong Worker co the luu (cache) gia tri cua
// bien running trong bo nho rieng cua no. Khi do, du luong main da doi
// running = false, luong Worker van khong thay duoc su thay doi nay va
// tiep tuc vong lap vo han. volatile dam bao moi thay doi tren bien
// deu duoc cap nhat truc tiep vao bo nho chinh (main memory), giup
// cac luong luon doc duoc gia tri moi nhat.