package Bai6_03.src;

public class Main {
    public static void main(String[] args) {
        // Gia lap tham so cau hinh (trong thuc te co the lay tu args[0] hoac file
        // config)
        String config = "win"; // Thu doi thanh "mac" de kiem tra

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

        // Tao va render cac thanh phan
        Button button = factory.createButton();
        Checkbox checkbox = factory.createCheckbox();

        button.render();
        checkbox.render();
    }

}
