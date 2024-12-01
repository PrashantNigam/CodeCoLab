package Graphs.DFS;

public class CountUnguardedCellsInTheGrid {
    public static void main(String[] args) {
        var ob = new CountUnguardedCellsInTheGrid();
        System.out.println(ob.countUnguarded(4, 6, new int[][]{{0, 0}, {1, 1}, {2, 3}},
                                                   new int[][]{{0, 1}, {2, 2}, {1, 4}}) == 7);
        System.out.println(ob.countUnguarded(3, 3, new int[][]{{1, 1}},
                                                   new int[][]{{0, 1}, {1, 0}, {2, 1}, {1, 2}}) == 4);
    }

    private static final char GUARD = 'G';
    private static final char GUARDED = 'P';
    private static final char WALL = 'W';
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int countUnguarded(int m, int n, int[][] guards, int[][] walls) {
        var guarded = new int[1];
        var grid = new char[m][n];

        Arrays.stream(walls).forEach(wall -> grid[wall[0]][wall[1]] = WALL);
        Arrays.stream(guards).forEach(guard -> grid[guard[0]][guard[1]] = GUARD);
        Arrays.stream(guards).forEach(guard -> Arrays.stream(DIRS)
                                                     .forEach(dir -> dfs(guard[0] + dir[0], guard[1] + dir[1], m, n, grid, guarded, dir)));

        return m * n - guards.length - walls.length - guarded[0];
    }

    private void dfs(int i, int j, int m, int n, char[][] grid, int[] guarded, int[] dir) {
        if (i == -1 || j == -1 || i == m || j == n || grid[i][j] == WALL || grid[i][j] == GUARD)
            return;

        if (grid[i][j] != GUARDED) {
            grid[i][j] = GUARDED;
            guarded[0]++;
        }

        dfs(i + dir[0], j + dir[1], m, n, grid, guarded, dir);
    }
}
