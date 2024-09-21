package Graphs.DFS;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

// Adobe Bloomberg ByteDance Google
public class LexicographicalNumbers {
    public static void main(String[] args) {
        var ob = new LexicographicalNumbers();
        System.out.println(ob.lexicalOrder(13).equals(List.of(1, 10, 11, 12, 13, 2, 3, 4, 5, 6, 7, 8, 9)));
        System.out.println(ob.lexicalOrder(2).equals(List.of(1, 2)));
    }

    public List<Integer> lexicalOrder(int n) {
        var nums = new ArrayList<Integer>();
        IntStream.range(1, 10).forEach(i -> addToNums(i, n, nums));
        return nums;
    }

    private void addToNums(int i, int n, List<Integer> nums) {
        if (i > n)
            return;

        nums.add(i);
        IntStream.range(0, 10).forEach(j -> addToNums(i * 10 + j, n, nums));
    }
}
