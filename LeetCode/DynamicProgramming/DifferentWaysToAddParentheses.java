package DynamicProgramming;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

// Amazon Facebook Google
public class DifferentWaysToAddParentheses {
    public static void main(String[] args) {
        var ob = new DifferentWaysToAddParentheses();
        System.out.println(ob.diffWaysToCompute("1+2*3").equals(List.of(7, 9)));
        System.out.println(ob.diffWaysToCompute("2*3-4-5").equals(List.of(8, -12, 7, -7, -3)));
        System.out.println(ob.diffWaysToCompute("2-1-1").equals(List.of(2, 0)));
        System.out.println(ob.diffWaysToCompute("2*3-4*5").equals(List.of(-34, -10, -14, -10, 10)));
    }

    private static final Map<Character, BiFunction<Integer, Integer, Integer>> OPERATIONS = Map.of('+', Integer::sum,
                                                                                                   '-', (a, b) -> a - b,
                                                                                                   '*', (a, b) -> a * b);
  
    public List<Integer> diffWaysToCompute(String expression) {
        return diffWaysToCompute(expression, new HashMap<>());
    }

    private List<Integer> diffWaysToCompute(String expression, Map<String, List<Integer>> memo) {
        if (memo.containsKey(expression))
            return memo.get(expression);

        var values = new ArrayList<Integer>();

        if (containsOperator(expression))
            splitExpression(expression, values, memo);
        else
            values.add(Integer.parseInt(expression));

        return memo.compute(expression, (_, _) -> values);
    }

    private void splitExpression(String expression, List<Integer> values, Map<String, List<Integer>> memo) {
        var n = expression.length();

        for (var i = 0; i < n; i++) {

            var ch = expression.charAt(i);
            if (Character.isDigit(ch))
                continue;

            var leftParts = diffWaysToCompute(expression.substring(0, i), memo);
            var rightParts = diffWaysToCompute(expression.substring(i + 1), memo);
            evaluateExpression(values, leftParts, rightParts, OPERATIONS.get(ch));
        }
    }

    private boolean containsOperator(String s) {
        return IntStream.range(0, s.length())
                        .anyMatch(i -> OPERATIONS.containsKey(s.charAt(i)));
    }

    private void evaluateExpression(List<Integer> values, List<Integer> leftParts, List<Integer> rightParts, BiFunction<Integer, Integer, Integer> operation) {
        for (var left : leftParts)
            for (var right : rightParts)
                values.add(operation.apply(left, right));
    }
}
