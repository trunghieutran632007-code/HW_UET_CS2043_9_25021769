import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SolutionTest {

    @Test
    @DisplayName("Negative input returns -1")
    void negativeInputReturnsMinusOne() {
        assertEquals(-1L, Solution.fibonacci(-1));
        assertEquals(-1L, Solution.fibonacci(-100));
    }

    @Test
    @DisplayName("Base cases: F(0)=0, F(1)=1")
    void baseCases() {
        assertEquals(0L, Solution.fibonacci(0));
        assertEquals(1L, Solution.fibonacci(1));
    }

    @ParameterizedTest(name = "F({0}) = {1}")
    @CsvSource({
            "2, 1",
            "3, 2",
            "5, 5",
            "10, 55",
            "20, 6765",
            "50, 12586269025",
            "92, 7540113804746346429"
    })
    @DisplayName("Standard Fibonacci values up to F(92)")
    void standardValues(long n, long expected) {
        assertEquals(expected, Solution.fibonacci(n));
    }

    @Test
    @DisplayName("F(93) and beyond should return Long.MAX_VALUE (overflow guard)")
    void overflowReturnsMaxValue() {
        assertEquals(Long.MAX_VALUE, Solution.fibonacci(93));
        assertEquals(Long.MAX_VALUE, Solution.fibonacci(1000));
        assertEquals(Long.MAX_VALUE, Solution.fibonacci(Long.MAX_VALUE));
    }
}
