import java.util.Scanner;
public class Solution {
    public int reverse(int n) {
        long reversed = 0;

        while (n != 0) {
            int lastDigit = n % 10;
            reversed = reversed * 10 + lastDigit;
            n = n / 10;

            //TH vuot qua kieu du lieu int
            if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
                return 0;
            }
        }

        return (int) reversed;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        Scanner scan = new Scanner(System.in);
        System.out.println(sol.reverse(20));
        System.out.println(sol.reverse(-123));
        System.out.println(sol.reverse(450));
        System.out.println(sol.reverse(1534236469));
        System.out.println(sol.reverse(0));
        System.out.println("Nhap vao 1 so: ");
        int n = scan.nextInt();
        System.out.println(sol.reverse(n));


    }
}