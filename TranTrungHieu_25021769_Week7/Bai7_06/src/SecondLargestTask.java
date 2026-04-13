package Bai7_06.src;

import java.util.*;
import java.util.concurrent.*;

public class SecondLargestTask implements Callable<Integer> {
    private final int[] arr;

    public SecondLargestTask(int[] arr) {
        this.arr = arr;
    }

    @Override
    public Integer call() throws Exception {
        if (arr == null || arr.length < 2) {
            throw new Exception("Mang khong du phan tu");
        }

        int first = Integer.MIN_VALUE;
        int second = Integer.MIN_VALUE;

        for (int x : arr) {
            if (x > first) {
                second = first;
                first = x;
            } else if (x > second && x != first) {
                second = x;
            }
        }

        if (second == Integer.MIN_VALUE) {
            throw new Exception("Khong tim thay so lon thu hai");
        }

        return second;
    }

}
