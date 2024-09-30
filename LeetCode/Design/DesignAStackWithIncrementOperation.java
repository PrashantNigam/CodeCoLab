package Design;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

// Amazon Microsoft Google eBay
public class DesignAStackWithIncrementOperation {
    public static void main(String[] args) {
        var ob = new CustomStack(3);
        ob.push(1);
        ob.push(2);
        System.out.println(ob.pop() == 2);
        ob.push(2);
        ob.push(3);
        ob.push(4);
        ob.increment(5, 100);
        ob.increment(2, 100);
        System.out.println(ob.pop() == 103);
        System.out.println(ob.pop() == 202);
        System.out.println(ob.pop() == 201);
        System.out.println(ob.pop() == -1);
    }

    // Lazy Propagation
    public static class CustomStack {

        private final int maxSize;
        private final int[] increments;
        private final Deque<Integer> stack = new ArrayDeque<>();        

        public CustomStack(int maxSize) {
            this.maxSize = maxSize;
            this.increments = new int[maxSize];
        }

        /**
         * Adds x to the top of the stack if the stack hasn't reached the maxSize.
         *
         * @param x int to push to stack
         */
        public void push(int x) {
            if (stack.size() == maxSize)
                return;

            stack.push(x);
        }

        /**
         * Pops the top element from the stack, applying any pending increments (Lazy Propagation)
         *
         * @return The popped element's value, or -1 if the stack is empty.
         */
        public int pop() {
            if (stack.isEmpty())
                return -1;

            var last = stack.size() - 1;
            var incrementedVal = stack.pop() + increments[last];

            if (!stack.isEmpty())
                increments[last - 1] += increments[last];

            increments[last] = 0;
            return incrementedVal;
        }

        /**
         * Increments the top of stack by val
         *
         * @param k   number of stack entries to increment
         * @param val stack entries increment value
         */
        public void increment(int k, int val) {
            if (stack.isEmpty())
                return;

            var last = Math.min(k, stack.size()) - 1;
            increments[last] += val;
        }
    }

    // Brute Force
    public static class CustomStack2 {

        private final int maxSize;
        private final List<Integer> stack = new LinkedList<>();

        /**
         * Initializes the object with maxSize
         *
         * @param maxSize maximum number of elements in the stack.
         */
        public CustomStack2(int maxSize) {
            this.maxSize = maxSize;
        }

        /**
         * Adds x to the top of the stack if the stack hasn't reached the maxSize.
         *
         * @param x int to push to stack
         */
        public void push(int x) {
            if (stack.size() == maxSize)
                return;
            
            stack.add(x);
        }

        /**
         * Pops from the stack
         *
         * @return returns the top of stack or -1 if the stack is empty.
         */
        public int pop() {
            return stack.isEmpty() ? -1 : stack.removeLast();
        }

        /**
         * Increments the bottom k elements of the stack by val.
         * If there are less than k elements in the stack, just increment all the elements in the stack.
         *
         * @param k   number of stack entries to increment
         * @param val stack entries increment value
         */
        public void increment(int k, int val) {
            var limit = Math.min(k, stack.size());

            for (var i = 0; i < limit; i++)
                stack.set(i, stack.get(i) + val);
        }
    }
}
