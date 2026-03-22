package Bai4_08.src;

public class SmartSpeaker extends Device implements WifiConnect, ChangeLevel {
    double volume;
    public SmartSpeaker(String id, String name) {
        super(id, name);
        this.volume = volume;
    }

    @Override
    public void connectWifi() {
        System.out.println(getName() + " connected to wifi");
    }

    @Override
    public void change(double level) {
        this.volume = level;
        System.out.println(getName() + " set volume to " + volume);
    }

}
