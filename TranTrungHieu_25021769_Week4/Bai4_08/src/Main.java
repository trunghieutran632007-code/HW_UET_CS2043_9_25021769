package Bai4_08.src;

import java.util.Scanner;

import Bai2_10.src.SmartLight;

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

            if (parts[0].equalsIgnoreCase("L")) {
                hub.addDevice(new SmartLight(id, name));
            } else if (parts[0].equalsIgnoreCase("AC")) {
                hub.addDevice(new AirConditioner(id, name));
            } else if (parts[0].equalsIgnoreCase("S")) {
                hub.addDevice(new SmartSpeaker(id, name));
            } else if (parts[0].equalsIgnoreCase("C")) {
                hub.addDevice(new Curtain(id, name));
            }

            
        }
    }

}
