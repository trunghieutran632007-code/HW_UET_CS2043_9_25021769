package Bai8_05.src;

// Don hang cong kenh (Yeu cau 4)
class BulkyOrder extends Order {
    public BulkyOrder(double weight, double distance) {
        super(weight, distance);
    }

    @Override
    public double getDeliveryFee() {
        return weight * 4000 + distance * 600 + 50000;
    }

    @Override
    public String getLabel() {
        return "[HANG CONG KENH]";
    }
}
