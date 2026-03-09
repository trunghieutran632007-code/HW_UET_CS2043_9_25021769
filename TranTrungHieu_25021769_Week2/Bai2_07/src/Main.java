package Bai2_07.src;

public class Main {
    public static void main(String[] args) {
        Product[] arr = {new Product("001", "Laptop", 1000), new Product("002", "Phone", 900)};

        //shallow copy
        Inventory inv1 = new Inventory(arr);
        tamperPrice(arr); //thay doi gia tri
        System.out.println("===Shallow Copy===");
        inv1.displayInfo();

        //deep copy
        inv1.items[0].setPrice(1000); // chinh lai du lieu goc ve 1000
        
        Inventory inv2 = Inventory.deepCopy(arr);
        tamperPrice(arr); //thay doi gia tri
        System.out.println("===Deep Copy===");        
        inv2.displayInfo();



    }


    
    public static void tamperPrice(Product[] products) {
        products[0].setPrice(5000);
    }
}
