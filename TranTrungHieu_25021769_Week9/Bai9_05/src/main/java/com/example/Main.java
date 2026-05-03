package com.example;

/**
 * Entry point - demo Calculator usage.
 */
public class Main {
    public static void main(String[] args) {
        Calculator calc = new Calculator();

        System.out.println("=== Calculator Demo ===");
        System.out.println("add(3, 5)       = " + calc.add(3, 5));
        System.out.println("subtract(10, 4) = " + calc.subtract(10, 4));
        System.out.println("multiply(6, 7)  = " + calc.multiply(6, 7));
        System.out.println("divide(10, 3)   = " + calc.divide(10, 3));
        System.out.println("abs(-42)        = " + calc.abs(-42));
        System.out.println("factorial(6)    = " + calc.factorial(6));
        System.out.println("isPrime(17)     = " + calc.isPrime(17));
        System.out.println("gcd(48, 18)     = " + calc.gcd(48, 18));
    }
}
