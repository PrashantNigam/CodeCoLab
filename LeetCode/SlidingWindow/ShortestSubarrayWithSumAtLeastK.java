package SlidingWindow;

import java.util.ArrayDeque;

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
        var dq = new ArrayDeque<Integer>();
        var prefixSum = getPrefixSum(nums);

        for (var i = 0; i < prefixSum.length; i++) {
            
            while (!dq.isEmpty() && prefixSum[i] - prefixSum[dq.peekFirst()] >= k)
                minLength = Math.min(minLength, i - dq.pollFirst());
            
            while (!dq.isEmpty() && prefixSum[i] <= prefixSum[dq.peekLast()])
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
