package Design;

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

    static class CustomStack {

        private final int maxSize;
        private final List<Integer> stack = new LinkedList<>();

        /**
         * Initializes the object with maxSize
         *
         * @param maxSize maximum number of elements in the stack.
         */
        public CustomStack(int maxSize) {
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
            return stack.isEmpty() 
                 ? -1 
                 : stack.removeLast();
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
