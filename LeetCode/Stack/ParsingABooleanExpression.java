package Stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

// Affinity Facebook Google Microsoft
public class ParsingABooleanExpression {
    public static void main(String[] args) {
        var ob = new ParsingABooleanExpression();
        System.out.println(!ob.parseBoolExpr("&(|(f))"));
        System.out.println(ob.parseBoolExpr("|(f,f,f,t)"));
        System.out.println(ob.parseBoolExpr("!(&(f,t))"));
    }

    private static final char AND = '&';
    private static final char FALSE = 'f';
    private static final char NOT = '!';
    private static final char OR = '|';
    private static final char TRUE = 't';

    public boolean parseBoolExpr(String expression) {
        var stack = new ArrayDeque<Character>();

        for (var ch : expression.toCharArray())
            switch (ch) {
                case ',':
                    continue;
                case ')':
                    processSubexpression(stack);
                    break;
                default:
                    stack.push(ch);
            }

        return stack.poll() == TRUE;
    }

    /**
     * @param stack a non-empty stack that may contain operands, operators and closing brackets
     */
    private void processSubexpression(Deque<Character> stack) {
        var letters = new HashSet<Character>();

        while (stack.peek() != '(') 
            letters.add(stack.pop());

        stack.pop();
        stack.push(applyOperation(letters, stack.pop()));
    }

    private char applyOperation(Set<Character> letters, char operation) {
        return switch (operation) {
            case AND -> letters.contains(FALSE) ? FALSE : TRUE;
            case NOT -> letters.contains(TRUE) ? FALSE : TRUE;
            case OR -> letters.contains(TRUE) ? TRUE : FALSE;
            default -> 0; // impossible case, but compiler requires it
        };
    }
}
