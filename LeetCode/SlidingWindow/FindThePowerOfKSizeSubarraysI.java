package SlidingWindow;

import java.util.Arrays;

public class FindThePowerOfKSizeSubarraysI {
    public static void main(String[] args) {
        var ob = new FindThePowerOfKSizeSubarraysI();
        System.out.println(Arrays.equals(ob.resultsArray(new int[]{1, 2, 3, 4, 3, 2, 5}, 3), new int[]{3, 4, -1, -1, -1}));
        System.out.println(Arrays.equals(ob.resultsArray(new int[]{2, 2, 2, 2, 2}, 4), new int[]{-1, -1}));
        System.out.println(Arrays.equals(ob.resultsArray(new int[]{3, 2, 3, 2, 3, 2}, 2), new int[]{-1, 3, -1, 3, -1}));
        System.out.println(Arrays.equals(ob.resultsArray(new int[]{1, 30, 31, 32}, 3), new int[]{-1, 32}));
    }

    public int[] resultsArray(int[] nums, int k) {
        var left = 1;
        var results = new int[nums.length - k + 1];
        var consecutiveCount = consecutiveCountOfFirstWindow(nums, k);
        Arrays.fill(results, -1);

        if (consecutiveCount == k)
            results[0] = nums[k - 1];

        for (var right = k; right < nums.length; right++) {
            if (nums[right] - nums[right - 1] == 1)
                consecutiveCount++;
            else
                consecutiveCount = 1;
            if (consecutiveCount >= k)
                results[left] = nums[right];

            left++;
        }

        return results;
    }

    private int consecutiveCountOfFirstWindow(int[] nums, int k) {
        var consecutiveCount = 1;

        for (var i = 1; i < k; i++)
            if (nums[i] - nums[i - 1] == 1)
                consecutiveCount++;
            else
                consecutiveCount = 1;

        return consecutiveCount;
    }
}
