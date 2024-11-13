package BitManipulation;

import java.util.Arrays;
import java.util.stream.IntStream;

public class LargestCombinationWithBitwiseANDGreaterThanZero {
    public static void main(String[] args) {
        var ob = new LargestCombinationWithBitwiseANDGreaterThanZero();
        System.out.println(ob.largestCombination(new int[]{16, 17, 71, 62, 12, 24, 14}) == 4);
        System.out.println(ob.largestCombination(new int[]{8, 8}) == 2);
    }

    private static final int[] MASKS = new int[31];

    static {
        MASKS[0] = 1;
        IntStream.range(1, 31)
                 .forEach(i -> MASKS[i] = MASKS[i - 1] << 1);
    }

    /**
     * @param candidates array of natural numbers
     * @return size of the largest combination of candidates with a bitwise AND > 0
     */
    public int largestCombination(int[] candidates) {
        var maxSize = 0;

        for (var mask : MASKS) 
            maxSize = Math.max(maxSize, (int) Arrays.stream(candidates)
                                                    .filter(candidate -> (candidate & mask) == mask)
                                                    .count());       
        return maxSize;
    }
}
