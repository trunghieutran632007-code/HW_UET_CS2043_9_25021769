package Bai3_03.src;

public class MathUtils {
    public int sum(int a, int b) {
        return a + b;
    }

}

class AdvancedMath extends MathUtils {
    @Override
    public int sum(int a, int b) {
        return a + b + 10; 
    }

    public double sum(double a, double b) {
        return a + b;
    }

}
