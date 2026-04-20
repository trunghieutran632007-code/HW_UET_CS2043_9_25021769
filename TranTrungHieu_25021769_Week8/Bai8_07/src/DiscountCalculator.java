package Bai8_07.src;

public class DiscountCalculator {

    public static double calculateDiscount(double price, String memberType) {
        if (price < 0) {
            throw new IllegalArgumentException("Price must not be negative: " + price);
        }

        return switch (memberType) {
            case "GUEST"  -> 0;
            case "MEMBER" -> price < 100 ? price * 0.05 : price * 0.10;
            case "VIP"    -> price < 100 ? price * 0.15 : price * 0.20;
            default -> throw new IllegalArgumentException("Unknown member type: " + memberType);
        };
    }
}
