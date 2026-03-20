package Bai3_09.src;

public class Invoice implements IPayable {
    String itemName;
    int quantity;
    double pricePerItem;

    public Invoice (String itemName, int quantity, double pricePerItem) {
        this.itemName = itemName;
        this.quantity = quantity;
        this.pricePerItem = pricePerItem;
    }

    @Override
    public double getPaymentAmount() {
        return quantity * pricePerItem;
    }


}
