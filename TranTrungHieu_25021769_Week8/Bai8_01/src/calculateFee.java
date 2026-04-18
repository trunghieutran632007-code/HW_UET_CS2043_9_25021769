//CODE SMELLS - tại sao là vấn đề
//1. Tên biến `t`, `h`, `r`, `m`, `f` khiến người đọc khó hiểu ý nghĩa biến
//2. Magic number 0,9: người đọc không hiểu ý nghĩa 0,9 là gì
//3. Parameter không được sử dụng: String t --> thừa 
// REFACTOR:
//1: Xoá tên biến
//2: Đặt tên hằng số cho 0,9 -> MEMBER_DISCOUNT_RATE = 0.9;
//3: Xoá tham số thừa

public class calculateFee {
    private static final double MEMBER_DISCOUNT_RATE = 0.9;

    public double calculateFee(int hours, double ratePerHour, boolean isMember) {
        double fee = hours * ratePerHour;
        if (isMember) {
            fee = fee * MEMBER_DISCOUNT_RATE;
        }
        return fee;
    }

}
