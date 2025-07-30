class Solution {
    public int cntSubarrays(int[] arr, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // For subarrays starting from index 0
        int prefixSum = 0, count = 0;

        for (int num : arr) {
            prefixSum += num;

            // Check if there is a prefixSum that when removed leaves sum k
            if (map.containsKey(prefixSum - k)) {
                count += map.get(prefixSum - k);
            }

            // Update map with current prefixSum
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }

        return count;
    }
        
    }
