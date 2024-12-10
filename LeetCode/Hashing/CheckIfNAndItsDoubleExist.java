package Hashing;

import java.util.Arrays;
import java.util.HashSet;

// Google
public class CheckIfNAndItsDoubleExist {
    public static void main(String[] args) {
        var ob = new CheckIfNAndItsDoubleExist();
        System.out.println(ob.checkIfExist(new int[]{10, 2, 5, 3}));
        System.out.println(!ob.checkIfExist(new int[]{3, 1, 7, 11}));
    }

    public boolean checkIfExist(int[] arr) {
        var values = new HashSet<Integer>();

        if (twoZeros(arr, values))
            return true;

        values.remove(0);

        return Arrays.stream(arr)
                     .anyMatch(n -> values.contains(2 * n));
    }

    private boolean twoZeros(int[] arr, HashSet<Integer> values) {
        return Arrays.stream(arr)
                     .anyMatch(n -> !values.add(n) && n == 0);
    }

    // Binary Search
    public boolean checkIfExist2(int[] arr) {
        Arrays.sort(arr);
        return IntStream.range(0, arr.length)
                        .anyMatch(i -> arr[i] == 0 && i + 1 < arr.length && arr[i + 1] == 0 
                                    || arr[i] != 0 && Arrays.binarySearch(arr, arr[i] * 2) >= 0);
    }
}
