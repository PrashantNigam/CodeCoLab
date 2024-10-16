package Heaps;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

// Wayfair
public class LongestHappyString {
    public static void main(String[] args) {
        var ob = new LongestHappyString();
        System.out.println(ob.longestDiverseString(1, 1, 7).equals("ccaccbcc"));
        System.out.println(ob.longestDiverseString(7, 1, 0).equals("aabaa"));
    }

    private static class Pair {
        private final char ch;
        private int count;

        public Pair(char ch, int count) {
            this.ch = ch;
            this.count = count;
        }
    }

    public String longestDiverseString(int a, int b, int c) {
        var happy = new StringBuilder();
        var maxHeap = buildMaxHeap(a, b, c);

        while (maxHeap.peek().count > 0) {
            var top = maxHeap.poll();
            var n = happy.length();

            if (n > 1 && happy.charAt(n - 1) == top.ch && happy.charAt(n - 2) == top.ch) {
                if (maxHeap.isEmpty() || maxHeap.peek().count == 0)
                    break;

                happy.append(maxHeap.peek().ch);
                maxHeap.peek().count--;
            } else {
                happy.append(top.ch);
                top.count--;
            }

            maxHeap.add(top);
        }

        return happy.toString();
    }

    private Queue<Pair> buildMaxHeap(int a, int b, int c) {
        var maxHeap = new PriorityQueue<Pair>(Comparator.comparing(pair -> -pair.count));
        maxHeap.add(new Pair('a', a));
        maxHeap.add(new Pair('b', b));
        maxHeap.add(new Pair('c', c));
        return maxHeap;
    }
}
