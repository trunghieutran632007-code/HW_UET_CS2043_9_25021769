public class Triangle extends Shape {

    private static final double HALF = 0.5;

    private final double base;
    private final double height;

    public Triangle(double base, double height) {
        this.base = base;
        this.height = height;
    }

    @Override
    public double getArea() {
        return HALF * base * height;
    }
}