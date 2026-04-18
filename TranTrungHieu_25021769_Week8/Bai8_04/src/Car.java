package Bai8_04.src;

public class Car extends Vehicle {
    public Car(String plate) { super(plate); }

    @Override
    public double getCharge(int hours) {
        double fee = 10;
        if (hours > 2) {
            fee += (hours - 2) * 3;
        }
        return fee;
    }
}