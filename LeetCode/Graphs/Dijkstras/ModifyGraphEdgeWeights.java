package Graphs.Dijkstras;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;

public class ModifyGraphEdgeWeights {
    public static void main(String[] args) {
        var ob = new ModifyGraphEdgeWeights();
        System.out.println(Arrays.deepEquals(
                ob.modifiedGraphEdges(5, new int[][]{{4, 1, -1}, {2, 0, -1}, {0, 3, -1}, {4, 3, -1}}, 0, 1, 5),
                new int[][]{{4, 1, 1}, {2, 0, 1}, {0, 3, 1}, {4, 3, 3}}));
        System.out.println(Arrays.deepEquals(
                ob.modifiedGraphEdges(3, new int[][]{{0, 1, -1}, {0, 2, 5}}, 0, 2, 6),
                new int[][]{}));
        System.out.println(Arrays.deepEquals(
                ob.modifiedGraphEdges(4, new int[][]{{1, 0, 4}, {1, 2, 3}, {2, 3, 5}, {0, 3, -1}}, 0, 2, 6),
                new int[][]{{1, 0, 4}, {1, 2, 3}, {2, 3, 5}, {0, 3, 1}}));
    }

    private static final int MAX_DISTANCE = (int) (2 * 1e9);
    private static final int[][] EMPTY = new int[][]{};
    private record Pair(int node, int distance) {}

    /**
     * When we first create the adjacency list, we don't include the edges with missing weights (i.e. weight = -1),
     * yet if there exists a path from source to destination with path sum less than the target without those edges,
     * then this means, we don't need those edges for the shortest paths.
     * Any more distance you add to the already shorter distance will only increase the distance, thus it won't remain
     * the shortest distance path which you already had achieved without the missing-weight edges.
     * This is our first trivial case.
     * <p>
     * With the same graph without the missing weight edges, if the path sum from source to destination is already
     * equal to target, then the missing weights can be assigned infinity value and the edges can be returned as they are.
     * This is our second trivial case.
     */
    public int[][] modifiedGraphEdges(int n, int[][] edges, int source, int destination, int target) {
        var graph = getAdjacencyList(n, edges);
        var pathSum = shortestDistance(graph, n, source, destination);

        if (pathSum < target)
            return EMPTY;
        if (pathSum == target)
            return fillMissingDistances(edges);

        for (var edge : edges) {
            if (edge[2] != -1)
                continue;

            edge[2] = 1;
            graph.get(edge[0]).add(new Pair(edge[1], 1));
            graph.get(edge[1]).add(new Pair(edge[0], 1));
            pathSum = shortestDistance(graph, n, source, destination);

            if (pathSum > target)
                continue;

            edge[2] += target - pathSum; // remaining weight
            return fillMissingDistances(edges);
        }

        return EMPTY;
    }

    /**
     * @return The shortest distance between source and destination using Dijkstra's Shortest Path Algorithm
     */
    private int shortestDistance(List<List<Pair>> graph, int n, int source, int destination) {
        var distance = new int[n];
        var visited = new HashSet<Integer>();
        var q = new PriorityQueue<Pair>(Comparator.comparingInt(edge -> edge.distance));

        q.add(new Pair(source, 0));
        Arrays.fill(distance, MAX_DISTANCE);
        distance[source] = 0;

        while (!q.isEmpty()) {
            var pair = q.poll();

            if (!visited.add(pair.node))
                continue;
            if (pair.node == destination)
                return pair.distance;

            for (var next : graph.get(pair.node)) {
                var newDistance = pair.distance + next.distance;

                if (newDistance >= distance[next.node])
                    continue;

                distance[next.node] = newDistance;
                q.add(new Pair(next.node, newDistance));
            }
        }

        return distance[destination];
    }

    private List<List<Pair>> getAdjacencyList(int n, int[][] edges) {
        var graph = new ArrayList<List<Pair>>();

        for (var i = 0; i < n; i++)
            graph.add(new ArrayList<>());

        for (var edge : edges) {
            if (edge[2] == -1)
                continue;

            graph.get(edge[0]).add(new Pair(edge[1], edge[2]));
            graph.get(edge[1]).add(new Pair(edge[0], edge[2]));
        }

        return graph;
    }

    private int[][] fillMissingDistances(int[][] edges) {
        for (var edge : edges)
            if (edge[2] == -1)
                edge[2] = MAX_DISTANCE;

        return edges;
    }
}