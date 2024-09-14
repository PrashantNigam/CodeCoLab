package Arrays;

import java.util.Arrays;

public class LongestSubarrayWithMaximumBitwiseAND {
    public static void main(String[] args) {
        var ob = new LongestSubarrayWithMaximumBitwiseAND();
        System.out.println(ob.longestSubarray(new int[]{1, 2, 3, 3, 2, 2}) == 2);
        System.out.println(ob.longestSubarray(new int[]{1, 2, 3, 4}) == 1);
    }

    public int longestSubarray(int[] nums) {
        var max = Arrays.stream(nums)
                        .max()
                        .getAsInt();

        return lengthOfLongestSubsequentOccurencesOfItem(nums, max);
    }

    private static int lengthOfLongestSubsequentOccurencesOfItem(int[] nums, int item) {
        var size = 0;
        var maxSize = 0;
        
        for (var num : nums)
            if (num == item) {
                size++;
            } else {
                maxSize = Math.max(maxSize, size);
                size = 0;
            }

        return Math.max(maxSize, size);
    }
}
