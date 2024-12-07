package SlidingWindow.FixedSize;

import java.util.HashMap;
import java.util.Map;

public class MaximumSumOfDistinctSubarraysWithLengthK {
    public static void main(String[] args) {
        var ob = new MaximumSumOfDistinctSubarraysWithLengthK();
        System.out.println(ob.maximumSubarraySum(new int[]{1, 5, 4, 2, 9, 9, 9}, 3) == 15);
        System.out.println(ob.maximumSubarraySum(new int[]{4, 4, 4}, 3) == 0);
        System.out.println(ob.maximumSubarraySum(new int[]{1, 1, 1, 7, 8, 9}, 3) == 24);
    }

    public long maximumSubarraySum(int[] nums, int k) {
        var sum = 0L;
        var left = 0;
        var maxSum = 0L;
        var distinct = 0;
        var map = new HashMap<Integer, Integer>();

        for (var right = 0; right < k; right++) {
            sum += nums[right];

            if (incVal(map, nums[right]) == 1)
                distinct++;
        }

        if (distinct == k)
            maxSum = sum;

        for (var right = k; right < nums.length; right++) {
            sum += nums[right] - nums[left];

            if (decVal(map, nums[left]) == 0)
                distinct--;
            if (incVal(map, nums[right]) == 1)
                distinct++;
            if (distinct == k)
                maxSum = Math.max(maxSum, sum);

            left++;
        }

        return maxSum;
    }

    private int incVal(Map<Integer, Integer> frequency, int key) {
        return frequency.compute(key, (_, v) -> v == null ? 1 : ++v);
    }

    private int decVal(Map<Integer, Integer> frequency, int key) {
        return frequency.compute(key, (_, v) -> v == null ? 0 : --v);
    }
}
