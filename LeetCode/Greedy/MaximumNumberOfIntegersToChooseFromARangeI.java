package Greedy;

import java.util.HashSet;
import java.util.Set;

public class MaximumNumberOfIntegersToChooseFromARangeI {
    public static void main(String[] args) {
        var ob = new MaximumNumberOfIntegersToChooseFromARangeI();
        System.out.println(ob.maxCount(new int[]{1, 6, 5}, 5, 6) == 2);
        System.out.println(ob.maxCount(new int[]{1, 2, 3, 4, 5, 6, 7}, 8, 1) == 0);
        System.out.println(ob.maxCount(new int[]{11}, 7, 50) == 7);
    }

    public int maxCount(int[] banned, int n, int maxSum) {
        var sum = 0;
        var count = 0;
        var blocked = Arrays.stream(banned)
                            .boxed()
                            .collect(Collectors.toCollection(HashSet::new));

        for (var i = 1; i <= n; i++) {
            var nextSum = sum + i;

            if (blocked.contains(i) || nextSum > maxSum)
                continue;

            sum = nextSum;
            count++;
        }

        return count;
    }
}
