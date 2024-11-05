package Hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class LongestSquareStreakInAnArray {
    public static void main(String[] args) {
        var ob = new LongestSquareStreakInAnArray();
        System.out.println(ob.longestSquareStreak(new int[]{4, 3, 6, 16, 8, 2}) == 3);
        System.out.println(ob.longestSquareStreak(new int[]{2, 3, 5, 6, 7}) == -1);
    }

    public static final double MAX = 1e5;

    public int longestSquareStreak(int[] nums) {
        var maxSize = 0;
        var set = Arrays.stream(nums)
                        .mapToObj(num -> (long) num)
                        .collect(Collectors.toCollection(HashSet::new));

        for (var num : nums)
            maxSize = Math.max(maxSize, longestSquareStreakSize(num, set));

        return maxSize == 1 ? -1 : maxSize;
    }

    private int longestSquareStreakSize(int num, Set<Long> set) {
        var size = 0;

        for (var term = (long) num; term <= MAX && set.contains(term); term *= term)
            size++;

        return size;
    }
}
