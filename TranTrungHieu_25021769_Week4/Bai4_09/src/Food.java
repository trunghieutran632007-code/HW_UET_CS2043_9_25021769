package Bai4_09.src;

public class Food extends Product {
    private String expDate;
    public Food(String id, String name, String expDate) {
        super(id, name);
        this.expDate = expDate;
    }

    public String getExpDate() {
        return expDate;
    }

    @Override
    public String checkInfo() {
        return getName() + " - " + getExpDate();
    }

}
