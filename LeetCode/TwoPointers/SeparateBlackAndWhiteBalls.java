package TwoPointers;

// 1Kosmos
public class SeparateBlackAndWhiteBalls {
    public static void main(String[] args) {
        var ob = new SeparateBlackAndWhiteBalls();
        System.out.println(ob.minimumSteps("101") == 1);
        System.out.println(ob.minimumSteps("100") == 2);
        System.out.println(ob.minimumSteps("0111") == 0);
    }

    // Similar to LC 75. Sort Colors
    public long minimumSteps(String s) {
        var swaps = 0L;
        var nextWhite = 0;

        for (var i = 0; i < s.length(); i++)
            if (s.charAt(i) == '0')
                swaps += i - nextWhite++;

        return swaps;
    }
}
