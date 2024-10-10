package Stack.Monotonic;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// Google
public class MaximumWidthRamp {
    public static void main(String[] args) {
        var ob = new MaximumWidthRamp();
        System.out.println(ob.maxWidthRamp(new int[]{6, 0, 8, 2, 1, 5}) == 4);
        System.out.println(ob.maxWidthRamp(new int[]{9, 8, 1, 0, 1, 9, 4, 0, 4, 1}) == 7);
    }

    public int maxWidthRamp(int[] nums) {
        var maxRamp = 0;
        var stack = toDecreasingStack(nums);

        for (var i = nums.length - 1; i >= 0; i--)
            maxRamp = Math.max(maxRamp, rampWidth(nums, i, stack));

        return maxRamp;
    }

    private Deque<Integer> toDecreasingStack(int[] nums) {
        var stack = new ArrayDeque<>(List.of(0));

        for (var i = 1; i < nums.length; i++)
            if (nums[i] < nums[stack.peek()])
                stack.push(i);

        return stack;
    }

    private int rampWidth(int[] nums, int i, Deque<Integer> stack) {
        var rampWidth = 0;

        while (!stack.isEmpty() && nums[i] >= nums[stack.peek()])
            rampWidth = Math.max(rampWidth, i - stack.pop());

        return rampWidth;
    }
}
