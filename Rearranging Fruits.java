class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        Map<Integer, Integer> freq = new HashMap<>();
        int n = basket1.length;

        for (int x : basket1) freq.put(x, freq.getOrDefault(x, 0) + 1);
        for (int x : basket2) freq.put(x, freq.getOrDefault(x, 0) - 1);

        List<Integer> extra = new ArrayList<>();
        int minElem = Integer.MAX_VALUE;

        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            int fruit = e.getKey();
            int count = e.getValue();

            if (count % 2 != 0) return -1; // can't balance

            // store only excess elements (half of them for swap)
            for (int i = 0; i < Math.abs(count) / 2; i++) {
                extra.add(fruit);
            }

            minElem = Math.min(minElem, fruit);
        }

        Collections.sort(extra);

        long cost = 0;
        for (int i = 0; i < extra.size() / 2; i++) {
            cost += Math.min(extra.get(i), 2 * minElem);
        }

        return cost;
    }
}

