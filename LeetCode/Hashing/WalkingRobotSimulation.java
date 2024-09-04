package Hashing;

import java.util.HashSet;
import java.util.Set;

// JaneStreet
public class WalkingRobotSimulation {
    public static void main(String[] args) {
        var ob = new WalkingRobotSimulation();
        System.out.println(ob.robotSim(new int[]{4, -1, 3}, new int[][]{}) == 25);
        System.out.println(ob.robotSim(new int[]{4, -1, 4, -2, 4}, new int[][]{{2, 4}}) == 65);
        System.out.println(ob.robotSim(new int[]{6, -1, -1, 6}, new int[][]{}) == 36);
    }

    public int robotSim(int[] commands, int[][] obstacles) {
        var robot = new Robot(obstacles);

        for (var command : commands)
            switch (command) {
                case -1 -> robot.turnRight();
                case -2 -> robot.turnLeft();
                default -> robot.move(command);
            }

        return robot.maxDistance;
    }

    private static class Robot {
        private int abscissa;
        private int ordinate;
        private int direction;
        private int maxDistance;
        private final Set<Square> obstacleSet = new HashSet<>();
        private record Square(int x, int y) {}

        private static final int[][] DIRECTIONS = {
                {0, 1},//North
                {1, 0}, // East
                {0, -1}, // South
                {-1, 0} // West
        };

        public Robot(int[][] obstacles) {
            for (var obstacle : obstacles)
                obstacleSet.add(new Square(obstacle[0], obstacle[1]));
        }

        public void turnLeft() {
            direction = (direction + 3) % 4;
        }

        public void turnRight() {
            direction = (direction + 1) % 4;
        }

        public void move(int steps) {
            var stepX = DIRECTIONS[direction][0];
            var stepY = DIRECTIONS[direction][1];

            while (steps-- > 0) {

                var nextX = abscissa + stepX;
                var nextY = ordinate + stepY;

                if (obstacleSet.contains(new Square(nextX, nextY)))
                    break;

                abscissa = nextX;
                ordinate = nextY;
                var euclideanDistance = abscissa * abscissa + ordinate * ordinate;
                maxDistance = Math.max(maxDistance, euclideanDistance);
            }
        }
    }
}
