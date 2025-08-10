class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int flipCount = 0;
        int operations = 0;
        int[] isFlipped = new int[n];

        for (int i = 0; i < n; i++) {
            if (i >= 3) {
                flipCount -= isFlipped[i - 3];
            }
            if ((nums[i] + flipCount) % 2 == 0) {
                if (i + 3 > n) {
                    return -1;
                }
                operations++;
                flipCount++;
                isFlipped[i] = 1;
            }
        }
        return operations;
    }
}
