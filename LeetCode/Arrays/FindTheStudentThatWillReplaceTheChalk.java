package Arrays;

import java.util.Arrays;

public class FindTheStudentThatWillReplaceTheChalk {
    public static void main(String[] args) {
        var ob = new FindTheStudentThatWillReplaceTheChalk();
        System.out.println(ob.chalkReplacer(new int[]{5, 1, 5}, 22) == 0);
        System.out.println(ob.chalkReplacer(new int[]{3, 4, 1, 2}, 25) == 1);
    }

    // Linear Search
    public int chalkReplacer(int[] chalk, int k) {
        var total = Arrays.stream(chalk)
                          .asLongStream()
                          .sum();                          
        var remaining = k % total;

        for (var i = 0; i < chalk.length; i++) {
            remaining -= chalk[i];

            if (remaining < 0)
                return i;
        }

        return 0;
    }
}