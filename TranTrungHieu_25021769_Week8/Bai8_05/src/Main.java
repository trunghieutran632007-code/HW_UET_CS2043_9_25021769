package Bai8_05.src;

public class Main {
    public static void main(String[] args) {
        // Sinh du lieu 4 don hang hon hop
        Order[] orders = {
                new StandardOrder(2.5, 10.0),
                new ExpressOrder(1.0, 5.0),
                new FragileOrder(3.0, 15.0),
                new StandardOrder(5.0, 20.0)
        };

        // In ra nhan va phi giao hang
        System.out.println("=== Danh sach don hang ===");
        for (Order order : orders) {
            System.out.println("Nhan: " + order.getLabel() + " | Phi giao hang: " + order.getDeliveryFee());
        }
    }

}
