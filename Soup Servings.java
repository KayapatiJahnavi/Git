
        class Solution {
    private Map<String, Double> memo = new HashMap<>();

    public double soupServings(int n) {
        if (n >= 4800) return 1.0; // Optimization: very close to 1 for large n

        return dfs(n, n);
    }

    private double dfs(int a, int b) {
        if (a <= 0 && b <= 0) return 0.5;
        if (a <= 0) return 1.0;
        if (b <= 0) return 0.0;

        String key = a + "," + b;
        if (memo.containsKey(key)) return memo.get(key);

        double prob = 0.25 * (
            dfs(a - 100, b) +
            dfs(a - 75, b - 25) +
            dfs(a - 50, b - 50) +
            dfs(a - 25, b - 75)
        );

        memo.put(key, prob);
        return prob;
    }
}
