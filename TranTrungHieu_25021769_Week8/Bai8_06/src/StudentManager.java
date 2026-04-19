package Bai8_06.src;

public class StudentManager {
    private Student student;
    private Course course;
    private CourseResult result;

    public StudentManager(Student student, Course course, CourseResult result) {
        this.student = student;
        this.course = course;
        this.result = result;
    }

    // In ra output giong he voi yeu cau goc
    public void printTranscript() {
        System.out.println("Sinh vien: " + student.getName() + " (" + student.getStudentId() + ")");

        System.out.println("Mon hoc: " + course.getCourseName() + " (" + course.getCourseId()
                + ") - " + course.getCredits() + " tin chi");

        System.out.println("Diem GK: " + result.getMidtermScore() + " | Diem CK: "
                + result.getFinalScore() + " | Diem BT: " + result.getAssignmentScore());

        System.out.printf("Diem tong ket: %.1f - Hoc luc: %s%n",
                result.calculateFinalGrade(), result.getAcademicStatus());
    }

}
