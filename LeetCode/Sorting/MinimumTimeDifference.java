package Sorting;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// Amazon Palantir
public class MinimumTimeDifference {
    public static void main(String[] args) {
        var ob = new MinimumTimeDifference();
        System.out.println(ob.findMinDifference(new ArrayList<>(List.of("23:59", "00:00"))) == 1);
        System.out.println(ob.findMinDifference(new ArrayList<>(List.of("00:00", "23:59", "00:00"))) == 0);
    }

    private static final int MINUTES_IN_DAY = 24 * 60;

    public int findMinDifference(List<String> timePoints) {
        var minutes = toSortedMinutes(timePoints);
        var minDiff = minutes.getFirst() + (MINUTES_IN_DAY - minutes.getLast());

        for (var i = 1; i < minutes.size(); i++)
            minDiff = Math.min(minDiff, minutes.get(i) - minutes.get(i - 1));

        return minDiff;
    }

    private List<Integer> toSortedMinutes(List<String> timePoints) {
        var times = new ArrayList<Integer>();

        for (var timePoint : timePoints) {
            var parts = timePoint.split(":");
            times.add(Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]));
        }

        times.sort(Comparator.naturalOrder());
        return times;
    }
}
