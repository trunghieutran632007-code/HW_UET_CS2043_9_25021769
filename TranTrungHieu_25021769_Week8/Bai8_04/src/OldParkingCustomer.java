package Bai8_04.src;

import java.util.ArrayList;
import java.util.List;

class OldParkingCustomer {
    private final String name;
    private final List<OldParkingTicket> tickets = new ArrayList<>();

    public OldParkingCustomer(String name) {
        this.name = name;
    }
    
    public void addTicket(OldParkingTicket ticket) {
        tickets.add(ticket);
    }

    public String receipt() {
        double totalFee = 0;
        int bonusPoints = 0;
        String result = "Parking Receipt for " + name + "\n";

        for (OldParkingTicket each : tickets) {
            double thisFee = 0;

            // calculate fee per ticket
            switch (each.getVehicle().getType()) {
                case OldVehicle.CAR:
                    thisFee += 10;
                    if (each.getHours() > 2) {
                        thisFee += (each.getHours() - 2) * 3;
                    }
                    break;
                case OldVehicle.BIKE:
                    thisFee += 5;
                    if (each.getHours() > 3) {
                        thisFee += (each.getHours() - 3) * 2;
                    }
                    break;
                case OldVehicle.TRUCK:
                    thisFee += 15 + each.getHours() * 4;
                    break;
            }
            totalFee += thisFee;

            // bonus points
            bonusPoints++;
            if (each.getVehicle().getType() == OldVehicle.TRUCK && each.getHours() > 5) {
                bonusPoints++;
            }

            result += "\t" + each.getVehicle().getPlate() + "\t" + thisFee + "\n";
        }

        result += "Total fee is " + totalFee + "\n";
        result += "You earned " + bonusPoints + " bonus points";
        return result;
    }
}
