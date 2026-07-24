class Solution {
    public int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int[] result = new int[k];

        for (int i = Math.max(0, k - nums2.length); i <= Math.min(k, nums1.length); i++) {
            int[] part1 = maxSubsequence(nums1, i);
            int[] part2 = maxSubsequence(nums2, k - i);

            int[] merged = merge(part1, part2);

            if (greater(merged, 0, result, 0)) {
                result = merged;
            }
        }

        return result;
    }

    private int[] maxSubsequence(int[] nums, int k) {
        int[] stack = new int[k];
        int top = -1;
        int remove = nums.length - k;

        for (int num : nums) {
            while (top >= 0 && remove > 0 && stack[top] < num) {
                top--;
                remove--;
            }

            if (top + 1 < k) {
                stack[++top] = num;
            } else {
                remove--;
            }
        }

        return stack;
    }

    private int[] merge(int[] nums1, int[] nums2) {
        int[] result = new int[nums1.length + nums2.length];

        int i = 0, j = 0;

        for (int k = 0; k < result.length; k++) {
            if (greater(nums1, i, nums2, j)) {
                result[k] = nums1[i++];
            } else {
                result[k] = nums2[j++];
            }
        }

        return result;
    }

    private boolean greater(int[] nums1, int i, int[] nums2, int j) {
        while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
            i++;
            j++;
        }

        if (j == nums2.length) {
            return true;
        }

        if (i == nums1.length) {
            return false;
        }

        return nums1[i] > nums2[j];
    }
}
