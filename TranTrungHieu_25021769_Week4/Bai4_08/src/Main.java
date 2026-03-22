package Bai4_08.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        Hub hub = new Hub();
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.strip().split("\\s+");
            String type = parts[0];
            String name = parts[1];
            String id = parts[2];

            
        }
    }

}
