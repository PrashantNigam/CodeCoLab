package PrefixSum;

import java.util.Arrays;

public class FindTheStudentThatWillReplaceTheChalk {
    public static void main(String[] args) {
        var ob = new FindTheStudentThatWillReplaceTheChalk(new int[]{5, 1, 5});
        System.out.println(ob.chalkReplacer(22) == 0);
        System.out.println(ob.chalkReplacer(4) == 0);
        System.out.println(ob.chalkReplacer(5) == 1);
        System.out.println(ob.chalkReplacer(7) == 2);
        

        ob = new FindTheStudentThatWillReplaceTheChalk(new int[]{3, 4, 1, 2});
        System.out.println(ob.chalkReplacer(25) == 1);
        System.out.println(ob.chalkReplacer(0) == 0);
    }

    private final long[] prefixSum;

    // T/S: O(n)/O(n)
    public FindTheStudentThatWillReplaceTheChalk(int[] chalk) {
        prefixSum = Arrays.stream(chalk).asLongStream().toArray();
        Arrays.parallelPrefix(prefixSum, Long::sum);
    }

    // T/S: O(lg n)/O(1)
    public int chalkReplacer(int k) {        
        var remaining = k % prefixSum[prefixSum.length - 1];
        return binarySearch(prefixSum, remaining);
    }

    /**
    * @return the smallest index i for which A[i] > remaining using Binary search
    */
    private int binarySearch(long[] A, long remaining) {
        var low = 0;
        var high = A.length - 1;

        while (low < high) {
            var mid = low + (high - low >> 1);

            if (A[mid] > remaining)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }
}
