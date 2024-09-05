package Maths;

import java.util.Arrays;
import java.util.stream.IntStream;

public class FindMissingObservations {
    public static void main(String[] args) {
        var ob = new FindMissingObservations();
        System.out.println(Arrays.equals(ob.missingRolls(new int[]{3, 2, 4, 3}, 4, 2), new int[]{6, 6}));
        System.out.println(Arrays.equals(ob.missingRolls(new int[]{1, 5, 6}, 3, 4), new int[]{3, 2, 2, 2}));
        System.out.println(Arrays.equals(ob.missingRolls(new int[]{1, 2, 3, 4}, 6, 4), new int[]{}));
    }

    private static final int[] EMPTY = {};

    public int[] missingRolls(int[] rolls, int mean, int n) {
        var m = rolls.length;
        var mSum = Arrays.stream(rolls)
                         .sum();
        var nSum = mean * (m + n) - mSum;

        if (nSum < n || nSum > 6 * n)
            return EMPTY;

        var missing = new int[n];
        var equalParts = nSum / n;
        var remain = nSum % n;
        Arrays.fill(missing, equalParts);
        IntStream.range(0, remain)
                 .forEach(i -> missing[i]++);
                 
        return missing;
    }
}
