package LineSweep;

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

    private record Point(int val, boolean isStart) {}
  
    public int minGroups(int[][] intervals) {
        var activeIntervals = 0;
        var maxActiveIntervals = 0;
        var points = toSortedPoints(intervals);

        for (var point : points) {
            if (point.isStart)
                activeIntervals++;
            else
                activeIntervals--;

            maxActiveIntervals = Math.max(maxActiveIntervals, activeIntervals);
        }

        return maxActiveIntervals;
    }

    private List<Point> toSortedPoints(int[][] intervals) {
        var points = new ArrayList<Point>();

        for (var interval : intervals) {
            points.add(new Point(interval[0], true));
            points.add(new Point(interval[1] + 1, false));
        }

        points.sort(Comparator.comparing((Point point) -> point.val)
                              .thenComparing(point -> point.isStart));
        return points;
    }
}
