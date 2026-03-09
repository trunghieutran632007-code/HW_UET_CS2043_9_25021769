package Bai2_07.src;

public class Inventory {
    Product[] items;
    //shallow copy
    public Inventory(Product[] initiallItems) {
        this.items = initiallItems;
    }

    //deep copy
    public static Inventory deepCopy(Product[] initiallItems) {
        Product[] copied = new Product[initiallItems.length];
        for (int i = 0; i < initiallItems.length; i++){
            copied[i] = new Product(initiallItems[i]);
        }
        Inventory inv = new Inventory(null);
        inv.items = copied;
        return inv;
    }

    public void displayInfo() {
        for (Product p : this.items) {
            System.out.println(p);
        }
    }

}
