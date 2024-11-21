package BinarySearch;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

public class MostBeautifulItemForEachQuery {
    public static void main(String[] args) {
        var ob = new MostBeautifulItemForEachQuery();
        System.out.println(Arrays.equals(ob.maximumBeauty(new int[][]{{1, 2}, {3, 2}, {2, 4}, {5, 6}, {3, 5}}, new int[]{1, 2, 3, 4, 5, 6}), 
                                         new int[]{2, 4, 5, 5, 6, 6}));
        System.out.println(Arrays.equals(ob.maximumBeauty(new int[][]{{1, 2}, {1, 2}, {1, 3}, {1, 4}}, new int[]{1}), 
                                         new int[]{4}));
        System.out.println(Arrays.equals(ob.maximumBeauty(new int[][]{{10, 1000}}, new int[]{5}), 
                                         new int[1]));
    }

    /**
     * @param items   items[i] = [price_i, beauty_i]
     * @param queries array of query prices
     * @return an array answer of the same length as queries where answer[j] is the answer to the jth query
     */
    public int[] maximumBeauty(int[][] items, int[] queries) {
        var maxBeauty = new int[items.length];
        var priceToIndex = new TreeMap<Integer, Integer>();
        Arrays.sort(items, Comparator.comparing(item -> item[0]));
        init(items, priceToIndex, maxBeauty);
        return Arrays.stream(queries)
                     .map(query -> execute(priceToIndex, maxBeauty, query))
                     .toArray();
    }

    private void init(int[][] items, TreeMap<Integer, Integer> priceToLastIndex, int[] maxBeauty) {
        priceToLastIndex.put(items[0][0], 0);
        maxBeauty[0] = items[0][1];

        for (var i = 1; i < items.length; i++) {
            priceToLastIndex.put(items[i][0], i);
            maxBeauty[i] = Math.max(maxBeauty[i - 1], items[i][1]);
        }
    }

    /**
     * @param priceToLastIndex sorted map of price to its last index
     * @param maxBeauty array containing max beauty till each index
     * @param query     query integer
     * @return maximum beauty of an item whose price is less than or equal to query
     */
    private int execute(TreeMap<Integer, Integer> priceToLastIndex, int[] maxBeauty, int query) {
        var bestPriceMatch = priceToLastIndex.floorKey(query);
        return bestPriceMatch == null ? 0 : maxBeauty[priceToLastIndex.get(bestPriceMatch)];
    }
}
