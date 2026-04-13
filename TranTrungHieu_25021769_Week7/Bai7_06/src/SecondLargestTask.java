package Bai7_06.src;

import java.util.concurrent.Callable;

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

        // Su dung Integer de co the kiem tra null thay vi dung Integer.MIN_VALUE
        Integer first = null;
        Integer second = null;

        for (int x : arr) {
            if (first == null || x > first) {
                second = first;
                first = x;
            } else if ((second == null || x > second) && x != first) {
                second = x;
            }
        }

        if (second == null) {
            throw new Exception("Khong tim thay so lon thu hai");
        }

        return second;
    }
}