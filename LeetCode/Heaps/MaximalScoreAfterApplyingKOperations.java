package Heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

// McKinsey
public class MaximalScoreAfterApplyingKOperations {
    public static void main(String[] args) {
        var ob = new MaximalScoreAfterApplyingKOperations();
        System.out.println(ob.maxKelements(new int[]{10, 10, 10, 10, 10}, 5) == 50);
        System.out.println(ob.maxKelements(new int[]{1, 10, 3, 3, 3}, 3) == 17);
    }

    public long maxKelements(int[] nums, int k) {
        var score = 0L;
        var maxHeap = toMaxHeap(nums);

        while (k-- > 0) {
            score += maxHeap.peek();
            maxHeap.add(Math.ceilDiv(maxHeap.poll(), 3));
        }

        return score;
    }

    private Queue<Integer> toMaxHeap(int[] nums) {
        var maxHeap = new PriorityQueue<Integer>(Comparator.reverseOrder());
        Arrays.stream(nums).forEach(maxHeap::add);
        return maxHeap;
    }
}
