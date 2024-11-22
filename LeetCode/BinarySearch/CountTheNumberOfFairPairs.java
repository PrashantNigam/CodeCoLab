package BinarySearch;

import java.util.Arrays;

public class CountTheNumberOfFairPairs {
    public static void main(String[] args) {
        var ob = new CountTheNumberOfFairPairs();
        System.out.println(ob.countFairPairs(new int[]{0, 1, 7, 4, 4, 5}, 3, 6) == 6);
        System.out.println(ob.countFairPairs(new int[]{1, 7, 9, 2, 5}, 11, 11) == 1);
        System.out.println(ob.countFairPairs(new int[]{0, 0, 0, 0, 0, 0}, 0, 0) == 15);
    }

    public long countFairPairs(int[] nums, int lower, int upper) {
        var pairs = 0L;
        Arrays.sort(nums);

        for (var i = 0; i < nums.length; i++) {
            var highIndex = bound(nums, i + 1, upper - nums[i], false);
            var lowIndex = bound(nums, i + 1, lower - nums[i], true);
            pairs += highIndex - lowIndex;
        }

        return pairs;
    }

    /**
     * @param nums  The sorted array in which to search.
     * @param low   The starting index from which the search will begin.
     * @param key   The value to search for.
     * @param lower flag for lower bound
     * @return if lower is true then return lower bound i.e. the index of the first element ≥ key,
     *         if lower is false return the upper bound i.e. index of the last element < key
     */
    private int bound(int[] nums, int low, int key, boolean lower) {
        var high = nums.length;

        while (low < high) {
            var mid = low + (high - low >> 1);
            var condition = lower ? nums[mid] < key : nums[mid] <= key;

            if (condition)
                low = mid + 1;
            else
                high = mid;
        }

        return low;
    }
}
