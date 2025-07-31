import java.util.*;

class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Step 1: Sort intervals by starting point
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> merged = new ArrayList<>();
        int[] current = intervals[0];
        merged.add(current);

        for (int[] interval : intervals) {
            int currentEnd = current[1];
            int nextStart = interval[0];
            int nextEnd = interval[1];

            // Overlap case → merge
            if (nextStart <= currentEnd) {
                current[1] = Math.max(currentEnd, nextEnd);
            } else {
                // No overlap → add new interval
                current = interval;
                merged.add(current);
            }
        }

        return merged.toArray(new int[merged.size()][]);
    }
}
