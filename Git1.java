class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> set1 = new HashSet<>();
        Set<Integer> result = new HashSet<>();

        // Add all elements of nums1 to set1
        for (int num : nums1) {
            set1.add(num);
        }

        // Check if elements of nums2 are also in set1
        for (int num : nums2) {
            if (set1.contains(num)) {
                result.add(num); // add to result set (keeps elements unique)
            }
        }

        // Convert set to int[]
        int[] output = new int[result.size()];
        int i = 0;
        for (int num : result) {
            output[i++] = num;
        }

        return output;
    }
}
    
