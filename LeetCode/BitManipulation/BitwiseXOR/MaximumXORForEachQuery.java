package BitManipulation.BitwiseXOR;

import java.util.Arrays;

public class MaximumXORForEachQuery {
    public static void main(String[] args) {
        var ob = new MaximumXORForEachQuery();
        System.out.println(Arrays.equals(ob.getMaximumXor(new int[]{0, 1, 1, 3}, 2), new int[]{0, 3, 2, 3}));
        System.out.println(Arrays.equals(ob.getMaximumXor(new int[]{2, 3, 4, 7}, 3), new int[]{5, 2, 6, 5}));
        System.out.println(Arrays.equals(ob.getMaximumXor(new int[]{0, 1, 2, 2, 5, 7}, 3), new int[]{4, 3, 6, 4, 6, 7}));
    }

    public int[] getMaximumXor(int[] nums, int maximumBit) {
        var j = nums.length - 1;
        var mask = (1 << maximumBit) - 1;
        var answer = new int[nums.length];
        var xor = Arrays.stream(nums)
                        .reduce(0, (a, b) -> a ^ b);

        for (var i = 0; i < nums.length; i++, j--) {
            answer[i] = xor ^ mask;
            xor ^= nums[j];
        }

        return answer;
    }
}
