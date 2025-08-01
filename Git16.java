class Solution {
    public int[] plusOne(int[] digits) {

        int n = digits.length;

        // Traverse the array from end to start
        for (int i = n - 1; i >= 0; i--) {
            // If the digit is less than 9, just add 1 and return
            if (digits[i] < 9) {
                digits[i]++;
                return digits;
            }

            // If digit is 9, set it to 0 and continue (carry over)
            digits[i] = 0;
        }

        // If all digits were 9, we need a new array like [1, 0, 0, ..., 0]
        int[] result = new int[n + 1];
        result[0] = 1; // Rest are already 0 by default
        return result;
    }
}

    
