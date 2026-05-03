package com.example;

/**
 * A simple calculator that supports basic arithmetic operations
 * and some utility methods for demonstration of code coverage.
 */
public class Calculator {

    /**
     * Cong hai so.
     */
    public int add(int a, int b) {
        return a + b;
    }

    /**
     * Tru hai so.
     */
    public int subtract(int a, int b) {
        return a - b;
    }

    /**
     * Nhan hai so.
     */
    public int multiply(int a, int b) {
        return a * b;
    }

    /**
     * Chia hai so. Nem ArithmeticException neu chia cho 0.
     */
    public double divide(int a, int b) {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero");
        }
        return (double) a / b;
    }

    /**
     * Tinh gia tri tuyet doi.
     */
    public int abs(int value) {
        if (value < 0) {
            return -value;
        }
        return value;
    }

    /**
     * Tinh giai thua cua n (n >= 0).
     */
    public long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("n must be non-negative");
        }
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }

    /**
     * Kiem tra so nguyen to.
     */
    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        if (n % 2 == 0 || n % 3 == 0) {
            return false;
        }
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * Tim uoc chung lon nhat (GCD) bang thuat toan Euclid.
     */
    public int gcd(int a, int b) {
        a = abs(a);
        b = abs(b);
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
