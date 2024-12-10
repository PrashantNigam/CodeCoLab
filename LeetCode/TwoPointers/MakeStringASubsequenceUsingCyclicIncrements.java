package TwoPointers;

public class MakeStringASubsequenceUsingCyclicIncrements {
    public static void main(String[] args) {
        var ob = new MakeStringASubsequenceUsingCyclicIncrements();
        System.out.println(ob.canMakeSubsequence("abc", "ad"));
        System.out.println(ob.canMakeSubsequence("zc", "ad"));
        System.out.println(!ob.canMakeSubsequence("ab", "d"));
    }

    public boolean canMakeSubsequence(String str1, String str2) {
        var m = str1.length();
        var n = str2.length();

        if (m < n)
            return false;

        var j = 0;

        for (var i = 0; i < m && j < n; i++)
            if (str1.charAt(i) == str2.charAt(j) || nextOf(str1.charAt(i)) == str2.charAt(j))
                j++;

        return j == n;
    }

    private char nextOf(char ch) {
        return ch == 'z' ? 'a' : (char) (ch + 1);
    }
}
