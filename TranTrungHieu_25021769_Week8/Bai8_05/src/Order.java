package Bai8_05.src;

public abstract class Order {
    protected double weight;
    protected double distance;

    public Order(double weight, double distance) {
        this.weight = weight;
        this.distance = distance;
    }

    // Cac phuong thuc truu tuong ma lop con phai tu trien khai
    public abstract double getDeliveryFee();

    public abstract String getLabel();

}
