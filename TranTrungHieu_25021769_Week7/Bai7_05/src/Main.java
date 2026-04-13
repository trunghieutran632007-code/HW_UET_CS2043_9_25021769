package Bai7_05.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhap so luong don hang m: ");
        int m = scanner.nextInt();
        scanner.nextLine();

        List<String> logs = new ArrayList<>();
        AtomicInteger count = new AtomicInteger(0);

        ExecutorService executor = Executors.newFixedThreadPool(3);
        List<Future<Boolean>> futures = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            System.out.println("---");
            System.out.println("Nhap id don hang " + (i + 1) + ": ");
            String id = scanner.nextLine();
            System.out.println("Nhap thoi gian xu ly (ms) cho " + id + ": ");
            long processMs = scanner.nextLong();
            scanner.nextLine();

            OrderTask task = new OrderTask(id, processMs, logs, count);
            futures.add(executor.submit(task));
        }

        for (Future<Boolean> future : futures) {
            try {
                future.get();
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Co loi xay ra trong qua trinh thuc thi task.");
                e.printStackTrace();
            }
        }

        // In ket qua sau khi da xu ly xong tat ca don hang
        System.out.println("\n=== KET QUA TONG HOP ===");
        System.out.println("Success = " + count.get());
        System.out.println("Danh sach log theo thu tu hoan thanh:");
        for (String log : logs) {
            System.out.println(log);
        }

        // Dong ExecutorService dung cach
        executor.shutdown();
        scanner.close();
    }

}
