import java.util.Scanner;
public class Solution {
    public boolean isPalindrome(int n) {
        if (n < 0) {
            return false;
        }

        if (n != 0 && n % 10 == 0) {
            return false;
        }

        int original = n;
        long reversed = 0;

        while (n > 0) {
            int digit = n % 10;
            reversed = reversed * 10 + digit;
            n /= 10;
        }

        // Kiểm tra xem số đã đảo ngược có giống số ban đầu không
        return original == (int) reversed;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        Scanner sc = new Scanner(System.in);
        System.out.println(sol.isPalindrome(121));
        System.out.println(sol.isPalindrome(-121));
        System.out.println(sol.isPalindrome(-101));
        System.out.println(sol.isPalindrome(-1221));
        System.out.println(sol.isPalindrome(1234567));
        System.out.println("Nhap vao 1 so: ");
        int n = sc.nextInt();
        System.out.println(sol.isPalindrome(n));
    }
}