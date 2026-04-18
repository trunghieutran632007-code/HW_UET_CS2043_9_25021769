public class Main {
    public static void main(String[] args) {
        Vehicle motorbike = new MotorBike("29A-123.45", "Honda");
        Vehicle car = new Car("30E-678.90", "Toyota");
        Vehicle eCar = new ElectricCar("51G-111.22", "VinFast");

        // Goi getInfo() de kiem tra output
        System.out.println(motorbike.getInfo());
        System.out.println(car.getInfo());
        System.out.println(eCar.getInfo());
    }
}