package Bai8_03.src;

class Car extends Vehicle {
    private double fuelLevel; // Chi xe chay xang moi dung

    public Car(String plate, String brand) {
        super(plate, brand);
        this.fuelLevel = 0.0;
    }

    @Override
    protected String getVehicleType() {
        return "O to";
    }

    public void refuel(double liters) {
        this.fuelLevel += liters;
    }
}