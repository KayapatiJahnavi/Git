class Solution {
    int cntCoprime(int[] arr) {
        int n = arr.length;
        int maxVal = 10000; // since arr[i] ≤ 10^4
        
        // Frequency array
        int[] freq = new int[maxVal + 1];
        for (int num : arr) {
            freq[num]++;
        }

        // Count multiples for each g
        int[] multiples = new int[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            for (int multiple = g; multiple <= maxVal; multiple += g) {
                multiples[g] += freq[multiple];
            }
        }

        // Use inclusion-exclusion to find pairs with gcd > 1
        long[] gcdPairs = new long[maxVal + 1];
        for (int g = maxVal; g >= 1; g--) {
            long count = (long) multiples[g] * (multiples[g] - 1) / 2; // total pairs divisible by g
            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                count -= gcdPairs[multiple]; // remove pairs with gcd multiple of g
            }
            gcdPairs[g] = count;
        }

        // Total pairs
        long totalPairs = (long) n * (n - 1) / 2;
        long nonCoprimePairs = 0;
        for (int g = 2; g <= maxVal; g++) {
            nonCoprimePairs += gcdPairs[g];
        }

        long coprimePairs = totalPairs - nonCoprimePairs;
        return (int) coprimePairs;
    }
}
