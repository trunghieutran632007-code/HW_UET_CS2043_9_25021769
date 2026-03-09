package Bai2_09.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===Nhap du lieu san pham 1===");
        System.out.print("Ten: ");
        String name1 = scanner.next();
        System.out.print("Gia tien: ");
        double price1 = scanner.nextDouble();
        System.out.print("So luong: ");
        int qty1 = scanner.nextInt();
        System.out.print("Khuyen mai: ");
        double disc1 = scanner.nextDouble();
        Product p1 = new Product(name1, price1, qty1, disc1);

        System.out.println("===Nhap du lieu san pham 2===");
        System.out.print("Ten: ");
        String name2 = scanner.next();
        System.out.print("Gia tien: ");
        double price2 = scanner.nextDouble();
        System.out.print("So luong: ");
        int qty2 = scanner.nextInt();
        System.out.print("Khuyen mai: ");
        double disc2 = scanner.nextDouble();
        Product p2 = new Product(name2, price2, qty2, disc2);

        //Giao dich
        System.out.println("===Giao dich===");
        System.out.print("Nhap so luong muon mua san pham " + name1 + ": ");
        int amount1 = scanner.nextInt();
        p1.sell(amount1);

        System.out.print("Nhap so luong muon mua san pham " + name2 + ": ");
        int amount2 = scanner.nextInt();
        p2.sell(amount2);

        //i
        System.out.println("Gia tien " + name1 + ": " + p1.calculateFinalPrice());
        System.out.println("Gia tien " + name2 + ": " + p2.calculateFinalPrice());

        //ii giam thue con 8%
        Product.updateTaxRate(0.08);
        System.out.println("Gia tien khi con 8% thue " + name1 + ": " + p1.calculateFinalPrice());
        System.out.println("Gia tien khi con 8% thue " + name2 + ": " + p2.calculateFinalPrice());

        //iii giam gia p1
        p1.updateDiscount(10.0);
        System.out.println("Gia tien " + name1 + ": " + p1.calculateFinalPrice());
        System.out.println("Gia tien " + name2 + ": " + p2.calculateFinalPrice());

        System.out.println("Tong doanh thu: " + Product.getTotalRevenue());

        scanner.close();

    }
}
