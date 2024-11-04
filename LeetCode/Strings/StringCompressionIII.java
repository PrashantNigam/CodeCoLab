package Strings;

public class StringCompressionIII {
    public static void main(String[] args) {
        var ob = new StringCompressionIII();
        System.out.println(ob.compressedString("abcde").equals("1a1b1c1d1e"));
        System.out.println(ob.compressedString("aaaaaaaaaaaaaabb").equals("9a5a2b"));
    }

    public String compressedString(String word) {
        var n = word.length();
        var compressed = new StringBuilder();

        for (var i = 0; i < n;) {
            var ch = word.charAt(i);
            var count = getCharCount(word, n, ch, i);
            compressed.append(count).append(ch);
            i += count;
        }

        return compressed.toString();
    }

    private int getCharCount(String word, int n, char ch, int i) {
        var count = 0;
        for (; i < n && word.charAt(i) == ch && count < 9; i++, count++);
        return count;
    }
}
