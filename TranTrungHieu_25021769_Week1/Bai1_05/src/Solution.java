import java.util.Scanner;

public class Solution {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        Solution sol = new Solution();
        System.out.println(sol.gcd(1071, 462));
        System.out.println(sol.gcd(10, 10));
        System.out.println(sol.gcd(17, 13));
        System.out.println("Nhap vao so dau tien: ");
        System.out.println("Nhap vao so thu 2: ");
        int a = scan.nextInt();
        int b = scan.nextInt();
        System.out.println(sol.gcd(a,b));

    }
    
}
