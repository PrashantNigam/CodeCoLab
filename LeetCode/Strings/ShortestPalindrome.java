package Strings;

// Adobe Amazon Bloomberg Facebook Google Microsoft Oracle PocketGems
public class ShortestPalindrome {
    public static void main(String[] args) {
        var ob = new ShortestPalindrome();
        System.out.println(ob.shortestPalindrome("aacecaaa").equals("aaacecaaa"));
        System.out.println(ob.shortestPalindrome("abcd").equals("dcbabcd"));
    }

    public String shortestPalindrome(String s) {
        var n = s.length();
        var reverse = new StringBuilder(s).reverse().toString();

        for (var i = 0; i <= n; i++)
            if (s.startsWith(reverse.substring(i)))
                return reverse.substring(0, i).concat(s);

        return reverse.concat(s);
    }
}
