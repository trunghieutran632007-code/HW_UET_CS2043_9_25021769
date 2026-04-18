package Bai8_04.src;

public abstract class Vehicle {
    private final String plate;

    public Vehicle(String plate) {
        this.plate = plate;
    }

    public String getPlate() {
        return plate;
    }

    // Abstract method to let subclasses define their own fee
    public abstract double getCharge(int hours);

    // Default bonus point logic (can be overridden)
    public int getBonusPoints(int hours) {
        return 1;
    }
}
