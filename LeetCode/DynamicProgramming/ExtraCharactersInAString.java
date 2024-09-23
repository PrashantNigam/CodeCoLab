package DynamicProgramming;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ExtraCharactersInAString {
    public static void main(String[] args) {
        var ob = new ExtraCharactersInAString();
        System.out.println(ob.minExtraChar("leetscode", new String[]{"leet", "code", "leetcode"}) == 1);
        System.out.println(ob.minExtraChar("sayhelloworld", new String[]{"hello", "world"}) == 3);
    }


    public int minExtraChar(String s, String[] dictionary) {
        return minExtraChar(s, new HashSet<>(Arrays.asList(dictionary)), s.length(), 0, new HashMap<>());
    }

    public int minExtraChar(String s, Set<String> dictionary, int n, int i, Map<Integer, Integer> memo) {
        if (i == n)
            return 0;
        if (memo.containsKey(i))
            return memo.get(i);

        var minExtra = n;
        var word = new StringBuilder();

        for (var j = i; j < n; j++) {
            word.append(s.charAt(j));

            var extra = dictionary.contains(word.toString())
                      ? 0
                      : word.length();

            var nextExtra = minExtraChar(s, dictionary, n, j + 1, memo);
            minExtra = Math.min(minExtra, extra + nextExtra);
        }

        memo.put(i, minExtra);
        return minExtra;
    }
}
