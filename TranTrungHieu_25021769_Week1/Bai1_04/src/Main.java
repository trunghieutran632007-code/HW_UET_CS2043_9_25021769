import java.util.Scanner;

public class Main {
    public static void main (String[] args){
        Solution sol = new Solution();

        Scanner sc = new Scanner(System.in);
        System.out.println(sol.fibonacci(-1));
        System.out.println(sol.fibonacci(10));
        System.out.println(sol.fibonacci(100));
        System.out.println("Nhap vao 1 so n: ");
        long n = sc.nextLong();

        System.out.println(sol.fibonacci(n));


    }
    
}
