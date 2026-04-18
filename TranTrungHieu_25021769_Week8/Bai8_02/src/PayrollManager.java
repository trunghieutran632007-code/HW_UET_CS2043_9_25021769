public class PayrollManager {

    // 1. Ap dung Replace Magic Number with Constant
    private static final double SOCIAL_INSURANCE_RATE = 0.08;
    private static final double HEALTH_INSURANCE_RATE = 0.015;
    private static final double PERSONAL_DEDUCTION = 11000000;
    
    private static final double TAX_TIER_1_LIMIT = 5000000;
    private static final double TAX_TIER_2_LIMIT = 10000000;
    private static final double TAX_RATE_1 = 0.05;
    private static final double TAX_RATE_2 = 0.10;
    
    private static final double MAX_TAX_TIER_1 = 250000;
    private static final double MAX_TAX_TIER_1_AND_2 = 750000;


    // 2. Tach cac khoi tinh toan thanh phuong thuc rieng
    private double calculateActualSalary(double baseSalary, int workDays, int totalDays) {
        return baseSalary * workDays / totalDays;
    }

    private double calculateInsurance(double actualSalary) {
        // Thay the actualSalary * 0.08 va 0.015 bang bien trung gian co ten tu giai thich
        double socialInsurance = actualSalary * SOCIAL_INSURANCE_RATE;
        double healthInsurance = actualSalary * HEALTH_INSURANCE_RATE;
        return socialInsurance + healthInsurance;
    }

    private double calculateTax(double actualSalary, double insurance, double taxRate) {
        double taxableIncome = actualSalary - insurance - PERSONAL_DEDUCTION;
        double tax = 0;
        
        if (taxableIncome > 0) {
            if (taxableIncome <= TAX_TIER_1_LIMIT) {
                tax = taxableIncome * TAX_RATE_1;
            } else if (taxableIncome <= TAX_TIER_2_LIMIT) {
                tax = MAX_TAX_TIER_1 + (taxableIncome - TAX_TIER_1_LIMIT) * TAX_RATE_2;
            } else {
                tax = MAX_TAX_TIER_1_AND_2 + (taxableIncome - TAX_TIER_2_LIMIT) * taxRate;
            }
        }
        return tax;
    }

    // 3. Ham printPayroll chi goi phuong thuc con va in ket qua
    public void printPayroll(String name, double baseSalary, int workDays, int totalDays, double taxRate, double bonus) {
        System.out.println("=== BANG LUONG (SAU REFACTOR) ===");
        System.out.println("Nhan vien: " + name);

        double actualSalary = calculateActualSalary(baseSalary, workDays, totalDays);
        double insurance = calculateInsurance(actualSalary);
        double tax = calculateTax(actualSalary, insurance, taxRate);
        
        double netSalary = actualSalary - insurance - tax + bonus;

        System.out.println("Luong co ban: " + baseSalary);
        System.out.println("Ngay cong: " + workDays + "/" + totalDays);
        System.out.println("Luong thuc te: " + actualSalary);
        System.out.println("Bao hiem: " + insurance);
        System.out.println("Thue TNCN: " + tax);
        System.out.println("Thuong: " + bonus);
        System.out.println("Thuc nhan: " + netSalary);
    }

    
}