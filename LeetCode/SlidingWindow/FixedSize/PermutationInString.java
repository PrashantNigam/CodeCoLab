package SlidingWindow.FixedSize;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Adobe Amazon Apple Bloomberg ByteDance Facebook Google Lyft Microsoft Oracle
public class PermutationInString {
    public static void main(String[] args) {
        var ob = new PermutationInString();
        System.out.println(ob.checkInclusion("ab", "eidbaooo"));
        System.out.println(!ob.checkInclusion("ab", "eidboaoo"));
    }

    /**
    * @return true if text contains a permutation of pattern, or false otherwise
    */
    public boolean checkInclusion(String pattern, String text) {
        var m = pattern.length();
        var n = text.length();

        if (m > n)
            return false;

        var pHash = new int[26];
        var tHash = new int[26];
        populate(pattern, text, m, pHash, tHash);

        if (Arrays.equals(pHash, tHash))
            return true; // first window

        var left = 0;        

        for (var right = m; right < n; right++) {
            
            var leftChar = text.charAt(left++) - 'a';
            var rightChar = text.charAt(right) - 'a';
            tHash[leftChar] = Math.max(tHash[leftChar] - 1, 0);
            tHash[rightChar]++;

            if (Arrays.equals(pHash, tHash))
                return true;
        }

        return false;
    }

    private void populate(String pattern, String text, int m, int[] pHash, int[] tHash) {
        for (var i = 0; i < m; i++) {
            pHash[pattern.charAt(i) - 'a']++;
            tHash[text.charAt(i) - 'a']++;
        }
    }

    /**
     * pattern and text contain only lower case alphabets
     *
     * @param pattern string whose anagram is to be searched
     * @param text    string to search anagram in
     * @return true if text contains anagram of pattern, else false
     */
    public boolean checkInclusion2(String pattern, String text) {
        var m = pattern.length();
        var n = text.length();

        if (m > n)
            return false;

        var left = 0;
        var matched = 0;
        var patternMap = getFrequencyMap(pattern);

        for (var right = 0; right < n; right++) {

            var newCount = patternMap.compute(text.charAt(right), (_, v) -> v == null ? null : --v);

            if (newCount != null && newCount == 0)
                matched++;
            if (patternMap.size() == matched)
                return true;
            if (right < m - 1)
                continue;

            var leftChar = text.charAt(left++);

            if (!patternMap.containsKey(leftChar)) 
                continue;
            if (patternMap.get(leftChar) == 0)
                matched--;
            patternMap.compute(leftChar, (_, v) -> ++v);
        }

        return false;
    }

    private Map<Character, Integer> getFrequencyMap(String s) {
        var map = new HashMap<Character, Integer>();

        for (var i = 0; i < s.length(); i++)
            map.compute(s.charAt(i), (_, v) -> v == null ? 1 : ++v);

        return map;
    }
}
