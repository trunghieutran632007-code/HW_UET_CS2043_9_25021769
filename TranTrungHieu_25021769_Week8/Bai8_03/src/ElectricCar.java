class ElectricCar extends Vehicle {
    private int batteryPercent; // Chi xe dien moi dung

    public ElectricCar(String plate, String brand) {
        super(plate, brand);
        this.batteryPercent = 0;
    }

    @Override
    protected String getVehicleType() {
        return "Xe dien";
    }

    public void charge(int percent) {
        this.batteryPercent += percent;
    }
}