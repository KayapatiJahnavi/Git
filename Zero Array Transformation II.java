class Solution {
    public int minZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;

        for (int k = 0; k < queries.length; k++) {
            int li = queries[k][0];
            int ri = queries[k][1];
            int val = queries[k][2];

            for (int i = li; i <= ri; i++) {
                nums[i] = Math.max(0, nums[i] - val);
            }

            if (allZero(nums)) {
                return k + 1; // because k is 0-indexed
            }
        }

        return -1;
    }

    private boolean allZero(int[] nums) {
        for (int num : nums) {
            if (num != 0) return false;
        }
        return true;
    }
}
