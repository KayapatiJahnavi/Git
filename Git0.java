class Solution {
    public long minOperations(int[][] queries) {
        
        long totalOperations = 0;
                                   
        
        for (int[] query : queries) { 
            int l = query[0]; // Extracting the left border of the range for the current query.
            int r = query[1]; // Extracting the right border of the range for the current query.
            
            
            totalOperations += calculateOperations(l, r); 
        }
        
        return totalOperations; // We return the accumulated total amount of transactions.
    }
    
    private long calculateOperations(int l, int r) {
        long totalSteps = 0; 
        
        int maxSteps = 0;
        for (int step = 0; step <= 16; step++) { 
        /*
The main idea: These formulas create ranges of numbers that require the same number of operations to reset.

Imagine that we are looking at numbers in some kind of “quaternary” representation. The operation of dividing by 4 is as if we are “shifting” a number in this system.

1. lowerBound = (1L << (2 * step))

    1L << n: As we have already discussed, this is 2^n.
    2 * step: We multiply step by 2.
    lowerBound = 2^(2 * step): This is the same as 4^step.

What does it mean?

    When step = 0, lowerBound = 4^0 = 1.
    When step = 1, lowerBound = 4^1 = 4.
    When step = 2, lowerBound = 4^2 = 16.
    When step = 3, lowerBound = 4^3 = 64.
    …
    When step = 15, lowerBound = 4^15 = 2^30.

These lowerbounds define the beginning of blocks of numbers.

2. upperBound = (1L << (2 * (step + 1))) - 1

    2 * (step + 1): This is 2*step + 2.
    1L << (2 * (step + 1)): This is 2^(2*step + 2).
    - 1: Subtract one.

What does it mean?

    2^(2*step + 2) in binary, this is 1, followed by 2*step + 2 zeros.
    Subtracting one from such a number gives a number consisting of 2*step + 2 units.

Examples:

    When step = 0:
lowerBound = 4^0 = 1
        upperBound = 2^(2*0 + 2) - 1 = 2^2 - 1 = 4 - 1 = 3.
        Range: [1, 3].

    When step = 1:
        lowerBound = 4^1 = 4
        upperBound = 2^(2*1 + 2) - 1 = 2^4 - 1 = 16 - 1 = 15.
        Range: [4, 15].

    When step = 2:
        lowerBound = 4^2 = 16
        upperBound = 2^(2*2 + 2) - 1 = 2^6 - 1 = 64 - 1 = 63.
        Range: [16, 63].

    When step = 3:
lowerBound = 4^3 = 64
        upperBound = 2^(2*3 + 2) - 1 = 2^8 - 1 = 256 - 1 = 255.
Range: [64, 255].

Why such ranges?

These ranges [4^step, 4^(step+1)-1] correspond exactly to the numbers that require step +1 division operations by 4 to become zero.

    Numbers from 1 to 3 require 1 operation. step=0, step+1=1.
    Numbers from 4 to 15 require 2 operations. step=1, step+1=2.
    Numbers from 16 to 63 require 3 operations. step=2, step+1=3.
*/
            long lowerBound = (1L << (2 * step));
            long upperBound = (1L << (2 * (step + 1))) - 1;
            
            // Check to exit the loop if the lower boundary of the block is already greater than the right boundary of the request.
            // This means that this block and all subsequent blocks no longer overlap with the range [l, r].
            if (lowerBound > r) break; 
            

/*
Imagine what you have:

    Your to-do list (your request): You have numbers from l to r. You want to calculate how many operations are needed for ALL these numbers.
    Ready-made “bundles” of operations: You have pre-known “bundles” of numbers for which you know how many operations are needed. For example:
        Pack 1: numbers [1, 3]. EACH number in this bundle requires 1 operation.
        Pack 2: numbers [4, 15]. For EACH number in this bundle, 2 operations are needed.
        Pack 3: numbers [16, 63]. EACH number in this bundle requires 3 operations.
        Etc.

Now look at the lines:
java

long rangeStart = Math.max((long)l, lowerBound);
long rangeEnd = Math.min((long)r, upperBound);

    lowerBound and upperBound: These are the boundaries of one of your “bundles”. For example, now we are looking at the bundle [4, 15] (where lowerBound=4, upperBound=15).
(long)l and (long)r: These are the boundaries of your to-do list. 
*/
            long rangeStart = Math.max((long)l, lowerBound);
            long rangeEnd = Math.min((long)r, upperBound);
            
            // If `RangeStart > RangeEnd', it means that the current block is [lowerBound, upperBound]
            // does not overlap with the query range [l, r] (or the intersection is empty).
            // Skip this block and move on to the next one.
            if (rangeStart > rangeEnd) continue; 
            
            // `count = rangeEnd - rangeStart + 1;`
            // Calculate the number of numbers in the actual intersection [RangeStart, RangeEnd].
            // This is the number of numbers that fall into the current `step` block And in the query range `[l, r]`.
            long count = rangeEnd - rangeStart + 1; 
            
            // `totalSteps += count * (step + 1);`
            // `step + 1' is the number of operations required by ONE number from the current block.
            // `count` is the number of such numbers in our query.
            // `count * (step + 1)` is the TOTAL number of "division steps" for ALL the numbers IN THIS BLOCK.,
            // which were included in our request.
            // `totalSteps' accumulates the total number of single division steps for all numbers in the range [l, r].
            totalSteps += count * (step + 1);
            
            // `maxSteps = Math.max(maxSteps, step + 1);`
            // `maxSteps' stores the maximum number of operations required by a SINGLE number.
            // `step + 1' is the number of operations for the numbers in the current block.
            // We are looking for the maximum `step + 1` among all blocks that intersect with `[l, r]`.
            // This is needed for the final calculation of `Math.max(maxSteps, (totalSteps + 1) / 2)'.
            maxSteps = Math.max(maxSteps, step + 1);
        }
        
        // `return Math.max(maxSteps, (totalSteps + 1) / 2);`
        // This is the final calculation of the minimum number of operations for the current query [l, r].
        //
// `(totalSteps + 1) / 2` is one score.
        // `totalSteps` is the total number of "single division steps" that need to be done for all numbers.
        /// Since each operation `(a, b) -> (a/4, b/4)` performs TWO such "steps" (one each for `a` and `b`),
// the minimum number of operations based on the total number of steps will be approximately `totalSteps/2`.
        // `+ 1` and integer division `/ 2` are a way to round the result up (for example, 3 steps -> (3+1)/2 = 2 operations).
        //
// `maxSteps' is a different estimate.
        // `maxSteps' is the maximum number of operations required by a SINGLE number.
        // If there is a number that needs 5 operations, then even if all the other numbers are very easy,
        // we can't do less than 5 operations anyway.
        //
// `Math.max(maxSteps, (totalSteps + 1) / 2)` - we take the maximum of these two estimates.
        // This ensures that we take into account both constraints:
        // 1. The total number of operations required for all the "steps".
        // 2. The minimum number of operations required for the heaviest number.
        // Thus, we get the minimum total number of operations for the range [l, r].
        return Math.max(maxSteps, (totalSteps + 1) / 2);
    }
}
