package Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MinimumStringLengthAfterRemovingSubstrings {
    public static void main(String[] args) {
        var ob = new MinimumStringLengthAfterRemovingSubstrings();
        System.out.println(ob.minLength("ABFCACDB") == 2);
        System.out.println(ob.minLength("ACBBD") == 5);
    }

    private static final String[] REPLACEABLE = {"AB", "CD"};

    public int minLength(String s) {
        var stack = new ArrayDeque<Character>();

        for (var ch : s.toCharArray())
            if (isReplaceable(ch, stack))
                stack.pop();
            else
                stack.push(ch);

        return stack.size();
    }

    private boolean isReplaceable(char current, Deque<Character> stack) {
        return Arrays.stream(REPLACEABLE)
                     .anyMatch(replaceable -> !stack.isEmpty()
                                           && stack.peek() == replaceable.charAt(0)
                                           && current == replaceable.charAt(1));
    }
}
