package Bai8_04.src;

class OldVehicle {
    static final int CAR = 0;
    static final int BIKE = 1;
    static final int TRUCK = 2;

    private final String plate;
    private final int type;

    public OldVehicle(String plate, int type) {
        this.plate = plate;
        this.type = type;
    }

    public String getPlate() {
        return plate;
    }

    public int getType() {
        return type;
    }
}