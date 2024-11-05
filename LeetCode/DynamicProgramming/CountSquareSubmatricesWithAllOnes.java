package DynamicProgramming;

// Google
public class CountSquareSubmatricesWithAllOnes {
    public static void main(String[] args) {
        var ob = new CountSquareSubmatricesWithAllOnes();
        System.out.println(ob.countSquares(new int[][]{{0, 1, 1, 1},
                                                       {1, 1, 1, 1},
                                                       {0, 1, 1, 1}}) == 15);
        System.out.println(ob.countSquares(new int[][]{{1, 0, 1},
                                                       {1, 1, 0},
                                                       {1, 1, 0}}) == 7);
    }

    public int countSquares(int[][] matrix) {
        var m = matrix.length;
        var n = matrix[0].length;
        var dp = new int[m][n];
        var count = fillFirstRow(matrix, m, dp) + fillFirstColumn(matrix, n, dp);

        if (matrix[0][0] == 1)
            count--; // double count adjustment

        for (var i = 1; i < m; i++)
            for (var j = 1; j < n; j++) {
                if (matrix[i][j] == 0)
                    continue;

                var top = dp[i - 1][j];
                var left = dp[i][j - 1];
                var topLeft = dp[i - 1][j - 1];
                count += dp[i][j] = 1 + min(top, left, topLeft);
            }

        return count;
    }

    private int fillFirstRow(int[][] matrix, int m, int[][] allOnes) {
        var ones = 0;

        for (var i = 0; i < m; i++) {
            if (matrix[i][0] == 0)
                continue;

            allOnes[i][0] = 1;
            ones++;
        }

        return ones;
    }

    private int fillFirstColumn(int[][] matrix, int n, int[][] allOnes) {
        var ones = 0;

        for (var j = 0; j < n; j++) {
            if (matrix[0][j] == 0)
                continue;

            allOnes[0][j] = 1;
            ones++;
        }

        return ones;
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }
}
