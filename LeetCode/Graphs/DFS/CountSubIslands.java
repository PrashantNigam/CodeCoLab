package Graphs.DFS;

import java.util.Arrays;

// Amazon Apple Meta Microsoft Google
public class CountSubIslands {
    public static void main(String[] args) {
        var ob = new CountSubIslands();
        System.out.println(ob.countSubIslands(new int[][]{
                        {1, 1, 1, 0, 0},
                        {0, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0},
                        {1, 1, 0, 1, 1}},
                new int[][]{
                        {1, 1, 1, 0, 0},
                        {0, 0, 1, 1, 1},
                        {0, 1, 0, 0, 0},
                        {1, 0, 1, 1, 0},
                        {0, 1, 0, 1, 0}}) == 3);
        System.out.println(ob.countSubIslands(new int[][]{
                        {1, 0, 1, 0, 1},
                        {1, 1, 1, 1, 1},
                        {0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1},
                        {1, 0, 1, 0, 1}},
                new int[][]{
                        {0, 0, 0, 0, 0},
                        {1, 1, 1, 1, 1},
                        {0, 1, 0, 1, 0},
                        {0, 1, 0, 1, 0},
                        {1, 0, 0, 0, 1}}) == 2);
    }

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    /**
     * Conditions for sub
     * 1. If boundary is reached or grid2 is water, then it counts as a sub
     * 2. If grid1 is water, then grid2 can't be a sub
     * 3. grid2 can be a sub of grid1 iff grid1 is land else not. grid2 need not be land
     * 4. Recursively all 4 neighbors must hold these conditions
     * 5. Don't use logical AND (&&) instead of bitwise AND (&). Logical AND will cause logical short-circuiting at the
     * first false value and the remaining calls won't execute. If the calls don't execute, then the 0s won't get
     * marked as visited/water which will fail the logic when using loop. For some reason, this is not applicable
     * on map-reduce
     */
    public int countSubIslands(int[][] grid1, int[][] grid2) {
        var subs = 0;
        var m = grid1.length;
        var n = grid1[0].length;

        for (var i = 0; i < m; i++)
            for (var j = 0; j < n; j++)
                if (grid2[i][j] == 1 && isSubIsland(grid1, grid2, m, n, i, j))
                    subs++;

        return subs;
    }

    private boolean isSubIsland(int[][] grid1, int[][] grid2, int m, int n, int i, int j) {
        if (i == -1 || i == m || j == -1 || j == n || grid2[i][j] == 0)
            return true;
        if (grid1[i][j] == 0)
            return false;

        grid2[i][j] = 0;

        return Arrays.stream(DIRS)
                     .map(dir -> isSubIsland(grid1, grid2, m, n, i + dir[0], j + dir[1]))
                     .reduce(true, (a, b) -> a && b);

        /*
        Note: The last map-reduce line is just a 1 liner for

        var isSub = true;
        for (var dir : DIRS)
            isSub &= isSubIsland(grid1, grid2, i + dir[0], j + dir[1]);
        */
    }
}
