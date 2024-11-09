package DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimumTotalDistanceTraveled {
    public static void main(String[] args) {
        var ob = new MinimumTotalDistanceTraveled();
        System.out.println(ob.minimumTotalDistance(new ArrayList<>(List.of(0, 4, 6)), new int[][]{{2, 2}, {6, 2}}) == 4);
        System.out.println(ob.minimumTotalDistance(new ArrayList<>(List.of(1, -1)), new int[][]{{-2, 1}, {2, 1}}) == 2);
    }

    public long minimumTotalDistance(List<Integer> robots, int[][] factories) {
        Collections.sort(robots);
        Arrays.sort(factories, Comparator.comparingInt(factory -> factory[0]));
        var flattened = flatten(factories);
        return dfs(robots, flattened, 0, 0, new Long[robots.size()][flattened.size()]);
    }

    private List<Integer> flatten(int[][] factories) {
        var factoryPositions = new ArrayList<Integer>();

        for (var factory : factories)
            for (var i = 0; i < factory[1]; i++)
                factoryPositions.add(factory[0]);

        return factoryPositions;
    }

    private long dfs(List<Integer> robot, List<Integer> factoryPositions, int robotIdx, int factoryIdx, Long[][] memo) {
        if (robotIdx == robot.size())
            return 0;
        if (factoryIdx == factoryPositions.size())
            return Long.MAX_VALUE;
        if (memo[robotIdx][factoryIdx] != null)
            return memo[robotIdx][factoryIdx];

        var skip = dfs(robot, factoryPositions, robotIdx, factoryIdx + 1, memo);
        var assign = getAssignDistance(robot, factoryPositions, robotIdx, factoryIdx, memo);
        return memo[robotIdx][factoryIdx] = Math.min(skip, assign);
    }

    private long getAssignDistance(List<Integer> robot, List<Integer> factoryPositions, int robotIdx, int factoryIdx, Long[][] memo) {
        var distance = Math.abs(robot.get(robotIdx) - factoryPositions.get(factoryIdx));
        var nextDistance = dfs(robot, factoryPositions, robotIdx + 1, factoryIdx + 1, memo);
        return nextDistance == Long.MAX_VALUE ? Long.MAX_VALUE : distance + nextDistance;
    }
}
