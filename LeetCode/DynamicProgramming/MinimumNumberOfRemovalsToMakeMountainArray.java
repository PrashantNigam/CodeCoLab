package DynamicProgramming;

import java.util.Arrays;

// Microsoft
public class MinimumNumberOfRemovalsToMakeMountainArray {
    public static void main(String[] args) {
        var ob = new MinimumNumberOfRemovalsToMakeMountainArray();
        System.out.println(ob.minimumMountainRemovals(new int[]{1, 3, 1}) == 0);
        System.out.println(ob.minimumMountainRemovals(new int[]{2, 1, 1, 5, 6, 2, 3, 1}) == 3);
    }

    public int minimumMountainRemovals(int[] nums) {
        var maxMountainLength = 0;
        var lis = longestIncreasingSubsequence(nums);
        var lds = longestDecreasingSubsequence(nums);

        for (var i = 0; i < nums.length; i++) {
            if (lis[i] < 2 || lds[i] < 2)
                continue;

            var lengthOfMountainWithPeakHere = lis[i] + lds[i] - 1;
            maxMountainLength = Math.max(maxMountainLength, lengthOfMountainWithPeakHere);
        }

        return nums.length - maxMountainLength;
    }

    /**
     * @return array of lengths of longest increasing subsequence ending at each index
     */
    private int[] longestIncreasingSubsequence(int[] nums) {
        var dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (var i = 0; i < nums.length; i++)
            for (var j = 0; j < i; j++)
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);

        return dp;
    }

    /**
     * @return array of lengths of longest decreasing subsequence starting at each index
     */
    private int[] longestDecreasingSubsequence(int[] nums) {
        var dp = new int[nums.length];
        Arrays.fill(dp, 1);

        for (var i = nums.length - 1; i >= 0; i--)
            for (var j = nums.length - 1; j > i; j--)
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[i], 1 + dp[j]);

        return dp;
    }
}
