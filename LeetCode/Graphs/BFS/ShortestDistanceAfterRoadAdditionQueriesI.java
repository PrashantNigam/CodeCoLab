package Graphs.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShortestDistanceAfterRoadAdditionQueriesI {
    public static void main(String[] args) {
        var ob = new ShortestDistanceAfterRoadAdditionQueriesI();
        System.out.println(Arrays.equals(ob.shortestDistanceAfterQueries(5, new int[][]{{2, 4}, {0, 2}, {0, 4}}), 
                                         new int[]{3, 2, 1}));
        System.out.println(Arrays.equals(ob.shortestDistanceAfterQueries(4, new int[][]{{0, 3}, {0, 2}}), 
                                         new int[]{1, 1}));
    }

    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        var graph = getAdjacencyList(n);
        return Arrays.stream(queries)
                     .mapToInt(query -> execute(n, query, graph))
                     .toArray();
    }

    private Map<Integer, List<Integer>> getAdjacencyList(int n) {
        var graph = new HashMap<Integer, List<Integer>>();
        IntStream.range(0, n - 1)
                 .forEach(i -> graph.compute(i, (_, _) -> new ArrayList<>())
                                    .add(i + 1));
        return graph;
    }

    private int execute(int n, int[] query, Map<Integer, List<Integer>> graph) {
        var visited = new boolean[n];
        var q = new ArrayDeque<>(List.of(0));
        graph.get(query[0])
             .add(query[1]);

        for (var distance = 0; !q.isEmpty(); distance++)
            for (var i = q.size(); i > 0; i--) {
                var head = q.poll();

                if (head == n - 1)
                    return distance;

                for (var next : graph.get(head)) {
                    if (visited[next])
                        continue;
                  
                    q.add(next);
                    visited[next] = true;
                }
            }

        return 0;
    }
}
