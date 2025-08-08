class Solution {
    public int maximumCount(int[] nums) {
        
        int n = nums.length;

        // Find the first index of a positive number
        int posIndex = firstGreaterThanZero(nums);

        // Find the first index of a non-negative number (>= 0)
        int nonNegIndex = firstGreaterOrEqualZero(nums);

        int posCount = n - posIndex;
        int negCount = nonNegIndex;

        return Math.max(posCount, negCount);
    }

    // Binary search to find first index where nums[i] > 0
    private int firstGreaterThanZero(int[] nums) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] > 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // Binary search to find first index where nums[i] >= 0
    private int firstGreaterOrEqualZero(int[] nums) {
        int low = 0, high = nums.length;
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] >= 0) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }
}
