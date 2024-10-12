package LineSweep;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
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

    /**
     * Assigns the smallest available chair to the target friend.
     *
     * @param times 2D array of arrival and departure times for each friend.
     * @param targetFriend Index of the friend for whom to assign a chair.
     * @return The smallest chair assigned to the target friend.
     */
    public int smallestChair(int[][] times, int targetFriend) {
        var chairs = new PriorityQueue<Integer>();
        var friendToChair = new HashMap<Integer, Integer>();
        var entries = new PriorityQueue<>(Comparator.comparing((Entry e) -> e.time)
                                                    .thenComparing(e -> e.isArrival));
        
        populate(times, targetFriend, chairs, entries);
        return assignAndReleaseChairs(targetFriend, entries, friendToChair, chairs);
    }

    /**
     * Initializes chairs and entries queues based on input data.
     *
     * @param times 2D array of arrival and departure times for each friend.
     * @param targetFriend Index of the target friend.
     * @param chairs Queue to store available chairs.
     * @param entries Queue to store arrival and departure entries.
     */
    private void populate(int[][] times, int targetFriend, Queue<Integer> chairs, Queue<Entry> entries) {
        for (var i = 0; i < times.length; i++) {
            chairs.add(i);

            if (times[i][0] > times[targetFriend][0])
                continue;

            entries.add(new Entry(times[i][0], i, true));
            entries.add(new Entry(times[i][1], i, false));
        }
    }

    /**
     * Assigns and releases chairs based on arrival and departure entries.
     *
     * @param targetFriend Index of the target friend.
     * @param entries Queue of arrival and departure entries.
     * @param friendToChair Map to store assigned chairs for each friend.
     * @param chairs Queue of available chairs.
     * @return The smallest chair assigned to the target friend.
     */
    private int assignAndReleaseChairs(int targetFriend, Queue<Entry> entries, Map<Integer, Integer> friendToChair, Queue<Integer> chairs) {
        while (!entries.isEmpty()) {
            var entry = entries.poll();

            if (entry.index == targetFriend)
                return chairs.poll();
            if (entry.isArrival)
                friendToChair.put(entry.index, chairs.poll());
            else
                chairs.add(friendToChair.remove(entry.index));            
        }

        return 0;
    }
}
