package Bai2_06.src;

public class Main {
    public static void main(String[] args) {
        Account account = new Account("ACC001", 5000000);
        account.addTransaction(new Transaction("001", 1000000, "1/1/2026"));
        account.addTransaction(new Transaction("002", 500000,   "2/1/2026"));

        System.out.println("===Du lieu goc===");
        for (Transaction t : account.getHistory()) {
            t.displayInfo();
        }

        //Hacker
        Transaction[] stolen = account.getHistory();

        // 1) Dat phan tu dau ve null
        stolen[0] = null;
        
        // 2) Sua amount = 9999999
        stolen[1] = new Transaction("002", 9999999, "2/1/2026");
        // Kiểm tra dữ liệu gốc có bị ảnh hưởng không
        System.out.println("\n=== Du lieu goc sau khi hacker tan cong: ===");
        for (Transaction t : account.getHistory()) {
            t.displayInfo();
        }
    }
}
