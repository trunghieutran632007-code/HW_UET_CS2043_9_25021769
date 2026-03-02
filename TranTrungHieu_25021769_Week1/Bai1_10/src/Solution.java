import java.util.Scanner;
public class Solution {
    public int secondLargest(int[] arr) {
        if (arr == null || arr.length < 2) {
            return -1;
        }

        Integer largest = null;
        Integer secondLargest = null;

        for (int num : arr) {
            if (largest == null || num > largest) {
                secondLargest = largest;
                largest = num;
            }
            else if (num < largest && (secondLargest == null || num > secondLargest)) {
                secondLargest = num;
            }
        }

        if (secondLargest == null) {
            return -1;
        }

        return secondLargest;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Scanner sc = new Scanner(System.in);
        int[] normalArray = {12, 35, 1, 10, 34, 1};
        int[] duplicateArray = {10, 10, 10};
        int[] singleElementArray = {5};
        int[] duplicateLargestArray = {10, 5, 10};
        int[] negativeArray = {-5, -1, -10, -2};
        System.out.println(sol.secondLargest(normalArray));
        System.out.println(sol.secondLargest(duplicateArray));
        System.out.println(sol.secondLargest(singleElementArray));
        System.out.println(sol.secondLargest(duplicateLargestArray));
        System.out.println(sol.secondLargest(negativeArray));
        System.out.print("Nhap so luong phan tu cua mang : ");
        int n = sc.nextInt();


        if (n < 2) {
            System.out.println(-1);
        } else {

            int[] arr = new int[n];

            for (int i = 0; i < n; i++) {
                System.out.print("Phan tu tai vi tri [" + i + "]: ");
                arr[i] = sc.nextInt();
            }

            int result = sol.secondLargest(arr);

            if (result != -1) {
                System.out.println("So lon thu hai la: " + result);
            } else {
                System.out.println(-1);
            }
        }
    }
}