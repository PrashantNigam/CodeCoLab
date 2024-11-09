package Strings;

public class CircularSentence {
    public static void main(String[] args) {
        var ob = new CircularSentence();
        System.out.println(ob.isCircularSentence("leetcode exercises sound delightful"));
        System.out.println(!ob.isCircularSentence("Leetcode is cool"));
    }

    public boolean isCircularSentence(String sentence) {
        var words = sentence.split(" ");
        var n = words.length;

        for (var i = 0; i + 1 < n; i++)
            if (words[i].charAt(words[i].length() - 1) != words[i + 1].charAt(0))
                return false;

        return words[0].charAt(0) == words[n - 1].charAt(words[n - 1].length() - 1);
    }
}
