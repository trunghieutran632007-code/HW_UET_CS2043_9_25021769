package Bai5_08_2.src;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.util.Scanner;

public class ReadData {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap ten tep de doc du lieu: ");
        String fileName = scanner.nextLine();

        System.out.println("Cac so doc duoc tu file la: ");

        // Su dung try-with-resources de tu dong dong file khi doc xong
        try (DataInputStream dis = new DataInputStream(new FileInputStream(fileName))) {
            
            // Vong lap vo han, se bi ngat khi gap EOFException
            while (true) {
                int number = dis.readInt();
                System.out.print(number + " ");
            }
            
        } catch (EOFException e) {
            // Da den cuoi file
            System.out.println("\n   Da doc het du lieu trong file");
            
        } catch (IOException e) {
            // Bat loi khi nhap sai ten file hoac file khong ton tai
            System.out.println("\nCo loi xay ra hoac file khong ton tai: " + e.getMessage());
        }
        
        scanner.close();
    }
}
