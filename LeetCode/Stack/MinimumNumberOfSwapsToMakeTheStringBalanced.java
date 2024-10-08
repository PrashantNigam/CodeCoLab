package Stack;

import java.util.ArrayDeque;

public class MinimumNumberOfSwapsToMakeTheStringBalanced {
    public static void main(String[] args) {
        var ob = new MinimumNumberOfSwapsToMakeTheStringBalanced();
        System.out.println(ob.minSwaps("][][") == 1);
        System.out.println(ob.minSwaps("]]][[[") == 2);
        System.out.println(ob.minSwaps("[]") == 0);
    }

    public int minSwaps(String s) {
        var n = s.length();
        var stack = new ArrayDeque<Character>();

        for (var i = 0; i < n; i++) {
            var ch = s.charAt(i);

            if (!stack.isEmpty() && stack.peek() == '[' && ch == ']')
                stack.pop();
            else
                stack.push(ch);
        }

        var unbalancedPairs = stack.size() >> 1;
        return ++unbalancedPairs >> 1;
    }
}
