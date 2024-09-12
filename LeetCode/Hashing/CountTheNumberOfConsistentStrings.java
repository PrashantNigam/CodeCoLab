package Hashing;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// Robinhood
public class CountTheNumberOfConsistentStrings {
    public static void main(String[] args) {
        var ob = new CountTheNumberOfConsistentStrings();
        System.out.println(ob.countConsistentStrings("ab", new String[]{"ad", "bd", "aaab", "baa", "badab"}) == 2);
        System.out.println(ob.countConsistentStrings("abc", new String[]{"a", "b", "c", "ab", "ac", "bc", "abc"}) == 7);
        System.out.println(ob.countConsistentStrings("cad", new String[]{"cc", "acd", "b", "ba", "bac", "bad", "ac", "d"}) == 4);
    }

    private static final int[] HIGH_BITS = new int[26];

    static {
        var x = 1;

        for (var i = 0; i < 26; i++) {
            HIGH_BITS[i] = x;
            x <<= 1;
        }
    }

    // Variation 1: Use HashSet class
    public int countConsistentStrings(String allowed, String[] words) {
        var count = 0;
        var letters = buildHashSet(allowed);

        for (var word : words)
            if (isConsistent(letters, word))
                count++;

        return count;
    }

    private Set<Character> buildHashSet(String allowed) {
        var letters = new HashSet<Character>();

        for (var i = 0; i < allowed.length(); i++)
            letters.add(allowed.charAt(i));

        return letters;
    }

    private boolean isConsistent(Set<Character> letters, String word) {
        for (var i = 0; i < word.length(); i++)
            if (!letters.contains(word.charAt(i)))
                return false;

        return true;
    }

    // Variation 2: 2 liner version of Variation 1, using streams, lambdas and method reference
    public int countConsistentStrings2(String allowed, String[] words) {
        var letters = IntStream.range(0, allowed.length())
                               .mapToObj(allowed::charAt)
                               .collect(Collectors.toCollection(HashSet::new));

        return (int) Arrays.stream(words)
                           .filter(word -> IntStream
                           .range(0, word.length())
                           .allMatch(i -> letters.contains(word.charAt(i))))
                           .count();
    }

    // Variation 3: Use a boolean array as a hash map
    public int countConsistentStrings3(String allowed, String[] words) {
        var count = 0;
        var letters = buildHashArray(allowed);

        for (var word : words)
            if (isConsistent(letters, word))
                count++;

        return count;
    }

    private boolean[] buildHashArray(String s) {
        var letters = new boolean[26];

        for (var i = 0; i < s.length(); i++)
            letters[s.charAt(i) - 'a'] = true;

        return letters;
    }

    private boolean isConsistent(boolean[] hash, String word) {
        for (var i = 0; i < word.length(); i++)
            if (!hash[word.charAt(i) - 'a'])
                return false;

        return true;
    }

    // Variation 4: Bit Flagging using a 32 bit number (int) 
    public int countConsistentStrings4(String allowed, String[] words) {
        var count = 0;
        var letters = buildBitMap(allowed);

        for (var word : words)
            if (isConsistent(letters, word))
                count++;

        return count;
    }

    private boolean isConsistent(int letters, String word) {
        for (var i = 0; i < word.length(); i++)
            if (!isBitSet(letters, word.charAt(i) - 'a'))
                return false;

        return true;
    }

    private boolean isBitSet(int n, int bit) {
        return (n & HIGH_BITS[bit]) != 0;
    }

    private int buildBitMap(String s) {
        var letters = 0;

        for (var i = 0; i < s.length(); i++)
            letters = letters | HIGH_BITS[s.charAt(i) - 'a'];

        return letters;
    }
}