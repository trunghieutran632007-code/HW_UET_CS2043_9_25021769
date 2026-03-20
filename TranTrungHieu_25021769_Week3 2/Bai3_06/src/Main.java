package Bai3_06.src;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so luong san pham can mua: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        Product[] Order = new Product[n];

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();

            // xu ly chuoi 
            int firstQuote = line.indexOf('"');
            int lastQuote = line.lastIndexOf('"');

            String type = line.substring(0, firstQuote).strip();
            String name = line.substring(firstQuote + 1, lastQuote);
            
            if (type.equals("E")) {
                String[] numbers = line.substring(lastQuote + 1).strip().split("\\s+");
                double price = Double.parseDouble(numbers[0]);
                double warranty = Double.parseDouble(numbers[1]);
                Order[i] = new Electronics(name, price, warranty);


            } else if (type.equals("F")) {
                String[] tailParts = line.substring(lastQuote + 1).strip().split("\\s+");
                double price = Double.parseDouble(tailParts[0]);
                String expDate = tailParts[1];
                Order[i] = new Food(name, price, expDate);
                
            }

        }

        double total = 0;
        
        for (int i = 0; i < n; i++) {
            // In ra thong tin tung san pham theo dung format
            System.out.println(Order[i].toString());
            
            // Cong don gia cuoi cung vao tong tien
            total += Order[i].getFinalPrice();
        }
        
        // In ra tong tien cuoi cung
        System.out.println("Total = " + total);


        
    }

}
