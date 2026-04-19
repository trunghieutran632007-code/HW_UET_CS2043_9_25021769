package Bai8_05.src;

public class ExpressOrder extends Order {
    public ExpressOrder(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    public double getDeliveryFee() {
        return (weight * 3000 + distance * 500) * 1.5;
    }

    @Override
    public String getLabel() {
        return "[HOA TOC]";
    }
}