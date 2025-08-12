class Solution {
    private static final int MOD = 1_000_000_007;
    public int numberOfWays(int n, int x) {
        // Build list of powers i^x <= n
        List<Integer> powers = new ArrayList<>();
        for (int base = 1; ; base++) {
            long p = 1;
            for (int i = 0; i < x; i++) {
                p *= base;
                if (p > n) break;
            }
            if (p > n) break;
            powers.add((int) p);
        }

        int[] dp = new int[n + 1];
        dp[0] = 1;

        // 0/1 Knapsack to ensure unique integers
        for (int power : powers) {
            for (int sum = n; sum >= power; sum--) {
                dp[sum] += dp[sum - power];
                if (dp[sum] >= MOD) dp[sum] -= MOD;
            }
        }

        return dp[n];
    }
}
