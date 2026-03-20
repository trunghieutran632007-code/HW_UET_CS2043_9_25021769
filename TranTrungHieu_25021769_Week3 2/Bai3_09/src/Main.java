package Bai3_09.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so doi tuong can thanh toan: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        IPayable[] payableList = new IPayable[n];
        double totalPayment = 0;

        for (int i = 0; i < n; i++) {
            // Doc toan bo 1 dong va loai bo khoang trang thua o hai dau (trim)
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");

            if (parts.length == 0)
                continue;

            String type = parts[0];

            if (type.equals("S")) {
                String id = parts[1];
                String name = parts[2];
                int workingHours = Integer.parseInt(parts[3]);
                double hourlyRate = Double.parseDouble(parts[4]);

                payableList[i] = new PartTimeStaff(id, name, workingHours, hourlyRate);

            } else if (type.equals("I")) {
                String itemName = parts[1];
                int quantity = Integer.parseInt(parts[2]);
                double pricePerItem = Double.parseDouble(parts[3]);

                payableList[i] = new Invoice(itemName, quantity, pricePerItem);
            }
        }

        for (int i = 0; i < n; i++) {
            if (payableList[i] != null) {
                double amount = payableList[i].getPaymentAmount();
                totalPayment += amount;

                if (payableList[i] instanceof PartTimeStaff) {
                    PartTimeStaff pts = (PartTimeStaff) payableList[i];
                    System.out.println("PartTimeStaff " + pts.getName() + " - Payment: " + amount);
                } else if (payableList[i] instanceof Invoice) {
                    Invoice inv = (Invoice) payableList[i];
                    System.out.println("Invoice " + inv.itemName + " - Payment: " + amount);
                }
            }
        }

        System.out.println("Total Payment = " + totalPayment);
        scanner.close();

    }

}
