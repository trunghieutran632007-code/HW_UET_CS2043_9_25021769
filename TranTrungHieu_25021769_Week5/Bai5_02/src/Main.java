package Bai5_02.src;

public class Main {
    public static void main(String[] args) {

        CustomerQueue queue = new CustomerQueue();
        MessageHistory history = new MessageHistory();

        System.out.println("=== HE THONG HO TRO KHACH HANG ===\n");

        // ---- BUOC 1: Khach A va Khach B vao hang doi ----
        System.out.println("--- Khach hang den ---");
        Customer customerA = new Customer(1, "Khach A");
        Customer customerB = new Customer(2, "Khach B");
        queue.addCustomer(customerA);
        queue.addCustomer(customerB);

        // ---- BUOC 2: Xu ly Khach A (da vao truoc - FIFO) ----
        System.out.println("\n--- Xu ly Khach A ---");
        Customer current = queue.processNext(); // poll() -> lay A ra (vao truoc ra truoc)

        if (current != null) {
            // Nhan vien go 3 dong tin nhan tra loi
            System.out.println("\n  Nhan vien go tin nhan:");
            history.typeMessage("Xin chao " + current.getName() + "!");
            history.typeMessage("Chung toi nhan duoc yeu cau cua ban.");
            history.typeMessage("Sai roi, can sua lai dong nay."); // dong bi sai

            // Xem dong cuoi truoc khi undo
            history.viewLast();

            // Undo dong cuoi bi sai
            System.out.println("\n  -> Undo dong vua go:");
            history.undo();

            // Xem lich su con lai
            System.out.println("\n  Lich su sau khi undo:");
            history.printHistory();
        }

        // ---- BUOC 3: Xu ly Khach B ----
        System.out.println("\n--- Xu ly Khach B ---");
        current = queue.processNext(); // poll() -> lay B ra

        if (current != null) {
            System.out.println("  Bat dau xu ly " + current.getName() + "...");
        }

        // ---- BUOC 4: Thu goi poll() khi hang doi da rong ----
        System.out.println("\n--- Thu xu ly tiep (hang doi rong) ---");
        queue.processNext(); // in ra "Khong con khach doi"
    }
}
