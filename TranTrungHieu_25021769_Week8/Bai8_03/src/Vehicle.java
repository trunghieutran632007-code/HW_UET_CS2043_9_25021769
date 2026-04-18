abstract class Vehicle {
    protected String plate;
    protected String brand;

    public Vehicle(String plate, String brand) {
        this.plate = plate;
        this.brand = brand;
    }

    // Phuong thuc de cac lop con tu dinh nghia loai xe
    protected abstract String getVehicleType();

    // Gom logic chung len lop cha
    public String getInfo() {
        return getVehicleType() + " [" + plate + "] - " + brand;
    }
}