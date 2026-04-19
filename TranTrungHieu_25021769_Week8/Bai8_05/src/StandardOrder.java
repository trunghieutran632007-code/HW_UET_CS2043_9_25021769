package Bai8_05.src;

class StandardOrder extends Order {
    public StandardOrder(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    public double getDeliveryFee() {
        return weight * 3000 + distance * 500;
    }

    @Override
    public String getLabel() {
        return "[THUONG]";
    }
}