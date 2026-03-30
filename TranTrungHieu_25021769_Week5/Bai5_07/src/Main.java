package Bai5_07.src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap duong dan tep nguon: ");
        String sourcePath = scanner.nextLine();

        System.out.println("Nhap duong dan tep dich: ");
        String destinationPath = scanner.nextLine();

        BufferedReader reader = null;
        PrintWriter writer = null;
        int lineCount = 0;

        try {
            reader = new BufferedReader(new FileReader(sourcePath));
            writer = new PrintWriter(new FileWriter(destinationPath));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.println(line);
                lineCount++;
            }
            System.out.println("So dong da sao chep: " + lineCount);

        } catch (FileNotFoundException e) {
            File srcFile = new File(sourcePath);
            if (!srcFile.exists()) {
                System.out.println("Source file not found.");
            } else {
                System.out.println("Cannot create destination file.");
            }

        } catch (IOException e) {
            System.out.println("I/O error.");
            e.printStackTrace();
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
                if (writer != null) {
                    writer.close();
                }
            } catch (IOException e) {
                System.out.println("Loi khi dong tep.");
                e.printStackTrace();
            }
            scanner.close();
        }

    }
}
