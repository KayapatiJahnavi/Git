class Solution {
    private int n;
    private int[] seg;
    private int[] baskets;

    public int numOfUnplacedFruits(int[] fruits, int[] baskets) {
        this.n = baskets.length;
        this.baskets = baskets;
        this.seg = new int[4 * n];
        build(1, 0, n - 1);

        int unplaced = 0;
        for (int fruit : fruits) {
            int idx = query(1, 0, n - 1, fruit);
            if (idx == -1) {
                unplaced++;
            } else {
                update(1, 0, n - 1, idx, Integer.MIN_VALUE);
            }
        }
        return unplaced;
    }

    // Build segment tree storing max capacity in segment
    private void build(int node, int l, int r) {
        if (l == r) {
            seg[node] = baskets[l];
            return;
        }
        int mid = (l + r) >> 1;
        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);
        seg[node] = Math.max(seg[node * 2], seg[node * 2 + 1]);
    }

    // Query leftmost basket index >= fruit capacity
    private int query(int node, int l, int r, int fruit) {
        if (seg[node] < fruit) {
            return -1;
        }
        if (l == r) {
            return l;
        }
        int mid = (l + r) >> 1;
        if (seg[node * 2] >= fruit) {
            return query(node * 2, l, mid, fruit);
        } else {
            return query(node * 2 + 1, mid + 1, r, fruit);
        }
    }

    // Mark basket used by setting its value to MIN
    private void update(int node, int l, int r, int idx, int val) {
        if (l == r) {
            seg[node] = val;
            return;
        }
        int mid = (l + r) >> 1;
        if (idx <= mid) {
            update(node * 2, l, mid, idx, val);
        } else {
            update(node * 2 + 1, mid + 1, r, idx, val);
        }
        seg[node] = Math.max(seg[node * 2], seg[node * 2 + 1]);
    }
}

    
