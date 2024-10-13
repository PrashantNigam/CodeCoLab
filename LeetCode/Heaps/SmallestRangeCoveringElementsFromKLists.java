package Heaps;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// Amazon Facebook Google Lyft Microsoft Pinterest Snap
public class SmallestRangeCoveringElementsFromKLists {
    public static void main(String[] args) {
        var ob = new SmallestRangeCoveringElementsFromKLists();
        System.out.println(Arrays.equals(ob.smallestRange(List.of(
                List.of(4, 10, 15, 24, 26),
                List.of(0, 9, 12, 20),
                List.of(5, 18, 22, 30))), new int[]{20, 24}));
        System.out.println(Arrays.equals(ob.smallestRange(List.of(
                List.of(1, 2, 3),
                List.of(1, 2, 3),
                List.of(1, 2, 3))), new int[]{1, 1}));
    }

    private static final int MIN_VALUE = (int) -1e5;
    private static final int MAX_VALUE = (int) 1e5;
    private record Item(int listIndex, int index, int value) {}

    public int[] smallestRange(List<List<Integer>> nums) {
        var minHeap = new PriorityQueue<Item>(Comparator.comparing(item -> item.value));
        var max = getMinHeap(nums, minHeap);
        var minRangeLow = MIN_VALUE;
        var minRangeHigh = MAX_VALUE;

        while (!minHeap.isEmpty()) {
            var min = minHeap.poll();
            var nextIndex = min.index + 1;
            var minList = nums.get(min.listIndex);

            if (max - min.value < minRangeHigh - minRangeLow) {
                minRangeLow = min.value;
                minRangeHigh = max;
            }
            if (nextIndex == minList.size())
                break;

            var next = minList.get(nextIndex);
            max = Math.max(max, next);
            minHeap.add(new Item(min.listIndex, nextIndex, next));
        }

        return new int[]{minRangeLow, minRangeHigh};
    }

    private int getMinHeap(List<List<Integer>> nums, Queue<Item> minHeap) {
        var max = Integer.MIN_VALUE;

        for (var i = 0; i < nums.size(); i++) {
            var first = nums.get(i).getFirst();
            max = Math.max(max, first);
            minHeap.add(new Item(i, 0, first));
        }

        return max;
    }
}
