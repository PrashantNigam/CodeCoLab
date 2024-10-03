package PrefixSum;

import java.util.HashMap;
import java.util.Map;

// Amazon
public class MakeSumDivisibleByP {
    public static void main(String[] args) {
        var ob = new MakeSumDivisibleByP();
        System.out.println(ob.minSubarray(new int[]{3, 1, 4, 2}, 6) == 1);
        System.out.println(ob.minSubarray(new int[]{6, 3, 5, 2}, 9) == 2);
        System.out.println(ob.minSubarray(new int[]{1, 2, 3}, 3) == 0);
    }

    public int minSubarray(int[] nums, int p) {
        var total = sumMod(nums, p);

        if (total == 0)
            return 0;

        var prefix = 0;
        var minLength = nums.length;
        var prefixSumToIndex = new HashMap<>(Map.of(0, -1));

        for (var right = 0; right < nums.length; right++) {
            prefix = Math.floorMod(prefix + nums[right], p);
            var target = Math.floorMod(prefix - total, p);

            if (prefixSumToIndex.containsKey(target)) {
                var left = prefixSumToIndex.get(target);
                var subArrayLength = right - left;
                minLength = Math.min(minLength, subArrayLength);
            }

            prefixSumToIndex.put(prefix, right);
        }

        return minLength == nums.length ? -1 : minLength;
    }

    private int sumMod(int[] nums, int p) {
        var sum = 0;

        for (var num : nums)
            sum = Math.floorMod(sum + num, p);

        return sum;
    }
}
