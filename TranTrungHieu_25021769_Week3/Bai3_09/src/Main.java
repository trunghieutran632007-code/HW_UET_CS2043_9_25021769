package Bai3_09.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        IPayable[] payableList = new IPayable[n];
        double totalPayment = 0;

        for (int i = 0; i < n; i++) {
            // Doc toan bo 1 dong va loai bo khoang trang thua o hai dau (trim)
            String line = scanner.nextLine().trim();
            String[] parts = line.split("\\s+");

            if (parts.length == 0) continue;

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
                totalPayment += payableList[i].getPaymentAmount();
            }
        }

        

        scanner.close();

    }

}
