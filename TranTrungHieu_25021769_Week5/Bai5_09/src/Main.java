package Bai5_09.src;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Student> students = new ArrayList<>();
        String fileName = "students.txt";
        System.out.println("=== Nhap thong tin sinh vien ===");

        while (true) {
            System.out.println("Nhap ID: ");
            String id = scanner.nextLine();

            if (id.equalsIgnoreCase("END")) {
                break;
            }

            System.out.println("Nhap ten: ");
            String name = scanner.nextLine();

            System.out.println("Nhap GPA: ");
            double gpa = scanner.nextDouble();
            scanner.nextLine();

            Student student = new Student(id, name, gpa);
            students.add(student);
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Student student : students) {
                oos.writeObject(student);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file");
        } catch (IOException e) {
            System.out.println("Loi khi ghi file");
        }

        System.out.println("=== Doc danh sach tu file ===");
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                Student student = (Student) ois.readObject();
                System.out.println(student);
            }
            
        } catch (EOFException e) {
            System.out.println("Da doc het file");
        } catch (ClassNotFoundException e) {
            System.out.println("Khong tim thay class Student");
        } catch (FileNotFoundException e) {
            System.out.println("Khong tim thay file");
        } catch (IOException e) {
            System.out.println("Loi IO");
        }
    }

}
