import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Solution: tinh so Fibonacci thu n bang phuong phap dynamic programming
 * voi xu ly tran so (Long.MAX_VALUE) khi n >= 93.
 */
public class Solution {

    private static final Logger log = LoggerFactory.getLogger(Solution.class);

    public static long fibonacci(long n) {
        log.debug("fibonacci called with n={}", n);

        if (n < 0) {
            log.warn("Negative input n={}, returning -1 as error sentinel", n);
            return -1;
        }
        if (n == 0) return 0;
        if (n == 1) return 1;

        // F(93) tran khoi long, tra ve Long.MAX_VALUE de bao hieu overflow
        if (n >= 93) {
            log.info("n={} >= 93, result overflows long. Returning Long.MAX_VALUE.", n);
            return Long.MAX_VALUE;
        }

        long[] dp = new long[(int) n + 1];
        dp[0] = 0;
        dp[1] = 1;

        for (int i = 2; i <= (int) n; i++) {
            if (dp[i - 1] > Long.MAX_VALUE - dp[i - 2]) {
                dp[i] = Long.MAX_VALUE;
            } else {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
        }

        log.debug("fibonacci({}) = {}", n, dp[(int) n]);
        return dp[(int) n];
    }
}
