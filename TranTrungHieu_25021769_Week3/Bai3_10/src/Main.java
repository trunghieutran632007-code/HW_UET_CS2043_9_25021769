package Bai3_10.src;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Employee> employees = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine().strip();
            String[] parts = line.split("\\s+");
            
            // Neu dong trong thi bo qua
            if (parts.length < 3) continue;

            String type = parts[0];
            String name = parts[1];
            double baseSalary = Double.parseDouble(parts[2]);

            if (type.equals("E")) {
                employees.add(new Employee(name, baseSalary));
            } else if (type.equals("D")) {
                int overtimeHours = Integer.parseInt(parts[3]);
                employees.add(new Developer(name, baseSalary, overtimeHours));
            } else if (type.equals("T")) {
                int bugsFound = Integer.parseInt(parts[3]);
                employees.add(new Tester(name, baseSalary, bugsFound));
            }
        }


        for (int i = 0; i < employees.size(); i++) {
            Employee emp = employees.get(i);
            System.out.println(emp.getName() + " - Bonus: " + emp.calculateBonus());
            if (emp instanceof Developer) {
                System.out.println("Tang khoa hoc AWS");
            } else if (emp instanceof Tester) {
                System.out.println("Tang tool Test");
            }
            
            // Xuong dong cho de nhin
            if (i < employees.size() - 1) {
                System.out.println();
            }
        }
        scanner.close();
    }

}
