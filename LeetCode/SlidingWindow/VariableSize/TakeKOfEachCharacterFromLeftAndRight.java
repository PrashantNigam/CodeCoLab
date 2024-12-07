package SlidingWindow.VariableSize;

import java.util.Arrays;
import java.util.stream.IntStream;

public class TakeKOfEachCharacterFromLeftAndRight {
    public static void main(String[] args) {
        var ob = new TakeKOfEachCharacterFromLeftAndRight();
        System.out.println(ob.takeCharacters("aabaaaacaabc", 2) == 8);
        System.out.println(ob.takeCharacters("a", 1) == -1);
    }

    public int takeCharacters(String s, int k) {
        var left = 0;
        var windowSize = 0;
        var n = s.length();
        var count = initCount(s, n);

        if (insufficientLetters(k, count))
            return -1;

        for (var right = 0; right < n; right++) {
            count[s.charAt(right) - 'a']--;

            for (; left <= right && insufficientLetters(k, count); left++)
                count[s.charAt(left) - 'a']++;

            windowSize = Math.max(windowSize, right - left + 1);
        }

        return n - windowSize;
    }

    private int[] initCount(String s, int n) {
        var count = new int[3];
      
        IntStream.range(0, n)
                 .forEach(i -> count[s.charAt(i) - 'a']++);
      
        return count;
    }

    private boolean insufficientLetters(int k, int[] count) {
        return Arrays.stream(count)
                     .anyMatch(letterCount -> letterCount < k);
    }
}
