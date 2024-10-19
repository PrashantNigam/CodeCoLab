package DynamicProgramming.Bits;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Adobe Amazon Facebook Google LinkedIn Microsoft Netflix PegaSystems Samsung VMware
public class CountNumberOfMaximumBitwiseORSubsets {
    public static void main(String[] args) {
        var ob = new CountNumberOfMaximumBitwiseORSubsets();
        System.out.println(ob.countMaxOrSubsets(new int[]{3, 1}) == 2);
        System.out.println(ob.countMaxOrSubsets(new int[]{2, 2, 2}) == 7);
        System.out.println(ob.countMaxOrSubsets(new int[]{3, 2, 1, 5}) == 6);
    }

    private record Pair(int i, int or) {}

    // Backtracking
    public int countMaxOrSubsets(int[] nums) {
      var maxOr = Arrays.stream(nums).reduce(0, (a, b) -> a | b);
      return countMaxOrSubsets(nums, 0, maxOr, 0);
    }
  
    private int countMaxOrSubsets(int[] nums, int i, int maxOr, int or) {
        if (i == nums.length)
            return or == maxOr ? 1 : 0;
    
        return countMaxOrSubsets(nums, i + 1, maxOr, or | nums[i]) // choose
             + countMaxOrSubsets(nums, i + 1, maxOr, or); // unchoose
    }

    // Memoization
    public int countMaxOrSubsets2(int[] nums) {
        var maxOr = Arrays.stream(nums).reduce(0, (a, b) -> a | b);
        return countMaxOrSubsets(nums, 0, maxOr, 0, new HashMap<>());
    }

    private int countMaxOrSubsets(int[] nums, int i, int maxOr, int or, Map<Pair, Integer> memo) {
        if (i == nums.length)
            return or == maxOr ? 1 : 0;

        var key = new Pair(i, or);

        if (memo.containsKey(key))
            return memo.get(key);

        var count = countMaxOrSubsets(nums, i + 1, maxOr, or | nums[i], memo)
                  + countMaxOrSubsets(nums, i + 1, maxOr, or, memo);

        return memo.compute(key, (_, _) -> count);
    }
}
