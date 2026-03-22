package Bai4_07.src;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        scanner.nextLine();

        List<Student> students = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            String line = scanner.nextLine();
            String[] parts = line.strip().split("//s+");
            students.add(new Student(parts[0], parts[1], Double.parseDouble(parts[2])));
        }

        students.removeIf(student -> student.getGpa() < 5.0);
        System.out.println("After removing GPA < 5.0:");
    }

}
