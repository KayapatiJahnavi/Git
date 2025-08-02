class Solution {
    public int mySqrt(int x) {
        
        if (x < 2) return x;

        int left = 1, right = x / 2;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Use long to avoid overflow when multiplying
            long square = (long) mid * mid;

            if (square == x) {
                return mid;
            } else if (square < x) {
                ans = mid;         // store result and search right
                left = mid + 1;
            } else {
                right = mid - 1;   // search left
            }
        }

        return ans;
    }
}

    
