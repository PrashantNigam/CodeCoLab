package Matrix;

import java.util.Arrays;

// ByteDance CapitalOne eBay Netflix Square Uber
public class RotatingTheBox {
    public static void main(String[] args) {
        var ob = new RotatingTheBox();
        System.out.println(Arrays.deepEquals(ob.rotateTheBox(new char[][]{
                {'#', '.', '#'}}), new char[][]{
                {'.'},
                {'#'},
                {'#'}}));
        System.out.println(Arrays.deepEquals(ob.rotateTheBox(new char[][]{
                {'#', '.', '*', '.'},
                {'#', '#', '*', '.'}
        }), new char[][]{
                {'#', '.'},
                {'#', '#'},
                {'*', '*'},
                {'.', '.'}}));
        System.out.println(Arrays.deepEquals(ob.rotateTheBox(new char[][]{
                {'#', '#', '*', '.', '*', '.'},
                {'#', '#', '#', '*', '.', '.'},
                {'#', '#', '#', '.', '#', '.'},
        }), new char[][]{
                {'.', '#', '#'},
                {'.', '#', '#'},
                {'#', '#', '*'},
                {'#', '*', '.'},
                {'#', '.', '*'},
                {'#', '.', '.'}}));
    }

    /*
    2 pointers. O(mn)/O(mn)
    0-th row becomes the last col in the newBox => newJ = m - 1 - i
    0-th col becomes the same row in the newBox => newI = j
     */
    public char[][] rotateTheBox(char[][] box) {
        var m = box.length;
        var n = box[0].length;
        var rotated = new char[n][m];
        slide(box, n);
        flip(box, m, n, rotated);
        return rotated;
    }

    private void slide(char[][] box, int n) {
        for (var row : box) {
            var empty = n - 1;

            for (var j = n - 1; j >= 0; j--)
                switch (row[j]) {
                    case '*' -> empty = j - 1;
                    case '#' -> {
                        // swap
                        row[j] = '.';
                        row[empty--] = '#';
                    }
                }
        }
    }

    private void flip(char[][] box, int m, int n, char[][] rotated) {
        for (var i = 0; i < m; i++)
            for (var j = 0; j < n; j++)
                rotated[j][m - i - 1] = box[i][j];
    }
}
