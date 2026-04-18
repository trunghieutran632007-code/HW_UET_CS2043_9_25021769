package Bai8_04.src;

public class Truck extends Vehicle {
    public Truck(String plate) { super(plate); }

    @Override
    public double getCharge(int hours) {
        return 15 + hours * 4;
    }

    @Override
    public int getBonusPoints(int hours) {
        int points = super.getBonusPoints(hours); // Base point
        if (hours > 5) {
            points++; // Extra point for truck
        }
        return points;
    }
}
