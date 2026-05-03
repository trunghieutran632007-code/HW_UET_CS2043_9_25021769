import java.util.Scanner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        log.info("Application starting");

        System.out.println("fibonacci(-1)  = " + Solution.fibonacci(-1));
        System.out.println("fibonacci(10)  = " + Solution.fibonacci(10));
        System.out.println("fibonacci(100) = " + Solution.fibonacci(100));

        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Nhap vao 1 so n: ");
            if (sc.hasNextLong()) {
                long n = sc.nextLong();
                long result = Solution.fibonacci(n);
                System.out.println("fibonacci(" + n + ") = " + result);
            } else {
                log.warn("No valid long input received from stdin");
            }
        }

        log.info("Application finished");
    }
}
