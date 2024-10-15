package Topics.TwoPointers;

public class SeparateBlackAndWhiteBalls {
    public static void main(String[] args) {
        var ob = new SeparateBlackAndWhiteBalls();
        System.out.println(ob.minimumSteps("101") == 1);
        System.out.println(ob.minimumSteps("100") == 2);
        System.out.println(ob.minimumSteps("0111") == 0);
    }

    public long minimumSteps(String s) {
        var swaps = 0L;
        var white = 0;

        for (var i = 0; i < s.length(); i++)
            if (s.charAt(i) == '0')
                swaps += i - white++;

        return swaps;
    }
}
