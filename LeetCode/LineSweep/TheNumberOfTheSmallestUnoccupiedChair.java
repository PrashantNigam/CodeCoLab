package LineSweep;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// Amazon Microsoft OtterAI
public class TheNumberOfTheSmallestUnoccupiedChair {
    public static void main(String[] args) {
        var ob = new TheNumberOfTheSmallestUnoccupiedChair();
        System.out.println(ob.smallestChair(new int[][]{{1, 4}, {2, 3}, {4, 6}}, 1) == 1);
        System.out.println(ob.smallestChair(new int[][]{{3, 10}, {1, 5}, {2, 6}}, 0) == 2);
    }

    private record Entry(int time, int index, boolean isArrival) {}

    public int smallestChair(int[][] times, int targetFriend) {
        var entries = new ArrayList<Entry>();
        var chairs = new PriorityQueue<Integer>();
        var friendToChair = new HashMap<Integer, Integer>();
        populate(times, targetFriend, chairs, entries);

        for (var entry : entries)
            if (entry.isArrival)
                friendToChair.put(entry.index, chairs.poll());
            else
                chairs.add(friendToChair.getOrDefault(entry.index, 0));

        return friendToChair.get(targetFriend);
    }

    private void populate(int[][] times, int targetFriend, Queue<Integer> chairs, List<Entry> entries) {
        for (var i = 0; i < times.length; i++) {
            chairs.add(i);

            if (times[i][0] >= times[targetFriend][1])
                continue;

            entries.add(new Entry(times[i][0], i, true));
            entries.add(new Entry(times[i][1], i, false));
        }

        entries.sort(Comparator.comparing((Entry e) -> e.time)
                               .thenComparing(e -> e.isArrival));
    }
}
