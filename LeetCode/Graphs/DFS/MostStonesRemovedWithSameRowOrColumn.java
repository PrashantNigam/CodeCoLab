package Graphs.DFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Google
public class MostStonesRemovedWithSameRowOrColumn {
    public static void main(String[] args) {
        var ob = new MostStonesRemovedWithSameRowOrColumn();
        System.out.println(ob.removeStones(new int[][]{{0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}}) == 5);
        System.out.println(ob.removeStones(new int[][]{{0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}}) == 3);
        System.out.println(ob.removeStones(new int[][]{{0, 0}}) == 0);
    }

    private record Point(int x, int y) {}

    public int removeStones(int[][] stones) {
        var remain = 0;
        var visited = new HashSet<Point>();
        var graph = getAdjacencyList(stones);

        for (var node : graph.keySet())
            if (dfs(node, graph, visited))
                remain++;

        return stones.length - remain;
    }

    private Map<Point, List<Point>> getAdjacencyList(int[][] stones) {
        var graph = new HashMap<Point, List<Point>>();

        for (var stone : stones) {
            var neighbors = graph.compute(new Point(stone[0], stone[1]), (k, v) -> new ArrayList<>());

            for (var other : stones) {
                if (stone[0] == other[0] && stone[1] == other[1]) // same stone
                    continue;
                if (stone[0] == other[0] || stone[1] == other[1]) // neighbor stone
                    neighbors.add(new Point(other[0], other[1]));
            }
        }

        return graph;
    }

    private boolean dfs(Point node, Map<Point, List<Point>> adjList, Set<Point> visited) {
        if (!visited.add(node))
            return false;

        for (var neighbor : adjList.get(node))
            dfs(neighbor, adjList, visited);

        return true;
    }
}
