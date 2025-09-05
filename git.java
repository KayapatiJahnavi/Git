class Solution {
    public int makeTheIntegerZero(int num1, int num2) {
        
        for (int op = 1; op <= 60; op++) {
            long rem = 1L * num1 - 1L * op * num2;
            if (rem < op) continue;
            int pop = Long.bitCount(rem);
            if (pop <= op && op <= rem) return op;
        }
        return -1;
    }
}
