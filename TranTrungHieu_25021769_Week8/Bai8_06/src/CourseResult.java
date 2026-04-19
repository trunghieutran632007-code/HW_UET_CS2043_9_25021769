package Bai8_06.src;

public class CourseResult {
    private double midtermScore;
    private double finalScore;
    private double assignmentScore;

    public CourseResult(double midtermScore, double finalScore, double assignmentScore) {
        this.midtermScore = midtermScore;
        this.finalScore = finalScore;
        this.assignmentScore = assignmentScore;
    }

    public double getMidtermScore() {
        return midtermScore;
    }

    public double getFinalScore() {
        return finalScore;
    }

    public double getAssignmentScore() {
        return assignmentScore;
    }

    public double calculateFinalGrade() {
        return assignmentScore * 0.2 + midtermScore * 0.3 + finalScore * 0.5;
    }

    public String getAcademicStatus() {
        double grade = calculateFinalGrade();
        if (grade >= 8.5)
            return "Gioi";
        if (grade >= 7.0)
            return "Kha";
        if (grade >= 5.5)
            return "Trung binh";
        return "Yeu";
    }

}
