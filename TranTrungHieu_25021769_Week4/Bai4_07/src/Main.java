package Bai4_07.src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so luong sinh vien: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.strip().split("\\s+");
            students.add(new Student(parts[0], parts[1], Double.parseDouble(parts[2])));
        }

        //Xoa sinh vien co gpa < 5.0
        students.removeIf(student -> student.getGpa() < 5.0);
        System.out.println("After removing GPA < 5.0:");

        for (Student s : students) {
            System.out.println(s);
        }

        //Sap xep theo ten
        students.sort((s1, s2) -> s1.getName().compareTo(s2.getName()));
        System.out.println("\nAfter sorting by name:");
        for (Student s : students) {
            System.out.println(s);
        }

        //Dinh nghia lai phep + - * / bang lambda
        Operation<Double> add      = (a, b) -> a + b;
        Operation<Double> subtract = (a, b) -> a - b;
        Operation<Double> multiply = (a, b) -> a * b;
        Operation<Double> divide   = (a, b) -> a / b;
    }

}
