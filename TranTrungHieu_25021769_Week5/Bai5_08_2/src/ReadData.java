package Bai5_08_2.src;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.EOFException;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class ReadData {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap ten tep (trong folder Bai5_08_1) de doc du lieu: ");
        String fileName = scanner.nextLine();

        // Dung "../" de lui ve thu muc cha (TranTrungHieu_25021769_Week5)
        // sau do di vao thu muc Bai5_08_1
        String filePath = "../Bai5_08_1/" + fileName;

        // In ra duong dan tuyet doi de kiem tra xem da tro dung chua
        File file = new File(filePath);
        System.out.println("\nChuong trinh dang tim file tai: " + file.getAbsolutePath());

        System.out.println("\nCac so doc duoc tu file la: ");

        // Su dung try-with-resources de tu dong dong file
        try (DataInputStream dis = new DataInputStream(new FileInputStream(filePath))) {
            
            // Vong lap doc du lieu cho den khi gap EOFException
            while (true) {
                int number = dis.readInt();
                System.out.print(number + " ");
            }
            
        } catch (EOFException e) {
            // Ket thuc file
            System.out.println("\nDa doc het du lieu trong file.");
            
        } catch (IOException e) {
            // Xu ly loi file khong ton tai hoac sai duong dan
            System.out.println("\nCo loi xay ra hoac file khong ton tai: " + e.getMessage());
        }
        
        scanner.close();
    }
}