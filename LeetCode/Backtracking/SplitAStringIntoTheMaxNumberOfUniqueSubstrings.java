package Backtracking;

import java.util.HashSet;
import java.util.Set;

// Google
public class SplitAStringIntoTheMaxNumberOfUniqueSubstrings {
    public static void main(String[] args) {
        var ob = new SplitAStringIntoTheMaxNumberOfUniqueSubstrings();
        System.out.println(ob.maxUniqueSplit("ababccc") == 5);
        System.out.println(ob.maxUniqueSplit("aba") == 2);
        System.out.println(ob.maxUniqueSplit("aa") == 1);
    }

    public int maxUniqueSplit(String s) {
        return backtrack(s, new HashSet<>());
    }

    private int backtrack(String s, Set<String> words) {
        var max = 0;

        for (var i = 1; i <= s.length(); i++) {
            var word = s.substring(0, i);

            if (!words.add(word))
                continue;

            max = Math.max(max, 1 + backtrack(s.substring(i), words));
            words.remove(word);
        }

        return max;
    }
}
