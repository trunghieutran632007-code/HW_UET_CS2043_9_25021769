package Bai3_06.src;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Electronics extends Product {
    private double warrantyFees;

    public Electronics(String name, double price, double warrantyFees) {
        super(name, price);
        this.warrantyFees = warrantyFees;
    }
}
