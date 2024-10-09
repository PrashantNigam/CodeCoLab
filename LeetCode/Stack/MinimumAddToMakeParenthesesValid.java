package Stack;

import java.util.ArrayDeque;

// Amazon Apple ByteDance Facebook Google Microsoft ServiceNow Twitter Uber
public class MinimumAddToMakeParenthesesValid {
    public static void main(String[] args) {
        var ob = new MinimumAddToMakeParenthesesValid();
        System.out.println(ob.minAddToMakeValid("())") == 1);
        System.out.println(ob.minAddToMakeValid("(((") == 3);
    }

    public int minAddToMakeValid(String s) {
        var unmatchedOpen = 0;
        var unmatchedClose = 0;

        for (var ch : s.toCharArray())
            if (ch == '(')
                unmatchedOpen++;
            else if (unmatchedOpen > 0)
                unmatchedOpen--;
            else
                unmatchedClose++;

        return unmatchedOpen + unmatchedClose;
    }

    /*
     * Let current char be C
     * If C is an opener, push it to stack
     * Else if C is a closer and top of stack (TOS) is an opener, then pop it from stack since this is a valid pair
     * Else if C is a closer but stack is empty, then push. This is because this bracket wasn't able to find an opener,
     * and we want to find the extras at the end and such brackets will never be able to form a pair
     * Else if C is a closer and TOS is a closer, then push for the same reason
     *
     * At the end, size of stack gives us number of extra brackets
     */
    public int minAddToMakeValid2(String s) {
        var n = s.length();
        var stack = new ArrayDeque<Character>();

        for (var i = 0; i < n; i++) {
            var ch = s.charAt(i);

            if (stack.isEmpty() || ch == '(' || stack.peek() == ')')
                stack.push(ch);
            else
                stack.pop();
        }

        return stack.size();
    }
}
