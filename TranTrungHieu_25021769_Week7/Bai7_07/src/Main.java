package Bai7_07.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so luong mang: ");
        int n = scanner.nextInt();

        int[][] arrays = new int[n][];

        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            arrays[i] = new int[m];
            for (int j = 0; j < m; j++) {
                arrays[i][j] = scanner.nextInt();
            }
        }
        // Tao mang luu so dem so nguyen to cua tung mang mot
        int[] primeCounts = new int[n];
        // Tao mang luu n luong tuong ung voi n numArr
        Thread[] threads = new Thread[n];

        // Xu ly n mang dong thoi bang cach tao n luong
        for (int i = 0; i < n; i++) {
            final int index = i;
            threads[i] = new Thread(() -> {
                int count = 0;
                for (int num : arrays[index]) {
                    if (isPrime(num)) {
                        count++;
                    }
                }
                // Luu ket qua cua luong vao mang chung
                primeCounts[index] = count;
            });
            threads[i].start(); // Bat dau chay luong
        }
        // Cho tat ca cac luong hoan thanh viec dem
        for (int i = 0; i < n; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Co loi gian doan!");
                e.printStackTrace();
            }
        }

        // In ra ket qua dem duoc cua tung mang va tim so nguyen to lon nhat
        int maxPrimes = -1;
        for (int i = 0; i < n; i++) {
            System.out.println("Array " + i + ": " + primeCounts[i]);
            if (primeCounts[i] > maxPrimes) {
                maxPrimes = primeCounts[i];
            }
        }

        // Tim tat ca cac mang co cung so luong so nguyen to nhieu nhat
        List<Integer> maxIndices = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (primeCounts[i] == maxPrimes) {
                maxIndices.add(i);
            }
        }

        // In ra ket luan cuoi cung
        for (int index : maxIndices) {
            System.out.println("Most primes: Array " + index + " with " + maxPrimes + " primes");
        }

        scanner.close();

    }

    public static boolean isPrime(int n) {
        if (n <= 1)
            return false;
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

}
