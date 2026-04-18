public class Main {
    // 4. Tao main() sinh du lieu mau va doi chieu
    public static void main(String[] args) {
        PayrollManager manager = new PayrollManager();

        // Du lieu mau
        String employeeName = "Nguyen Van A";
        double baseSalary = 30000000;
        int workDays = 22;
        int totalDays = 22;
        double taxRate = 0.15;
        double bonus = 2000000;

        manager.printPayroll(employeeName, baseSalary, workDays, totalDays, taxRate, bonus);
    }

}
