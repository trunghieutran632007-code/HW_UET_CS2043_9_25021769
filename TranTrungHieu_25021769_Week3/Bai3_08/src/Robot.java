package Bai3_08.src;

public abstract class Robot {
    private int id;
    private String modelName;
    private int batteryLevel;

    public Robot(int id, String modelName) {
        this.id = id;
        this.modelName = modelName;
    }

    public void chargeBattery() {
        this.batteryLevel = 100;
    }

    public final void showIdentity() {
        System.out.println(id);
        System.out.println(modelName);
    }

    public abstract void performMainTask() {}
}
