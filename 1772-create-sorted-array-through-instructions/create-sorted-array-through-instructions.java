class Solution {
    static class Fenwick {
        int[] tree;

        Fenwick(int n) {
            tree = new int[n + 1];
        }

        void update(int i, int val) {
            while (i < tree.length) {
                tree[i] += val;
                i += i & -i;
            }
        }

        int query(int i) {
            int sum = 0;
            while (i > 0) {
                sum += tree[i];
                i -= i & -i;
            }
            return sum;
        }
    }

    public int createSortedArray(int[] instructions) {
        int MOD = 1_000_000_007;

        int max = 0;
        for (int x : instructions) {
            max = Math.max(max, x);
        }

        Fenwick bit = new Fenwick(max);

        long ans = 0;

        for (int i = 0; i < instructions.length; i++) {
            int x = instructions[i];

            
            int smaller = bit.query(x - 1);

            // Count numbers strictly greater than x
            int greater = i - bit.query(x);

            ans += Math.min(smaller, greater);

            bit.update(x, 1);
        }

        return (int)(ans % MOD);
    }
}
