package Bai6_03.src;

public class Main {
    public static void main(String[] args) {
        
        String config = "win"; // "win" or "mac"

        UIFactory factory;

        // Chon factory dua tren cau hinh
        if (config.equalsIgnoreCase("win")) {
            factory = new WindowsFactory();
        } else if (config.equalsIgnoreCase("mac")) {
            factory = new MacFactory();
        } else {
            System.out.println("He dieu hanh khong hop le");
            return;
        }

        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        checkbox.render();
    }

}
