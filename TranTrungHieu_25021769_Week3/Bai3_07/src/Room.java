package Bai3_07.src;

public abstract class Room {
    private int nights;

    public Room (int nights) {
        this.nights = nights;
    }

    public abstract long Pay();

    public int getNights() {
        return nights;
    }
}

class StandardRoom extends Room {
    public StandardRoom (int nights) {
        super(nights);
    }

    @Override
    public long Pay() {
        long toltal = (long) (super.getNights()) * 500000;
        if (super.getNights() > 3) {
            toltal = (long) (toltal * 0.95);
        }
        return toltal;
    }
}

class VipRoom extends Room {
    public VipRoom(int nights) {
        super(nights);
    }

    @Override
    public long Pay() {
        return (long) getNights() * 2000000;
    }
}
