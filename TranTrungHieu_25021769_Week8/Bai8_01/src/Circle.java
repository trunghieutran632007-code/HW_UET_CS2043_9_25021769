public class Circle extends Shape {

    private final double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double getArea() {
        // Math.PI thay thế magic number 3.1415
        return Math.PI * radius * radius;
    }
}