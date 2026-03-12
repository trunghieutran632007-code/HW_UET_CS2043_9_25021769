package Bai3_06.src;

public class Product {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
}

class Electronics extends Product {
    double warrantyFees;

    public Electronics(String name, double price, double warrantyFees) {
        super(name, price);
        this.warrantyFees = warrantyFees;
    }
}
