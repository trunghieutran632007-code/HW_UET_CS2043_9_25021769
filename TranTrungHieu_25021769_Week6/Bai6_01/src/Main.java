package Bai6_01.src;

public class Main {
    public static void main(String[] args) {
        // Luong 1
        Thread thread1 = new Thread(() -> {
            AppConfig config1 = AppConfig.getInstance();
            System.out.println("Luong 1 - Ma HashCode cua AppConfig la: " + config1.hashCode());
        });

        // Luong 2
        Thread thread2 = new Thread(() -> {
            AppConfig config2 = AppConfig.getInstance();
            System.out.println("Luong 2 - Ma HashCode cua AppConfig la: " + config2.hashCode());
        });

        thread1.start();
        thread2.start();

    }

}
