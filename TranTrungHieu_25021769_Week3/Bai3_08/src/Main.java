package Bai3_08.src;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();
        Robot[] robots = new Robot[n];
        
        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] words = line.strip().split("\\s+");
            String type = words[0];
            int id = Integer.parseInt(words[1]);
            String modelName = words[2];

            if (type.equalsIgnoreCase("DR")) {
                robots[i] = new DroneRobot(id, modelName);
            } else if (type.equalsIgnoreCase("FR")) {
                robots[i] = new FishRobot(id, modelName);
            } else if (type.equalsIgnoreCase("AR")) {
                robots[i] = new AmphibiousRobot(id, modelName);
            }
        }

        for (int i = 0; i < n; i++) {
            Robot robot = robots[i];
            robot.performMainTask();
            if (robot instanceof Flyable) {
                ((Flyable) robot).fly();
            }
            if (robot instanceof Swimmable) {
                ((Swimmable) robot).swim();
            }
            if (robot instanceof GPS) {
                ((GPS) robot).getCoordinates();
            }
        }
        scanner.close();
    }

}
