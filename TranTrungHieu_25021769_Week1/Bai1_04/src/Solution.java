// Import your library
// Do not change the name of the Solution class
public class Solution {

    public static long fibonacci(long n) {
        if (n < 0) return -1;
        if (n == 0) return 0;
        if (n == 1) return 1;
        // n >=93 --> Long.MAX_VALUE
        if (n >= 93) return Long.MAX_VALUE;

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

        return dp[(int) n];
    }
}