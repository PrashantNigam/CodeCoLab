package Hashing;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class FlipColumnsForMaximumNumberOfEqualRows {
    public static void main(String[] args) {
        var ob = new FlipColumnsForMaximumNumberOfEqualRows();
        System.out.println(ob.maxEqualRowsAfterFlips(new int[][]{{0, 1}, {1, 1}}) == 1);
        System.out.println(ob.maxEqualRowsAfterFlips(new int[][]{{0, 1}, {1, 0}}) == 2);
        System.out.println(ob.maxEqualRowsAfterFlips(new int[][]{{0, 0, 0}, {0, 0, 1}, {1, 1, 0}}) == 2);
    }

    public int maxEqualRowsAfterFlips(int[][] matrix) {
        var rowCount = new HashMap<List<Integer>, Integer>();

        for (var row : matrix) {
            var initial = row[0];
            var wave = new ArrayList<Integer>();

            for (var val : row)
                wave.add(val == initial ? 1 : 0);

            rowCount.compute(wave, (_, v) -> v == null ? 1 : ++v);
        }

        return Collections.max(rowCount.values());
    }

    // Would work iff number of columns in matrix <= 64
    public int maxEqualRowsAfterFlips2(int[][] matrix) {
        var rowCount = new HashMap<Long, Integer>();

        for (var row : matrix) {
            var initial = row[0];
            var x = 0L;

            for (var val : row) {
                if (val == initial)
                    x |= 1;
                x <<= 1;
            }

            rowCount.compute(x, (_, v) -> v == null ? 1 : ++v);
        }

        return Collections.max(rowCount.values());
    }

    // Brute Force
    public int maxEqualRowsAfterFlips3(int[][] matrix) {
        var maxIdenticalRows = 0;

        for (var row : matrix) {
            var flippedRow = Arrays
                    .stream(row)
                    .map(val -> 1 - val)
                    .toArray();

            var equals = (int) Arrays
                    .stream(matrix)
                    .filter(compareRow -> Arrays.equals(compareRow, row) || Arrays.equals(compareRow, flippedRow))
                    .count();

            maxIdenticalRows = Math.max(maxIdenticalRows, equals);
        }

        return maxIdenticalRows;
    }
}
