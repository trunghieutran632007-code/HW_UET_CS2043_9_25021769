package Bai5_08_1.src;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;

public class WriteData {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap ten tep de ghi du lieu: ");
        String fileName = scanner.nextLine();

        System.out.println("Nhap so luong so nguyen n: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        // Su dung try-with-resources de tu dong dong file
        try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName))) {

            for (int i = 0; i < n; i++) {
                System.out.print("Nhap so thu " + (i + 1) + ": ");
                int number = scanner.nextInt();

                dos.writeInt(number);
            }

            System.out.println("Ghi du lieu thanh cong vao " + fileName);

        } catch (IOException e) {
            // Xu ly loi
            System.out.println("Co loi xay ra khi ghi file: " + e.getMessage());
        }

        scanner.close();
    }
}
