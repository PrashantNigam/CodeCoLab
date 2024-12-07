package Graphs.Dijkstras;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumTimeToVisitACellInAGrid {
    public static void main(String[] args) {
        var ob = new MinimumTimeToVisitACellInAGrid();
        System.out.println(ob.minimumTime(new int[][]{{0, 1, 3, 2}, {5, 1, 2, 5}, {4, 3, 8, 6}}) == 7);
        System.out.println(ob.minimumTime(new int[][]{{0, 2, 4}, {3, 2, 1}, {1, 0, 4}}) == -1);
    }

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private record Node(int row, int col, int time) {}

    /**
     * You start at t = 0 at (0,0)
     * You must move 1 step every second
     * If bottom and right neighbors of (0,0) > 1, then it's impossible to reach target
     *
     * @param grid matrix of whole numbers representing clock time before which you can't visit each cell.
     * @return the minimum time required in which you can visit the bottom-right cell of the matrix.
     * If you cannot visit the bottom-right cell, then return -1
     */
    public int minimumTime(int[][] grid) {
        if (Math.min(grid[0][1], grid[1][0]) > 1)
            return -1;

        var times = initTimes(grid);
        var visited = new boolean[grid.length][grid[0].length];
        var q = new PriorityQueue<Node>(Comparator.comparing(node -> node.time));
        q.add(new Node(0, 0, 0));

        while (!q.isEmpty()) {
            var node = q.poll();

            if (visited[node.row][node.col])
                continue;

            visited[node.row][node.col] = true;

            for (var dir : DIRS) {
                var x = node.row + dir[0];
                var y = node.col + dir[1];

                if (outOfBounds(grid, x, y))
                    continue;

                var nextTime = 0;
                var diff = grid[x][y] - node.time;

                if (grid[x][y] <= node.time + 1)
                    nextTime = node.time + 1;
                else if ((diff & 1) == 0)
                    nextTime = grid[x][y] + 1;
                else
                    nextTime = grid[x][y];
                if (times[x][y] <= nextTime)
                    continue;

                times[x][y] = nextTime;
                q.add(new Node(x, y, nextTime));
            }
        }

        return times[times.length - 1][times[0].length - 1];
    }

    private int[][] initTimes(int[][] grid) {
        var distance = new int[grid.length][grid[0].length];

        for (var row : distance)
            Arrays.fill(row, Integer.MAX_VALUE);

        distance[0][0] = 0;
        return distance;
    }

    private boolean outOfBounds(int[][] A, int row, int column) {
        return row == -1 || column == -1 || row == A.length || column == A[0].length;
    }
}
