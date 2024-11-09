package Strings;

public class DeleteCharactersToMakeFancyString {
    public static void main(String[] args) {
        var ob = new DeleteCharactersToMakeFancyString();
        System.out.println(ob.makeFancyString("leeetcode").equals("leetcode"));
        System.out.println(ob.makeFancyString("aaabaaaa").equals("aabaa"));
        System.out.println(ob.makeFancyString("aab").equals("aab"));
        System.out.println(ob.makeFancyString("a").equals("a"));
        System.out.println(ob.makeFancyString("aa").equals("aa"));
    }

    public String makeFancyString(String s) {
        var n = s.length();

        if (n < 3)
            return s;

        var fancy = new StringBuilder();

        for (var i = 0; i + 2 < n; i++) {
            var ch = s.charAt(i);
          
            if (ch == s.charAt(i + 1) && ch == s.charAt(i + 2)) // 3 consecutive characters are equal
                continue;
            
            fancy.append(ch);
        }

        return fancy.append(s, n - 2, n)
                    .toString();
    }
}
