package Bai4_08.src;

public abstract class Device {
    private String id;
    private String name;
    private boolean status;

    public Device(String id, String name) {
        this.id = id;
        this.name = name;
        this.status = false;
    }

    public void turnOn() {
        this.status = true;
    }

    public void turnOff() {
        this.status = false;
        System.out.println(name + " turned off");
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }


}
