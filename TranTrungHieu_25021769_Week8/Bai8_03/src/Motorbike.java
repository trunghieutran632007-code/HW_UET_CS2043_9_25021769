class MotorBike extends Vehicle {
    private double fuelLevel; // Chi xe chay xang moi dung

    public MotorBike(String plate, String brand) {
        super(plate, brand);
        this.fuelLevel = 0.0;
    }

    @Override
    protected String getVehicleType() {
        return "Xe may";
    }

    public void refuel(double liters) {
        this.fuelLevel += liters;
    }
}