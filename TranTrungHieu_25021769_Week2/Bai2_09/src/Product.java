package Bai2_09.src;

public class Product {
    private String name;
    private double price;
    private int quantity;
    private double discount;
    private static double taxRate = 0.1;
    private static double totalRevenue = 0;

    public Product(String name, double price, int quantity, double discount) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.discount = discount;
    }

    public static void updateTaxRate(double newRate) {
        taxRate = newRate;
    }

    public double calculateFinalPrice() {
        return (price - discount) * (1 + taxRate);
    }

    public void updateDiscount(double newDiscount) {
        this.discount = newDiscount;
    }

    public void sell(int amount) {
        if (amount <= quantity) {
            quantity -= amount;
            double finalPrice = calculateFinalPrice();
            double revenue = amount * finalPrice;
            totalRevenue += revenue;
            System.out.println("Ban thanh cong " + amount + " " + name + ". Thu duoc: " + revenue);
        } else {
            System.err.println("Khong du hang cho san pham: " + name);
        }
    }

    public static double getTotalRevenue() {
        return totalRevenue;
    }
}
