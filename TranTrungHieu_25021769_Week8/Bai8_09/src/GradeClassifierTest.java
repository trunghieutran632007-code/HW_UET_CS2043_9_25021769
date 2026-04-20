package Bai8_09.src;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GradeClassifierTest {

    @Test
    @DisplayName("Kiem tra cac gia tri bien cho hoc luc Yeu [0.0, 5.0)")
    public void testGradeYeu() {
        assertEquals("Yếu", GradeClassifier.classifyGrade(0.0));
        assertEquals("Yếu", GradeClassifier.classifyGrade(4.9));
    }

    @Test
    @DisplayName("Kiem tra cac gia tri bien cho hoc luc Trung binh [5.0, 6.5)")
    public void testGradeTrungBinh() {
        assertEquals("Trung bình", GradeClassifier.classifyGrade(5.0));
        assertEquals("Trung bình", GradeClassifier.classifyGrade(6.4));
    }

    @Test
    @DisplayName("Kiem tra cac gia tri bien cho hoc luc Kha [6.5, 8.0)")
    public void testGradeKha() {
        assertEquals("Khá", GradeClassifier.classifyGrade(6.5));
        assertEquals("Khá", GradeClassifier.classifyGrade(7.9));
    }

    @Test
    @DisplayName("Kiem tra cac gia tri bien cho hoc luc Gioi [8.0, 10.0]")
    public void testGradeGioi() {
        assertEquals("Giỏi", GradeClassifier.classifyGrade(8.0));
        assertEquals("Giỏi", GradeClassifier.classifyGrade(10.0));
    }

    @Test
    @DisplayName("Kiem tra ngoai le va thong bao loi cho gia tri bien khong hop le")
    public void testInvalidGpaThrowsException() {
        IllegalArgumentException exLow = assertThrows(IllegalArgumentException.class, () -> {
            GradeClassifier.classifyGrade(-0.1);
        });
        assertEquals("GPA không hợp lệ: -0.1", exLow.getMessage());

        IllegalArgumentException exHigh = assertThrows(IllegalArgumentException.class, () -> {
            GradeClassifier.classifyGrade(10.1);
        });
        assertEquals("GPA không hợp lệ: 10.1", exHigh.getMessage());
    }

    @Test
    @DisplayName("Kiem tra cac gia tri tieu bieu giua cac khoang tuong duong (EP Hop le)")
    public void testValidEquivalencePartitions() {
        assertEquals("Yếu", GradeClassifier.classifyGrade(2.5));
        assertEquals("Trung bình", GradeClassifier.classifyGrade(6.0));
        assertEquals("Khá", GradeClassifier.classifyGrade(7.0));
        assertEquals("Giỏi", GradeClassifier.classifyGrade(9.0));
    }

    @Test
    @DisplayName("Kiem tra cac gia tri tieu bieu thuoc khoang tuong duong khong hop le (EP Khong hop le)")
    public void testInvalidEquivalencePartitions() {
        IllegalArgumentException exLow = assertThrows(IllegalArgumentException.class, () -> {
            GradeClassifier.classifyGrade(-5.0);
        });
        assertEquals("GPA không hợp lệ: -5.0", exLow.getMessage());

        IllegalArgumentException exHigh = assertThrows(IllegalArgumentException.class, () -> {
            GradeClassifier.classifyGrade(15.0);
        });
        assertEquals("GPA không hợp lệ: 15.0", exHigh.getMessage());
    }
}
