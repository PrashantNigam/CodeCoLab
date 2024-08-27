package Graphs.Dijkstras;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

// Google
public class PathWithMaximumProbability {
    public static void main(String[] args) {
        var ob = new PathWithMaximumProbability();
        System.out.println(ob.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.2},
                0, 2) == 0.25);
        System.out.println(ob.maxProbability(3, new int[][]{{0, 1}, {1, 2}, {0, 2}}, new double[]{0.5, 0.5, 0.3},
                0, 2) == 0.3);
        System.out.println(ob.maxProbability(3, new int[][]{{0, 1}}, new double[]{0.5}, 0, 2) == 0);
    }

    private record Pair(int node, double probability) {}

    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        var graph = getAdjList(n, edges, succProb);
        var visited = new HashSet<Integer>();
        var maxHeap = new PriorityQueue<Pair>(Comparator.comparing(pair -> -pair.probability));
        maxHeap.add(new Pair(start, 1));

        while (!maxHeap.isEmpty()) {
            var pair = maxHeap.poll();
            visited.add(pair.node);

            if (pair.node == end)
                return pair.probability;

            for (var next : graph.get(pair.node))
                if (!visited.contains(next.node))
                    maxHeap.add(new Pair(next.node, pair.probability * next.probability));
        }

        return 0;
    }

    private Map<Integer, List<Pair>> getAdjList(int n, int[][] edges, double[] succProb) {
        var graph = new HashMap<Integer, List<Pair>>();

        for (var i = 0; i < n; i++)
            graph.put(i, new ArrayList<>());

        for (var i = 0; i < edges.length; i++) {
            var edge = edges[i];
            graph.get(edge[0]).add(new Pair(edge[1], succProb[i]));
            graph.get(edge[1]).add(new Pair(edge[0], succProb[i]));
        }

        return graph;
    }
}
