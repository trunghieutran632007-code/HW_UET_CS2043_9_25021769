package Bai8_04.src;

class OldParkingTicket {
    private final OldVehicle vehicle;
    private final int hours;

    public OldParkingTicket(OldVehicle vehicle, int hours) {
        this.vehicle = vehicle;
        this.hours = hours;
    }

    public OldVehicle getVehicle() {
        return vehicle;
    }

    public int getHours() {
        return hours;
    }
}