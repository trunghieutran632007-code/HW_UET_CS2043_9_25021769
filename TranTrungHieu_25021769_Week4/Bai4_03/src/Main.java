package Bai4_03.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so luong nhan vien: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            String info = scanner.nextLine();
            String[] parts = info.strip().split("\\s+");
            if (parts[0].equalsIgnoreCase("O")) {
                String id = parts[1];
                String name = parts[2];
                double baseSalary = Double.parseDouble(parts[3]);
                employees[i] = new OfficeWorker(id, name, baseSalary);
            } else if (parts[0].equalsIgnoreCase("T")) {
                String id = parts[1];
                String name = parts[2];
                double baseSalary = Double.parseDouble(parts[3]);
                double overtimeHours = Double.parseDouble(parts[4]);
                employees[i] = new Technician(id, name, baseSalary, overtimeHours);
            }
        }

        double totalPay = 0;
        for (int i = 0; i < n; i++) {
            System.out.println(employees[i].name + " - Pay: " + employees[i].calculatePay());
            employees[i].work();
            System.out.println();
            totalPay = totalPay + employees[i].calculatePay();
        }
        System.out.println("Total Pay: " + totalPay);
        
    }

}
