package Bai2_10.src;

public class Main {
    public static void main(String[] args) {
        CentralHub hub = new CentralHub();


        SmartLight l1 = new SmartLight("L01", "Den phong khach", 80);
        
        SmartLight l2 = new SmartLight("L02", "Den ngu");

        l2.setBrightness("ECO");

        l1.connectToHub(hub);
        l2.connectToHub(hub);

        System.out.println("Do sang cua " + l1.getName() + " hien tai la: " + l1.getBrightness());
        System.out.println("Do sang cua " + l2.getName() + " hien tai la: " + l2.getBrightness());
    }
    
}
