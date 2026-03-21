package Bai4_06.src;

public class Student implements Comparable<Student> {
    private String name;
    private double gpa;

    public Student(String name, double gpa) {
        this.name = name;
        this.gpa  = gpa;
    }

    @Override
    public int compareTo(Student other) {
        // So sanh theo GPA tang dan
        return Double.compare(this.gpa, other.gpa);
    }

    @Override
    public String toString() {
        return name + "(" + gpa + ")";
    }
}
