package Bai5_06.src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Nhap so nguyen a: ");
            int a = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Nhap so nguyen b: ");
            int b = scanner.nextInt();
            scanner.nextLine();

            System.out.println("Ket qua: " + a / b);

        } catch (InputMismatchException e){
            System.out.println("Ban phai nhap vao so nguyen!");
        } catch (ArithmeticException e) {
            System.out.println("Khong the chia cho 0!");
        } finally {
            System.out.println("Program finished .");
            scanner.close();
        }
    }

}
