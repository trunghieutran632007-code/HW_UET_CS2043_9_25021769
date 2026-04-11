package Bai7_02.src;

import java.util.*;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        Scanner sc = new Scanner(System.in);

        System.out.println("Nhap so luong phan tu: ");
        int n = sc.nextInt();
        sc.nextLine();
        int[] arr = new int[n];

        for (int i = 0; i < n; i++) {
            System.out.println("Nhap phan tu so: " + (i + 1));
            arr[i] = sc.nextInt();
            sc.nextLine();
        }

        System.out.println("Mang sau khi nhap: " + Arrays.toString(arr));
        
        int k = 4;

        ExecutorService executor = Executors.newFixedThreadPool(k);

        List<Future<Integer>> futures = new ArrayList<>();

        int chunkSize = (int) Math.ceil((double) n/k);

        for (int i = 0; i < k; i++) {
            int start = i * chunkSize;
            int end = Math.min(start + chunkSize, n);

            if (start >= n) break; // neu mang ngan hon k doan thi bo qua

            final int s = start;
            final int e = end;

            Callable<Integer> task = () -> {
                int sum = 0;
                for (int j = s; j < e; j++) {
                    sum += arr[j];
                }
                System.out.println("Thread " + Thread.currentThread().getName()
                        + " tinh doan [" + s + ", " + e + ") = " + sum);
                return sum;
            };

            Future<Integer> future = executor.submit(task);
            futures.add(future);
        }

        int total = 0;

        for (Future<Integer> future : futures) {
            total += future.get();
        }

        System.out.println("Tong cuoi: " + total);

        executor.shutdown();
        sc.close();
    }


}
