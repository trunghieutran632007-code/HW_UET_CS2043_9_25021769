package Bai4_08.src;

public class AirConditioner extends Device implements WifiConnect {
    public AirConditioner(String id, String name) {
        super(id, name);
    }

    @Override
    public void connectWifi() {
        System.out.println(getName() + " connected to wifi");
    }

}
