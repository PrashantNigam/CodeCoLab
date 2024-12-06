package Graphs.BFS;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

// Airbnb Amazon Google Uber
public class SlidingPuzzle {
    public static void main(String[] args) {
        var ob = new SlidingPuzzle();
        System.out.println(ob.slidingPuzzle(new int[][]{{1, 2, 3}, 
                                                        {4, 0, 5}}) == 1);
        System.out.println(ob.slidingPuzzle(new int[][]{{1, 2, 3}, 
                                                        {5, 4, 0}}) == -1);
        System.out.println(ob.slidingPuzzle(new int[][]{{4, 1, 2}, 
                                                        {5, 0, 3}}) == 5);
    }

    private static final List<Integer> END = List.of(1, 2, 3, 4, 5, 0);
    private static final Map<Integer, List<Integer>> ADJACENCY_LIST = Map.of(0, List.of(1, 3),
                                                                             1, List.of(0, 2, 4),
                                                                             2, List.of(1, 5),
                                                                             3, List.of(0, 4),
                                                                             4, List.of(1, 3, 5),
                                                                             5, List.of(2, 4));
    public int slidingPuzzle(int[][] board) {
        var start = getStart(board);
        var q = new ArrayDeque<>(List.of(start));
        var visited = new HashSet<>(List.of(start));

        for (var moves = 0; !q.isEmpty(); moves++)
            for (var i = q.size(); i > 0; i--) {
                var head = q.poll();

                if (head.equals(END))
                    return moves;

                var zeroIndex = head.indexOf(0);

                for (var neighborIndex : ADJACENCY_LIST.get(zeroIndex)) {
                    var next = new ArrayList<>(head);
                    Collections.swap(next, zeroIndex, neighborIndex);
                    if (visited.add(next))
                        q.add(next);
                }
            }

        return -1;
    }

    private List<Integer> getStart(int[][] board) {
        return Arrays.stream(board)
                     .flatMapToInt(Arrays::stream)
                     .boxed()
                     .collect(Collectors.toCollection(ArrayList::new));
    }
}
