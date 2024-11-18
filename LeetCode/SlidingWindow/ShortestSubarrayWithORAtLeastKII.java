package SlidingWindow;

import java.util.stream.IntStream;

public class ShortestSubarrayWithORAtLeastKII {
    public static void main(String[] args) {
        var ob = new ShortestSubarrayWithORAtLeastKII();
        System.out.println(ob.minimumSubarrayLength(new int[]{1, 2, 3}, 2) == 1);
        System.out.println(ob.minimumSubarrayLength(new int[]{2, 1, 8}, 10) == 3);
        System.out.println(ob.minimumSubarrayLength(new int[]{1, 2}, 0) == 1);
    }

    public static final int[] MASKS = new int[32];

    static {
        MASKS[0] = 1;
        IntStream.range(1, 32)
                 .forEach(i -> MASKS[i] = MASKS[i - 1] << 1);
    }

    public int minimumSubarrayLength(int[] nums, int k) {
        var left = 0;
        var setBitCount = new int[32];
        var minLength = Integer.MAX_VALUE;

        for (var right = 0; right < nums.length; right++) {
            updateSetBitCount(setBitCount, nums[right], true);

            for (; left <= right && toDecimal(setBitCount) >= k; left++) {
                minLength = Math.min(minLength, right - left + 1);
                updateSetBitCount(setBitCount, nums[left], false);
            }
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    private void updateSetBitCount(int[] setBitCount, int num, boolean add) {
        for (var i = 0; i < setBitCount.length; i++, num >>= 1) {
            if ((num & 1) == 0)
                continue;
            if (add)
                setBitCount[i]++;
            else
                setBitCount[i]--;
        }
    }

    private int toDecimal(int[] setBitCount) {
        return IntStream.range(0, 32)
                        .filter(i -> setBitCount[i] != 0)
                        .map(i -> MASKS[i])
                        .reduce(0, (a, b) -> a | b);
    }
}
