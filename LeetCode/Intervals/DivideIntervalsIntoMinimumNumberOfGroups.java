package Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

// Adobe Walmart
public class DivideIntervalsIntoMinimumNumberOfGroups {
    public static void main(String[] args) {
        var ob = new DivideIntervalsIntoMinimumNumberOfGroups();
        System.out.println(ob.minGroups(new int[][]{{5, 10}, {6, 8}, {1, 5}, {2, 3}, {1, 10}}) == 3);
        System.out.println(ob.minGroups(new int[][]{{1, 3}, {5, 6}, {8, 10}, {11, 13}}) == 1);
        System.out.println(ob.minGroups(new int[][]{{1, 1}}) == 1);
    }

    // Same as Meeting Rooms II
    public int minGroups(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[0]));
        var minHeapOfEnds = new PriorityQueue<>(List.of(intervals[0][1]));

        for (var i = 1; i < intervals.length; i++) {
            var start = intervals[i][0];
            var end = intervals[i][1];

            // if this interval starts at/after some interval ends, then pop the earliest ending interval from the heap
            if (start > minHeapOfEnds.peek())
                minHeapOfEnds.poll();

            minHeapOfEnds.add(end);
        }

        return minHeapOfEnds.size();
    }
}
