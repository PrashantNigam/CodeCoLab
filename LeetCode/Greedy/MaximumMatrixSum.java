package Matrix;

public class MaximumMatrixSum {
    public static void main(String[] args) {
        var ob = new MaximumMatrixSum();
        System.out.println(ob.maxMatrixSum(new int[][]{{1, -1}, {-1, 1}}) == 4);
        System.out.println(ob.maxMatrixSum(new int[][]{{1, 2, 3}, {-1, -2, -3}, {1, 2, 3}}) == 16);
    }

    public long maxMatrixSum(int[][] matrix) {
        var sum = 0L;
        var negatives = 0;
        var min = Long.MAX_VALUE;

        for (var row : matrix)
            for (var x : row) {
                if (x < 0)
                    negatives++;

                x = Math.abs(x);
                sum += x;
                min = Math.min(min, x);
            }

        if ((negatives & 1) == 1)
            sum -= min << 1;

        return sum;
    }
}
