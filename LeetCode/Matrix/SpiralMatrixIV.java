package Matrix;

import LinkedList.ListNode;
import java.util.Arrays;
import static LinkedList.ListNode.construct;

public class SpiralMatrixIV {
    public static void main(String[] args) {
        var ob = new SpiralMatrixIV();
        System.out.println(Arrays.deepEquals(ob.spiralMatrix(3, 5, construct(3, 0, 2, 6, 8, 1, 7, 9, 4, 2, 5, 5, 0)),
                new int[][]{{3, 0, 2, 6, 8},
                            {5, 0, -1, -1, 1},
                            {5, 2, 4, 9, 7}}));
        System.out.println(Arrays.deepEquals(ob.spiralMatrix(1, 4, construct(0, 1, 2)), new int[][]{{0, 1, 2, -1}}));
    }

    public int[][] spiralMatrix(int m, int n, ListNode head) {
        var firstRow = 0;
        var firstCol = 0;
        var lastRow = m - 1;
        var lastCol = n - 1;
        var node = new ListNode[]{head};
        var matrix = new int[m][n];

        while (firstRow <= lastRow && firstCol <= lastCol) {
            firstRow = fillFirstRow(firstCol, lastCol, matrix, firstRow, node);
            lastCol = fillLastColumn(firstRow, lastRow, matrix, lastCol, node);

            if (firstRow > lastRow || firstCol > lastCol)
                break;

            lastRow = fillLastRow(lastCol, firstCol, matrix, lastRow, node);
            firstCol = fillFirstColumn(lastRow, firstRow, matrix, firstCol, node);
        }

        return matrix;
    }

    private int fillFirstRow(int firstCol, int lastCol, int[][] matrix, int firstRow, ListNode[] node) {
        for (var i = firstCol; i <= lastCol; i++)
            matrix[firstRow][i] = val(node);

        return ++firstRow;
    }

    private int fillLastColumn(int firstRow, int lastRow, int[][] matrix, int lastCol, ListNode[] node) {
        for (var i = firstRow; i <= lastRow; i++)
            matrix[i][lastCol] = val(node);

        return --lastCol;
    }

    private int fillLastRow(int lastCol, int firstCol, int[][] matrix, int lastRow, ListNode[] node) {
        for (var i = lastCol; i >= firstCol; i--)
            matrix[lastRow][i] = val(node);

        return --lastRow;
    }

    private int fillFirstColumn(int lastRow, int firstRow, int[][] matrix, int firstCol, ListNode[] node) {
        for (var i = lastRow; i >= firstRow; i--)
            matrix[i][firstCol] = val(node);

        return ++firstCol;
    }

    private int val(ListNode[] node) {
        if (node[0] == null)
            return -1;

        var val = node[0].val;
        node[0] = node[0].next;
        return val;
    }
}