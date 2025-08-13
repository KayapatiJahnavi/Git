class Solution {
    public int minSoldiers(int[] arr, int k) {
        // code here
        int n = arr.length;
        int needLucky = (n + 1) / 2; // ceil(n / 2)
        int alreadyLucky = 0;
        List<Integer> costList = new ArrayList<>();

        // Step 1 & 3: Count already lucky and calculate costs
        for (int soldiers : arr) {
            if (soldiers % k == 0) {
                alreadyLucky++;
            } else {
                int cost = k - (soldiers % k);
                costList.add(cost);
            }
        }

        // Step 2: If enough lucky troops already
        if (alreadyLucky >= needLucky) {
            return 0;
        }

        // Step 4: Sort costs
        Collections.sort(costList);

        // Step 5: Add smallest costs until we have enough lucky troops
        int totalAdded = 0;
        int troopsToMakeLucky = needLucky - alreadyLucky;

        for (int i = 0; i < troopsToMakeLucky && i < costList.size(); i++) {
            totalAdded += costList.get(i);
        }

        return totalAdded;
    }
}
