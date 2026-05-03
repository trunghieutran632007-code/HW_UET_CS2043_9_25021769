package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private Calculator calc;

    @BeforeEach
    void setUp() {
        calc = new Calculator();
    }

    // --- add ---
    @Test
    void testAdd() {
        assertEquals(8, calc.add(3, 5));
        assertEquals(0, calc.add(-3, 3));
        assertEquals(-7, calc.add(-3, -4));
    }

    // --- subtract ---
    @Test
    void testSubtract() {
        assertEquals(6, calc.subtract(10, 4));
        assertEquals(-1, calc.subtract(2, 3));
    }

    // --- multiply ---
    @Test
    void testMultiply() {
        assertEquals(42, calc.multiply(6, 7));
        assertEquals(0, calc.multiply(0, 100));
        assertEquals(-12, calc.multiply(-3, 4));
    }

    // --- divide ---
    @Test
    void testDivide() {
        assertEquals(2.5, calc.divide(5, 2), 0.0001);
        assertEquals(-3.0, calc.divide(9, -3), 0.0001);
    }

    @Test
    void testDivideByZero() {
        ArithmeticException ex = assertThrows(
            ArithmeticException.class,
            () -> calc.divide(10, 0)
        );
        assertEquals("Cannot divide by zero", ex.getMessage());
    }

    // --- abs ---
    @Test
    void testAbsPositive() {
        assertEquals(5, calc.abs(5));
    }

    @Test
    void testAbsNegative() {
        assertEquals(42, calc.abs(-42));
    }

    @Test
    void testAbsZero() {
        assertEquals(0, calc.abs(0));
    }

    // --- factorial ---
    @Test
    void testFactorial() {
        assertEquals(1, calc.factorial(0));
        assertEquals(1, calc.factorial(1));
        assertEquals(120, calc.factorial(5));
        assertEquals(720, calc.factorial(6));
    }

    @Test
    void testFactorialNegative() {
        assertThrows(IllegalArgumentException.class, () -> calc.factorial(-1));
    }

    // --- isPrime ---
    @Test
    void testIsPrime() {
        assertFalse(calc.isPrime(0));
        assertFalse(calc.isPrime(1));
        assertTrue(calc.isPrime(2));
        assertTrue(calc.isPrime(3));
        assertFalse(calc.isPrime(4));
        assertTrue(calc.isPrime(5));
        assertTrue(calc.isPrime(17));
        assertFalse(calc.isPrime(25));  // 5*5
        assertFalse(calc.isPrime(9));   // 3*3
        assertTrue(calc.isPrime(29));
        assertFalse(calc.isPrime(49));  // 7*7, covers i+2 branch
    }

    // --- gcd ---
    @Test
    void testGcd() {
        assertEquals(6, calc.gcd(48, 18));
        assertEquals(1, calc.gcd(17, 13));
        assertEquals(5, calc.gcd(0, 5));
        assertEquals(7, calc.gcd(-21, 14));
    }
}
