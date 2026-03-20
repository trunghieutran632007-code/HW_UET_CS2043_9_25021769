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

    public String getModelName() {
        return modelName;
    }

    public abstract void performMainTask();
}

class DroneRobot extends Robot implements Flyable, GPS {
    public DroneRobot (int id, String modelName) {
        super(id, modelName);
    }
    @Override
    public void performMainTask() {
        System.out.println(getModelName() + " performing main task");
    }

    @Override
    public void fly() {
        System.out.println(getModelName() + " flying");
    }

    @Override
    public void getCoordinates() {
        System.out.println(getModelName() + " getting coordinates");
    }


}

class FishRobot extends Robot implements Swimmable {
    public FishRobot(int id, String modelName) {
        super(id, modelName);
    }

    @Override
    public void performMainTask() {
        System.out.println(getModelName() + " performing main task");
    }

    @Override
    public void swim() {
        System.out.println(getModelName() + " swimming");
    }
}

class AmphibiousRobot extends Robot implements Flyable, GPS, Swimmable {
    public AmphibiousRobot (int id, String modeName) {
        super(id, modeName);
    }

    @Override
    public void performMainTask() {
        System.out.println(getModelName() + " performing main task");
    }

    @Override
    public void fly() {
        System.out.println(getModelName() + " flying");
    }

    @Override
    public void getCoordinates() {
        System.out.println(getModelName() + " getting coordinates");
    }

    @Override
    public void swim() {
        System.out.println(getModelName() + " swimming");
    }
}
