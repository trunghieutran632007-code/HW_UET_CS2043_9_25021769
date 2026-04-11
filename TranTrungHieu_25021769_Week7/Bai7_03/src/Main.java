package Bai7_03.src;

public class Main {
    public static void main(String[] args) {
        BankAccount account = new BankAccount(0);
        
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.deposit(100);
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) {
                account.withdraw(100);
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println("Final balance: " + account.getBalance());
            
        } catch (InterruptedException e) {
            System.out.println("Luong bi gian doan! ");
        }
    }

}
