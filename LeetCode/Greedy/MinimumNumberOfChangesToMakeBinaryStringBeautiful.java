package Greedy;

public class MinimumNumberOfChangesToMakeBinaryStringBeautiful {
    public static void main(String[] args) {
        var ob = new MinimumNumberOfChangesToMakeBinaryStringBeautiful();
        System.out.println(ob.minChanges("1001") == 2);
        System.out.println(ob.minChanges("10") == 1);
        System.out.println(ob.minChanges("0000") == 0);
    }

    public int minChanges(String s) {
        var changes = 0;
        var n = s.length();

        for (var i = 0; i + 1 < n; i++)
            if (s.charAt(i) != s.charAt(i + 1))
                changes++;

        return changes;
    }

    public int minChanges2(String s) {
        var size = 0;
        var changes = 0;
        var n = s.length();
        var ch = s.charAt(0);

        for (var i = 0; i < n; i++) {
            if (s.charAt(i) == ch) {
                size++;
                continue;
            }
            if ((size & 1) == 0) {
                size = 1;
            } else {
                size = 0;
                changes++;
            }

            ch = s.charAt(i);
        }

        return changes;
    }
}
