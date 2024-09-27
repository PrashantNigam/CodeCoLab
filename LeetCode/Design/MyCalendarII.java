package Design;

import java.util.TreeMap;

// Amazon Google
public class MyCalendarII {
    public static void main(String[] args) {
        var ob = new MyCalendarTwo();
        System.out.println(ob.book(10, 20));
        System.out.println(ob.book(50, 60));
        System.out.println(ob.book(10, 40));
        System.out.println(!ob.book(5, 15));
        System.out.println(ob.book(5, 10));
        System.out.println(ob.book(25, 55));
    }

    private static class MyCalendarTwo {
        private final TreeMap<Integer, Integer> timeCount = new TreeMap<>();

        /**
         * Line Sweep Algorithm
         *
         * @param start start of event (inclusive)
         * @param end   end of event (exclusive)
         * @return true if the event can be added to the calendar successfully without causing a triple booking.
         * Otherwise, return false and do not add the event to the calendar
         */
        public boolean book(int start, int end) {
            // add booking to calendar
            incrementCountFor(start);
            decrementCountFor(end);

            if (!tripleBooked())
                return true;

            // remove booking from calendar
            decrementCountFor(start);
            incrementCountFor(end);
            return false;
        }

        private void incrementCountFor(int time) {
            timeCount.compute(time, (_, c) -> c == null ? 1 : ++c);
        }

        private void decrementCountFor(int time) {
            timeCount.compute(time, (_, c) -> c == null ? -1 : --c);
        }

        private boolean tripleBooked() {
            var bookings = 0;

            for (var count : timeCount.values()) {
                bookings += count;

                if (bookings > 2)
                    return true;
            }

            return false;
        }
    }
}
