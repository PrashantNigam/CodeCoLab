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

     /**
     * Iterate over s
     * If adjacent characters are same, continue iterating
     * Else if the string size seen yet is even then no flips need to be made, but the string resets here 
     * (i.e. a partition has to be made at current index - 1) and size becomes 1 for the string thus starting
     * Else if the string size seen yet is odd, then this bit must be flipped and the string ends here and a new one 
     * starts at current index + 1 with size = 0
     */
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
