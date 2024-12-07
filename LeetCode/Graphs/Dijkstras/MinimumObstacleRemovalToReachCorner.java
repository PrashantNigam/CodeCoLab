package Graphs.Dijkstras;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumObstacleRemovalToReachCorner {
    public static void main(String[] args) {
        var ob = new MinimumObstacleRemovalToReachCorner();
        System.out.println(ob.minimumObstacles(new int[][]{{0, 1, 1},
                                                           {1, 1, 0},
                                                           {1, 1, 0}}) == 2);
      
        System.out.println(ob.minimumObstacles(new int[][]{{0, 1, 0, 0, 0},
                                                           {0, 1, 0, 1, 0},
                                                           {0, 0, 0, 1, 0}}) == 0);
    }

    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    private record Node(int row, int col, int distance) {}

    public int minimumObstacles(int[][] grid) {
        var distance = initDistance(grid);
        var q = new PriorityQueue<Node>(Comparator.comparing(node -> node.distance));
        q.add(new Node(0, 0, 0));

        while (!q.isEmpty()) {
            var node = q.poll();

            for (var dir : DIRS) {
                var x = node.row + dir[0];
                var y = node.col + dir[1];

                if (outOfBounds(grid, x, y))
                    continue;

                var nextDistance = node.distance + grid[x][y];

                if (distance[x][y] <= nextDistance)
                    continue;

                distance[x][y] = nextDistance;
                q.add(new Node(x, y, nextDistance));
            }
        }

        return distance[grid.length - 1][grid[0].length - 1];
    }

    private int[][] initDistance(int[][] grid) {
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
