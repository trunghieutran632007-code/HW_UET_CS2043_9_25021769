package Bai8_04.src;

public class Main {
    public static void main(String[] args) {
        
        // Test data for Old Code
        OldParkingCustomer oldCustomer = new OldParkingCustomer("Nguyen Van A");
        oldCustomer.addTicket(new OldParkingTicket(new OldVehicle("29A-12345", OldVehicle.CAR), 3));
        oldCustomer.addTicket(new OldParkingTicket(new OldVehicle("29B-67890", OldVehicle.BIKE), 4));
        oldCustomer.addTicket(new OldParkingTicket(new OldVehicle("29C-11111", OldVehicle.TRUCK), 6));

        System.out.println("--- Output before refactor ---");
        System.out.println(oldCustomer.receipt());
        System.out.println("\n");

        // Test data for Refactored Code
        ParkingCustomer newCustomer = new ParkingCustomer("Nguyen Van A");
        newCustomer.addTicket(new ParkingTicket(new Car("29A-12345"), 3));
        newCustomer.addTicket(new ParkingTicket(new Bike("29B-67890"), 4));
        newCustomer.addTicket(new ParkingTicket(new Truck("29C-11111"), 6));

        System.out.println("--- Output after refactor ---");
        System.out.println(newCustomer.receipt());
    }
}
