package Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

// Amazon Facebook Google
public class RankTransformOfAnArray {
    public static void main(String[] args) {
        var ob = new RankTransformOfAnArray();
        System.out.println(Arrays.equals(ob.arrayRankTransform(new int[]{40, 10, 20, 30}), 
                                         new int[]{4, 1, 2, 3}));
        System.out.println(Arrays.equals(ob.arrayRankTransform(new int[]{100, 100, 100}), 
                                         new int[]{1, 1, 1}));
        System.out.println(Arrays.equals(ob.arrayRankTransform(new int[]{37, 12, 28, 9, 100, 56, 80, 5, 12}),        
                                         new int[]{5, 3, 4, 2, 8, 6, 7, 1, 3}));
    }

    public int[] arrayRankTransform(int[] arr) {
        var rank = 1;
        var ranks = new int[arr.length];
        var numToIndices = getNumberToIndices(arr);

        for (var indices : numToIndices.values()) {
            for (var i : indices)
                ranks[i] = rank;

            rank++;
        }

        return ranks;
    }

    private Map<Integer, List<Integer>> getNumberToIndices(int[] arr) {
        var numToIndices = new TreeMap<Integer, List<Integer>>();

        for (var i = 0; i < arr.length; i++)
            numToIndices.computeIfAbsent(arr[i], _ -> new ArrayList<>())
                        .add(i);

        return numToIndices;
    }
}
