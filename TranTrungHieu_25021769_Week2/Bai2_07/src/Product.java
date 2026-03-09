package Bai2_07.src;

public class Product {
    String id;
    String name;
    double price;

    public Product(String id, String name, double price){
        this.id = id;
        this.name = name;
        this.price = price;
    }

    //deep copy constructor
    public Product(Product other) {
        this.id = other.id;
        this.name = other.name;
        this.price = other.price;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

    @Override
    public String toString() {
        return "Product{id=" + id + ", name='" + name + "', price=" + price + "$}";
    }

}
