package Bai8_04.src;

public class Bike extends Vehicle {
    public Bike(String plate) { super(plate); }

    @Override
    public double getCharge(int hours) {
        double fee = 5;
        if (hours > 3) {
            fee += (hours - 3) * 2;
        }
        return fee;
    }
}
