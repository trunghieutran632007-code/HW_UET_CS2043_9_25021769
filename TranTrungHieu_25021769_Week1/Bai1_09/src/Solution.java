import java.util.Scanner;
public class Solution{
    public static int sumOfDigits(int n) {
        int sum = 0;
        while (n != 0) {
            sum += n % 10;
            n = n / 10;
        }
        return sum;
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Solution sol = new Solution();
        System.out.println(sol.sumOfDigits(123));
        System.out.println(sol.sumOfDigits(-123));
        System.out.println(sol.sumOfDigits(0));
        System.out.println("Nhap vao 1 so: ");
        int n = sc.nextInt();
        System.out.println(sol.sumOfDigits(n));



    }
}