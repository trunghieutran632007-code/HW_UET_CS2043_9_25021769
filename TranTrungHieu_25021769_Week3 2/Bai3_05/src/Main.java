package Bai3_05.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so luong nhan vien: ");
        int n = scanner.nextInt();
        scanner.nextLine();
        Employee[] employees = new Employee[n];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();

            // xu ly chuoi 
            int firstQuote = line.indexOf('"');
            int lastQuote = line.lastIndexOf('"');

            String type = line.substring(0, firstQuote).strip();
            String name = line.substring(firstQuote + 1, lastQuote);
            String[] numbers = line.substring(lastQuote + 1).strip().split("\\s+");

            if (type.equals("F")) {
                double baseSalary = Double.parseDouble(numbers[0]);
                double bonus = Double.parseDouble(numbers[1]);
                double penalty = Double.parseDouble(numbers[2]);
                employees[i] = new FullTimeEmployee(name, baseSalary, bonus, penalty);
            } else if (type.equals("P")) {
                double workingHours = Double.parseDouble(numbers[0]);
                double hourlyRate = Double.parseDouble(numbers[1]);
                employees[i] = new PartTimeEmployee(name, workingHours, hourlyRate);
            }
            
        }
        //In ra man hinh
        for (Employee emp : employees) {
            System.out.println(emp.getName() + " - " + emp.getType() + " - " + emp.calculateSalary());
        }

        scanner.close();
    }

}
