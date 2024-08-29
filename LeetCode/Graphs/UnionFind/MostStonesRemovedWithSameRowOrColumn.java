package Graphs.UnionFind;

import java.util.HashMap;
import java.util.Map;

// Google
public class MostStonesRemovedWithSameRowOrColumn {
    public static void main(String[] args) {
        var ob = new MostStonesRemovedWithSameRowOrColumn();
        System.out.println(ob.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}) == 5);
        System.out.println(ob.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}) == 3);
        System.out.println(ob.removeStones(new int[][]{{0, 0}}) == 0);
    }

    /*
    Row numbers are taken as negative to differentiate them from column numbers. This distinction is necessary because 
    both row and column indices are represented as integers, and there could be overlapping values between rows and 
    columns. If they were both treated as positive integers, there would be a risk of collision, where a row and a 
    column might be mistaken for the same entity
     */
    public int removeStones(int[][] stones) {
        var ob = new UnionFind(stones);

        for (var stone : stones)
            ob.union(-(stone[0] + 1), stone[1] + 1);

        return stones.length - ob.components;
    }

    private static class UnionFind {
        private final Map<Integer, Integer> parent = new HashMap<>();
        private final Map<Integer, Integer> rank = new HashMap<>();
        private int components;

        private UnionFind(int[][] stones) {
            for (var stone : stones) {
                var row = -(stone[0] + 1);
                var col = stone[1] + 1;
                parent.put(row, row);
                parent.put(col, col);
            }

            components = parent.size();
        }

        private int find(int child) {
            if (child != parent.get(child))
                parent.put(child, find(parent.get(child)));

            return parent.get(child);
        }

        private void union(int x, int y) {
            var parentX = find(x);
            var parentY = find(y);

            if (parentX == parentY)
                return;

            var rankParentX = rank.getOrDefault(parentX, 0);
            var rankParentY = rank.getOrDefault(parentY, 0);
            components--;

            if (rankParentX > rankParentY) {
                parent.put(parentY, parentX);
            } else if (rankParentX < rankParentY) {
                parent.put(parentX, parentY);
            } else {
                rank.compute(parentY, (k, v) -> v == null ? 1 : ++v);
                parent.put(parentX, parentY);
            }
        }
    }
}
