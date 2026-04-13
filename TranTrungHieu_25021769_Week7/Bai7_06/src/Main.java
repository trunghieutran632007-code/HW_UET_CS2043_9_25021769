package Bai7_06.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Nhap n (so luong mang): ");
        int n = sc.nextInt();

        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<Future<Integer>> listFuture = new ArrayList<>();

        // 1 & 2. Nhap mang va gui vao ExecutorService
        for (int i = 0; i < n; i++) {
            System.out.print("Nhap so phan tu cua mang thu " + (i + 1) + ": ");
            int m = sc.nextInt();
            
            int[] arr = new int[m];
            System.out.println("Nhap cac phan tu: ");
            for (int j = 0; j < m; j++) {
                arr[j] = sc.nextInt(); // Khong can sc.nextLine() o day
            }
            
            Callable<Integer> task = new SecondLargestTask(arr);
            listFuture.add(executor.submit(task));
        }

        // 3, 4 & 5. Dung Future.get() de lay ket qua va tinh tong
        long totalSum = 0;
        System.out.println("\n=== Ket qua tung mang ===");
        
        for (int i = 0; i < listFuture.size(); i++) {
            try {
                Integer result = listFuture.get(i).get();
                System.out.println("Mang " + (i + 1) + " - So lon thu hai: " + result);
                totalSum += result;
            } catch (InterruptedException | ExecutionException e) {
                // Xu ly truong hop mang khong co so lon thu hai hop le
                // Su dung e.getCause().getMessage() de lay thong bao loi chinh xac nhat
                String errorMsg = e.getCause() != null ? e.getCause().getMessage() : e.getMessage();
                System.out.println("Mang " + (i + 1) + " - Bo qua (Loi: " + errorMsg + ")");
            }
        }

        System.out.println("-------------------------");
        System.out.println("Tong cuoi cung: " + totalSum);

        // Dong executor
        executor.shutdown();
        sc.close();
    }
}