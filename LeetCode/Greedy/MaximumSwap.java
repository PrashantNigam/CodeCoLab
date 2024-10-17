package Greedy;

import java.util.HashMap;
import java.util.Map;

// Amazon ByteDance Facebook Google Microsoft Uber
public class MaximumSwap {
    public static void main(String[] args) {
        var ob = new MaximumSwap();
        System.out.println(ob.maximumSwap(2736) == 7236);
        System.out.println(ob.maximumSwap(9973) == 9973);
        System.out.println(ob.maximumSwap(98368) == 98863);
    }

    /**
     * * For a maximum swap, you need to swap a digit with a greater digit on its right side
     * * Create a map of digits to their last index in the num
     * * For each digit d in num, run down from 9 to d (exclusive)
     * * If this digit is to the right of d, then swap and exit
     * * Else if no match is found, then num is already maxed out
     * * The array created will of length O(log num)
     * * The inner loop runs in O(1) time
     */
    public int maximumSwap(int num) {
        var arr = String.valueOf(num).toCharArray();
        var digitToLastIndex = buildMap(arr);

        for (var i = 0; i < arr.length; i++) {
            var maxNumber = getMaxNumber(arr, i, digitToLastIndex);

            if (maxNumber != -1)
                return maxNumber;
        }

        return num;
    }

    private Map<Integer, Integer> buildMap(char[] arr) {
        var digitToLastIndex = new HashMap<Integer, Integer>();

        for (var i = 0; i < arr.length; i++)
            digitToLastIndex.put(Character.getNumericValue(arr[i]), i);

        return digitToLastIndex;
    }

    private int getMaxNumber(char[] arr, int digitIndex, Map<Integer, Integer> digitToLastIndex) {
        var digit = Character.getNumericValue(arr[digitIndex]);

        for (var d = 9; d > digit; d--) {
            var lastIndex = digitToLastIndex.getOrDefault(d, 0);

            if (lastIndex <= digitIndex)
                continue;

            swap(arr, digitIndex, lastIndex);
            return Integer.parseInt(new String(arr));
        }

        return -1;
    }

    private void swap(char[] A, int i, int j) {
        var temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }
}
