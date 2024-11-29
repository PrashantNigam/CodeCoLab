package SlidingWindow;

import java.util.Arrays;

public class DefuseTheBomb {
    public static void main(String[] args) {
        var ob = new DefuseTheBomb();
        System.out.println(Arrays.equals(ob.decrypt(new int[]{5, 7, 1, 4}, 3), new int[]{12, 10, 16, 13}));
        System.out.println(Arrays.equals(ob.decrypt(new int[]{1, 2, 3, 4}, 0), new int[]{0, 0, 0, 0}));
        System.out.println(Arrays.equals(ob.decrypt(new int[]{2, 4, 9, 3}, -2), new int[]{12, 5, 6, 13}));
    }

    public int[] decrypt(int[] code, int k) {
        var decode = new int[code.length];

        if (k == 0)
            return decode;
        if (k > 0)
            return decrypt(code, decode, 1, k + 1);
        return decrypt(code, decode, code.length + k, code.length);
    }

    private int[] decrypt(int[] code, int[] decode, int left, int right) {
        var sum = decode[0] = Arrays.stream(code, left, right).sum();

        for (var i = 1; i < code.length; i++)
            decode[i] = sum += code[right++ % code.length] - code[left++ % code.length];

        return decode;
    }
}
