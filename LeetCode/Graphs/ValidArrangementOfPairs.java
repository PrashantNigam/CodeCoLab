package Graphs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class ValidArrangementOfPairs {
    public static void main(String[] args) {
        var ob = new ValidArrangementOfPairs();
        System.out.println(Arrays.deepEquals(ob.validArrangement(new int[][]{{5, 1}, {4, 5}, {11, 9}, {9, 4}}), 
                                                                 new int[][]{{11, 9}, {9, 4}, {4, 5}, {5, 1}}));
        System.out.println(Arrays.deepEquals(ob.validArrangement(new int[][]{{1, 3}, {3, 2}, {2, 1}}), 
                                                                 new int[][]{{1, 3}, {3, 2}, {2, 1}}));
        System.out.println(Arrays.deepEquals(ob.validArrangement(new int[][]{{1, 2}, {1, 3}, {2, 1}}), 
                                                                 new int[][]{{1, 2}, {2, 1}, {1, 3}}));
    }

    public int[][] validArrangement(int[][] pairs) {
        var path = new ArrayDeque<Integer>();
        var inDegrees = new HashMap<Integer, Integer>();
        var outDegrees = new HashMap<Integer, Integer>();
        var graph = new HashMap<Integer, Deque<Integer>>();
        var start = getStartOfArrangement(pairs, graph, outDegrees, inDegrees);
        var stack = new ArrayDeque<>(List.of(start));

        while (!stack.isEmpty()) {
            var next = graph.get(stack.peek());

            if (next.isEmpty())
                path.add(stack.pop());
            else
                stack.push(next.pop());
        }

        return createArrangement(pairs.length, path);
    }

    private int getStartOfArrangement(int[][] pairs, Map<Integer, Deque<Integer>> graph, Map<Integer, Integer> outDegrees, Map<Integer, Integer> inDegrees) {
        for (var pair : pairs) {
            graph.put(pair[0], new ArrayDeque<>());
            graph.put(pair[1], new ArrayDeque<>());
            inDegrees.put(pair[0], 0);
            inDegrees.put(pair[1], 0);
            outDegrees.put(pair[0], 0);
            outDegrees.put(pair[1], 0);
        }

        for (var pair : pairs) {
            graph.get(pair[0]).add(pair[1]);
            outDegrees.compute(pair[0], (_, v) -> ++v);
            inDegrees.compute(pair[1], (_, v) -> ++v);
        }

        return graph.keySet()
                    .stream()
                    .filter(key -> outDegrees.get(key) - inDegrees.get(key) == 1)
                    .findFirst()
                    .orElse(pairs[0][0]);
    }

    private int[][] createArrangement(int n, Deque<Integer> path) {
        return IntStream.range(0, n)
                        .mapToObj(_ -> new int[]{path.pollLast(), path.peekLast()})
                        .toArray(int[][]::new);
    }
}
