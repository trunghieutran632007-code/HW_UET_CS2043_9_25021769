package Bai7_08.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {

    // Ham kiem tra so nguyen to
    private static boolean isPrime(int n) {
        if (n < 2)
            return false;
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        if (!scanner.hasNextInt())
            return;
        int n = scanner.nextInt();

        int[][] arrays = new int[n][];
        for (int i = 0; i < n; i++) {
            int m = scanner.nextInt();
            arrays[i] = new int[m];
            for (int j = 0; j < m; j++) {
                arrays[i][j] = scanner.nextInt();
            }
        }

        // Khoi tao hai thread pool rieng biet
        ExecutorService pool1 = Executors.newFixedThreadPool(3);
        ExecutorService pool2 = Executors.newFixedThreadPool(3);

        // Danh sach luu tru cac future de tinh tong cuoi cung
        List<CompletableFuture<Long>> futures = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            final int index = i;
            final int[] arr = arrays[i];

            // Giai doan 1: Loc so nguyen to chay tren pool1
            CompletableFuture<Long> future = CompletableFuture.supplyAsync(() -> {
                List<Integer> primes = new ArrayList<>();
                for (int num : arr) {
                    if (isPrime(num)) {
                        primes.add(num);
                    }
                }

                // In ket qua ngay khi Giai doan 1 hoan thanh
                System.out.println("Stage 1 - Array " + index + ": " + primes.toString());

                return primes;

            }, pool1)
                    // Giai doan 2: Tinh toan dua tren ket qua Giai doan 1 chay tren pool2
                    .thenApplyAsync(primes -> {
                        long sum = 0;
                        int count = primes.size();

                        if (count % 2 == 0) {
                            // So luong chan: Tinh tong binh phuong
                            for (int p : primes) {
                                sum += (long) p * p;
                            }
                            System.out.println("Stage 2 - Array " + index + ": sum of squares = " + sum);
                        } else {
                            // So luong le: Tinh tong lap phuong
                            for (int p : primes) {
                                sum += (long) p * p * p;
                            }
                            System.out.println("Stage 2 - Array " + index + ": sum of cubes = " + sum);
                        }

                        return sum;
                    }, pool2);

            futures.add(future);
        }

        // Cho tat ca cac mang xu ly xong ca 2 giai doan va cong don ket qua
        long totalSum = 0;
        for (CompletableFuture<Long> f : futures) {
            try {
                totalSum += f.get(); // Lay ket qua tra ve tu Giai doan 2
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // In tong cuoi cung
        System.out.println("Total = " + totalSum);

        // Dong cac thread pool sau khi hoan thanh cong viec
        pool1.shutdown();
        pool2.shutdown();
        scanner.close();
    }
}