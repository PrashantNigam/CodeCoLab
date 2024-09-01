package Matrix;

import java.util.Arrays;

// Google
public class Convert1DArrayInto2DArray {
    public static void main(String[] args) {
        var ob = new Convert1DArrayInto2DArray();
        System.out.println(Arrays.deepEquals(ob.construct2DArray(new int[]{1, 2, 3, 4}, 2, 2), new int[][]{{1, 2}, {3, 4}}));
        System.out.println(Arrays.deepEquals(ob.construct2DArray(new int[]{1, 2}, 1, 1), new int[][]{}));
    }

    private static final int[][] EMPTY = new int[][]{};

    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n)
            return EMPTY;

        var k = 0;
        var matrix = new int[m][n];

        for (var i = 0; i < m; i++)
            for (var j = 0; j < n; j++)
                matrix[i][j] = original[k++];

        return matrix;
    }
}