package Bai3_06.src;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getFinalPrice() {
        return this.price;
    }

    @Override
    public String toString() {
        return this.name + " - " + this.getClass().getSimpleName() + " - " + this.getFinalPrice();
    }
}

class Electronics extends Product {
    private static double tax = 0.1;
    private double warrantyFees;

    public Electronics(String name, double price, double warrantyFees) {
        super(name, price);
        this.warrantyFees = warrantyFees;
    }
    
    @Override
    public double getFinalPrice() {
        double basePrice = super.getFinalPrice();
        return basePrice + (basePrice * tax) + warrantyFees;
    }
}

class Food extends Product {
    private String expDateString;
    

    public Food(String name, double price, String expDateString) {
        super(name, price);
        this.expDateString = expDateString;
    }

    @Override
    public double getFinalPrice() {
        LocalDate today = LocalDate.parse("2025-03-06");
        LocalDate expDate = LocalDate.parse(expDateString);
        long dayLeft  = ChronoUnit.DAYS.between(today, expDate);

        if(dayLeft < 7 && dayLeft >= 0) {
            return super.getFinalPrice() * 0.8;
        } else {
            return super.getFinalPrice();
        }




    }

}
