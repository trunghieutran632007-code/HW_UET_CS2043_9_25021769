
import java.util.Scanner;

class Solution{
    public static boolean isPrime(int n){
        if (n < 2) return false;
        for (int i = 2; i <= Math.sqrt(n); i++ ){
            if (n % i == 0) return false;

        }
        return true;
    }

    public static void main (String[] args){
        Scanner sc = new Scanner(System.in);

        Solution sol = new Solution();
        System.out.println(sol.isPrime(-10));
        System.out.println(sol.isPrime(2));
        System.out.println(sol.isPrime(1));
        System.out.println(sol.isPrime(3));
        System.out.println(sol.isPrime(20));
        System.out.println(sol.isPrime(53));

        System.out.println("Nhap vao mot chu so: ");
        int n = sc.nextInt();
        System.out.println(sol.isPrime(n));
    }
    

}