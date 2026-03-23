package Bai4_09.src;
public abstract class Product {
    private String id;
    private String name;

    public Product(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public abstract String checkInfo();

    @Override
    public String toString() {
        return name + " id: " + id;
    }

}
