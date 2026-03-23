package Bai4_09.src;

public class Electronic extends Product {
    private String warranty;

    public Electronic(String id, String name, String warranty) {
        super(id, name);
        this.warranty = warranty;
    }

    public String getWarranty() {
        return warranty;
    }

    @Override
    public String checkInfo() {
        return getName() + " - " + getWarranty();
    }

}
