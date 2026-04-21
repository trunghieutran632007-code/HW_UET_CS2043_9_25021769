package Bai8_06.src;

public class Main {
    public static void main(String[] args) {
        // Khoi tao thong tin sinh vien
        Student student = new Student("23020001", "Nguyen Van A", 3.5);

        // Khoi tao thong tin mon hoc
        Course course = new Course("UET.CS2043", "Lap trinh nang cao", 3);

        // Khoi tao diem so (Diem GK, Diem CK, Diem BT)
        CourseResult result = new CourseResult(8.0, 7.5, 9.0);

        // Tap hop cac thanh phan vao StudentManager va in bang diem
        StudentManager manager = new StudentManager(student, course, result);

        // Goi phuong thuc in
        manager.printTranscript();
    }
}