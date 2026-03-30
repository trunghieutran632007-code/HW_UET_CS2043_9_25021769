package Bai5_10.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String fileName = scanner.nextLine();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(fileName));
            Map<String, String> configMap = new HashMap<>();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.strip();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("=", 2);
                if (parts.length == 2) {
                    configMap.put(parts[0].strip(), parts[1].strip());
                }
            }
            // Kiem tra du lieu
            validateConfig(configMap);

            for (Map.Entry<String, String> entry : configMap.entrySet()) {
                System.out.println(entry.getKey() + "=" + entry.getValue());
            }
            System.out.println("Config loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println(" Config file not found.");
        } catch (IOException e) {
            System.out.println("I/O error.");
            e.printStackTrace();
        } catch (NumberFormatException e) {
            System.out.println(" Invalid number format.");
        } catch (InvalidConfigException e) {
            System.out.println("Invalid config: " + e.getMessage());

        } finally {
            scanner.close();
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    System.out.println("Loi khi dong file.");
                }
            }
            System.out.println("Program finished.");

        }
    }

    // Kiem tra xem co hop le khong
    public static void validateConfig(Map<String, String> configMap)
            throws InvalidConfigException, NumberFormatException {
        if (!configMap.containsKey("username")) {
            throw new InvalidConfigException("Missing username");
        }
        if (!configMap.containsKey("timeout")) {
            throw new InvalidConfigException("Missing timeout");
        }

        int timeout = Integer.parseInt(configMap.get("timeout"));
        if (timeout <= 0) {
            throw new InvalidConfigException("timeout must be > 0");
        }

        if (configMap.containsKey("maxConnections")) {
            int maxConnections = Integer.parseInt(configMap.get("maxConnections"));
            if (maxConnections < 1) {
                throw new InvalidConfigException("maxConnections must be >= 1");
            }
        }
    }

}
