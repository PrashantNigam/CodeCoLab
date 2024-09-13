package BitManipulation.BitwiseXOR;

import java.util.Arrays;

// Airtel
public class XORQueriesOfASubarray {
    public static void main(String[] args) {
        var ob = new XORQueriesOfASubarray();        
        System.out.println(Arrays.equals(ob.xorQueries(new int[]{1, 3, 4, 8}, new int[][]{{0, 1}, {1, 2}, {0, 3}, {3, 3}}), 
                                         new int[]{2, 7, 14, 8}));
        System.out.println(Arrays.equals(ob.xorQueries(new int[]{4, 8, 2, 10}, new int[][]{{2, 3}, {1, 3}, {0, 0}, {0, 3}}), 
                                         new int[]{8, 0, 4, 4}));
    }

    public int[] xorQueries(int[] arr, int[][] queries) {
        var prefixXOR = arr.clone();
        Arrays.parallelPrefix(prefixXOR, (a, b) -> a ^ b);

        return Arrays.stream(queries)
                     .mapToInt(query -> rangeXOR(prefixXOR, query))
                     .toArray();
    }

    private int rangeXOR(int[] prefixXOR, int[] query) {
        var left = query[0];
        var right = query[1];

        return left == 0
             ? prefixXOR[right]
             : prefixXOR[right] ^ prefixXOR[left - 1];
    }
}
