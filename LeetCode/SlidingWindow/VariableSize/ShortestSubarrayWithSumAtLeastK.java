package SlidingWindow.VariableSize;

import java.util.ArrayDeque;
import java.util.List;

// Amazon Facebook GoldmanSachs Google
public class ShortestSubarrayWithSumAtLeastK {
    public static void main(String[] args) {
        var ob = new ShortestSubarrayWithSumAtLeastK();
        System.out.println(ob.shortestSubarray(new int[]{1}, 1) == 1);
        System.out.println(ob.shortestSubarray(new int[]{1, 2}, 4) == -1);
        System.out.println(ob.shortestSubarray(new int[]{2, -1, 2}, 3) == 3);
    }

    /**
     * @return length of the shortest non-empty subarray of nums with a sum of at least k.
     * If there is no such subarray, return -1.
     */
    public int shortestSubarray(int[] nums, int k) {
        var minLength = Integer.MAX_VALUE;
        var prefixSum = getPrefixSum(nums);
        var dq = new ArrayDeque<>(List.of(0));

        for (var i = 1; i < prefixSum.length; i++) {

            // shrink window from left till subarray sum >= k
            while (!dq.isEmpty() && prefixSum[i] - prefixSum[dq.peekFirst()] >= k)
                minLength = Math.min(minLength, i - dq.pollFirst());

            // maintain monotonic nature of the deque
            while (!dq.isEmpty() && prefixSum[dq.peekLast()] >= prefixSum[i])
                dq.pollLast();

            dq.addLast(i);
        }

        return minLength == Integer.MAX_VALUE ? -1 : minLength;
    }

    private long[] getPrefixSum(int[] nums) {
        var prefixSum = new long[nums.length + 1];

        for (var i = 1; i < prefixSum.length; i++)
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];

        return prefixSum;
    }
}
