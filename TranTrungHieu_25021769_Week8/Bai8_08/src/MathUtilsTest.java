package Bai8_08.src;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MathUtilsTest {
    @BeforeAll
    static void setUpAll() {
        System.out.println("=== Bat dau chay MathUtilsTest ===");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("=== Ket thuc ===");
    }

    // === Test method max(int a, int b) ===
    // EP
    @Test
    @DisplayName("EP - a > b: Ky vong tra ve a")
    void maxAGreaterthanB() {
        assertEquals(6, MathUtils.max(6, 3));
    }

    @Test
    @DisplayName("EP - a < b: ky vong tra ve b")
    void maxALessThanB() {
        assertEquals(6, MathUtils.max(3, 6));
    }

    @Test
    @DisplayName("EP - a = b: ky vong tra ve a hoac b")
    void maxAEqualsB() {
        assertEquals(6, MathUtils.max(6, 6));
    }

    // BVA
    @Test
    @DisplayName("BVA - a = Integer.MAX_VALUE, b = 0")
    void aIsMax() {
        assertEquals(Integer.MAX_VALUE, MathUtils.max(Integer.MAX_VALUE, 0));
    }

    @Test
    @DisplayName("BVA - a = Integer.MIN_VALUE, b = 0")
    void aIsMin() {
        assertEquals(0, MathUtils.max(Integer.MIN_VALUE, 0));
    }

    @Test
    @DisplayName("BVA - a = 0, b = Integer.MAX_VALUE")
    void bIsMax() {
        assertEquals(Integer.MAX_VALUE, MathUtils.max(0, Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("BVA - a = 0, b = Integer.MIN_VALUE")
    void bIsMin() {
        assertEquals(0, MathUtils.max(0, Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("BVA - ca hai bien: MIN_VALUE vs MAX_VALUE")
    void maxAndMin() {
        assertEquals(Integer.MAX_VALUE,
                MathUtils.max(Integer.MIN_VALUE, Integer.MAX_VALUE));
        assertEquals(Integer.MAX_VALUE,
                MathUtils.max(Integer.MAX_VALUE, Integer.MIN_VALUE));
    }

    @Test
    @DisplayName("BVA - a = b = Integer.MAX_VALUE")
    void bothAreMax() {
        assertEquals(Integer.MAX_VALUE,
                MathUtils.max(Integer.MAX_VALUE, Integer.MAX_VALUE));
    }

    @Test
    @DisplayName("BVA - a = b = Integer.MIN_VALUE")
    void bothAreMin() {
        assertEquals(Integer.MIN_VALUE,
                MathUtils.max(Integer.MIN_VALUE, Integer.MIN_VALUE));
    }

    // === Test method divide ===

    @Test
    @DisplayName("EP - b > 0: phep chia binh thuong")
    void bPositive() {
        assertEquals(5, MathUtils.divide(10, 2));
    }

    @Test
    @DisplayName("EP - a > 0, b < 0: ket qua co dau am")
    void aPositiveBNegative() {
        assertEquals(-5, MathUtils.divide(10, -2));
    }

    @Test
    @DisplayName("EP - b = 0: NEM IllegalArgumentException")
    void bEqualsZero() {
        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> MathUtils.divide(10, 0));
        assertEquals("Divider must not be zero", ex.getMessage());
    }

    // ---------- Cac truong hop bo sung (tang do bao phu) ----------
    @Test
    @DisplayName("a = 0 va b != 0: 0 / b = 0")
    void aEqualsZero() {
        assertEquals(0, MathUtils.divide(0, 5));
    }

    @Test
    @DisplayName("a < 0 va b > 0: ket qua am")
    void aNegativeBPositive() {
        assertEquals(-5, MathUtils.divide(-10, 2));
    }

    @Test
    @DisplayName("a < 0 va b < 0: ket qua duong")
    void bothAreNegative() {
        assertEquals(5, MathUtils.divide(-10, -2));
    }

    @Test
    @DisplayName("Integer division: 7 / 2 = 3 ")
    void lamTronXuong() {
        assertEquals(3, MathUtils.divide(7, 2));
    }

}
