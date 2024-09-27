package Design;

import java.util.NavigableMap;
import java.util.TreeMap;

// Amazon Apple Google Intuit Microsoft Uber
public class MyCalendarI {
    public static void main(String[] args) {
        var ob = new MyCalendar();
        System.out.println(ob.book(10, 20));
        System.out.println(!ob.book(15, 25));
        System.out.println(ob.book(20, 30));
    }

    private static class MyCalendar {
        private final NavigableMap<Integer, Integer> startToEnd = new TreeMap<>();

        /**
         * @param start start of event (inclusive)
         * @param end   end of event (exclusive)
         * @return true if the event can be added to the calendar successfully without causing a double booking.
         * Otherwise, return false and do not add the event to the calendar.
         */
        public boolean book(int start, int end) {
            if (thisEventOverlapsWithPreviousEvent(start) || thisEventOverlapsWithNextEvent(start, end))
                return false;

            startToEnd.put(start, end);
            return true;
        }

        private boolean thisEventOverlapsWithPreviousEvent(int start) {
            var prevEvent = startToEnd.floorEntry(start);
            var prevEnd = prevEvent == null ? 0 : prevEvent.getValue();
            return start < prevEnd;
        }

        private boolean thisEventOverlapsWithNextEvent(int start, int end) {
            var nextEvent = startToEnd.ceilingEntry(start);
            var nextStart = nextEvent == null ? Integer.MAX_VALUE : nextEvent.getKey();
            return end > nextStart;
        }
    }
}
