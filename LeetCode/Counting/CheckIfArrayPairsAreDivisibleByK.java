package Counting;

import java.util.HashMap;
import java.util.Objects;
import java.util.stream.IntStream;

// Qubole
public class CheckIfArrayPairsAreDivisibleByK {
    public static void main(String[] args) {
        var ob = new CheckIfArrayPairsAreDivisibleByK();
        System.out.println(ob.canArrange(new int[]{1, 2, 3, 4, 5, 10, 6, 7, 8, 9}, 5));
        System.out.println(ob.canArrange(new int[]{1, 2, 3, 4, 5, 6}, 7));
        System.out.println(!ob.canArrange(new int[]{1, 2, 3, 4, 5, 6}, 10));
        System.out.println(ob.canArrange(new int[]{-1, 1, -2, 2, -3, 3, -4, 4}, 3));
    }

    public boolean canArrange(int[] arr, int k) {
        var remainderCount = new HashMap<Integer, Integer>();

        for (var num : arr)
            remainderCount.compute(Math.floorMod(num, k), (_, v) -> v == null ? 1 : ++v);

        if ((remainderCount.getOrDefault(0, 0) & 1) == 1)
            return false;

        return IntStream.rangeClosed(1, k >> 1)
                        .allMatch(i -> Objects.equals(remainderCount.get(i), 
                                                      remainderCount.get(k - i)));
    }
}
