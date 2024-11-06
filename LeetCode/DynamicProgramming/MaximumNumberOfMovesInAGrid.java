package DynamicProgramming;

public class MaximumNumberOfMovesInAGrid {
    public static void main(String[] args) {
        var ob = new MaximumNumberOfMovesInAGrid();
        System.out.println(ob.maxMoves(new int[][]{{2, 4, 3, 5},
                                                   {5, 4, 9, 3},
                                                   {3, 4, 2, 11},
                                                   {10, 9, 13, 15}}) == 3);
        System.out.println(ob.maxMoves(new int[][]{{3, 2, 4},
                                                   {2, 1, 9},
                                                   {1, 1, 7}}) == 0);
    }

    private static final int[] DIRS = {-1, 0, 1};

    public int maxMoves(int[][] grid) {
        var moves = 0;
        var m = grid.length;
        var n = grid[0].length;
        var dp = new Integer[m][n];

        for (var i = 0; i < m; i++)
            moves = Math.max(moves, dfs(grid, m, n, i, 0, 0, dp));

        return moves;
    }

    private int dfs(int[][] grid, int m, int n, int i, int j, int prev, Integer[][] dp) {
        if (i == -1 || i == m || j == -1 || j == n || prev >= grid[i][j])
            return -1;
        if (dp[i][j] != null)
            return dp[i][j];

        var moves = 0;

        for (var dir : DIRS) {
            var nextMoves = dfs(grid, m, n, i + dir, j + 1, grid[i][j], dp);

            if (nextMoves == -1)
                continue;

            moves = Math.max(moves, 1 + nextMoves);
        }

        return dp[i][j] = moves;
    }
}
