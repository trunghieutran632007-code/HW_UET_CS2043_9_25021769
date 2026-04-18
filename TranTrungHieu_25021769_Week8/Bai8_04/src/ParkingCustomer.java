package Bai8_04.src;

public class ParkingCustomer {
    private final String name;
    private final List<ParkingTicket> tickets = new ArrayList<>();

    public ParkingCustomer(String name) {
        this.name = name;
    }

    public void addTicket(ParkingTicket ticket) {
        tickets.add(ticket);
    }

    public String receipt() {
        String result = "Parking Receipt for " + name + "\n";

        for (ParkingTicket each : tickets) {
            result += "\t" + each.getVehicle().getPlate() + "\t" + each.getCharge() + "\n";
        }

        // Replace Temp with Query
        result += "Total fee is " + getTotalFee() + "\n";
        result += "You earned " + getTotalBonusPoints() + " bonus points";

        return result;
    }

    // Extracted Query Method for Total Fee
    private double getTotalFee() {
        double result = 0;
        for (ParkingTicket each : tickets) {
            result += each.getCharge();
        }
        return result;
    }

    // Extracted Query Method for Total Bonus Points
    private int getTotalBonusPoints() {
        int result = 0;
        for (ParkingTicket each : tickets) {
            result += each.getBonusPoints();
        }
        return result;
    }
}
