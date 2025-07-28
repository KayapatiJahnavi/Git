class Solution {
    // Euclidean algorithm to compute gcd
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public int countCoprimePairs(int[] arr) {
        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (gcd(arr[i], arr[j]) == 1) {
                    count++;
                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr = {2, 3, 4, 5};
        System.out.println(sol.countCoprimePairs(arr)); // Output: 5
    }
}
