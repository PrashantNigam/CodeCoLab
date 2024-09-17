package Hashing;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// Amazon Expedia Microsoft
public class UncommonWordsFromTwoSentences {
    public static void main(String[] args) {
        var ob = new UncommonWordsFromTwoSentences();
        System.out.println(Arrays.equals(ob.uncommonFromSentences("this apple is sweet", "this apple is sour"),
                                         new String[]{"sour", "sweet"}));
        System.out.println(Arrays.equals(ob.uncommonFromSentences("apple apple", "banana"), 
                                         new String[]{"banana"}));
    }

    public String[] uncommonFromSentences(String s1, String s2) {
        var wordCount = new HashMap<String, Integer>();
      
        countWords(s1, wordCount);
        countWords(s2, wordCount);

        return wordCount.entrySet()
                        .stream()
                        .filter(e -> e.getValue() == 1)
                        .map(Map.Entry::getKey)
                        .toArray(String[]::new);
    }

    private void countWords(String s, Map<String, Integer> wordCount) {
        Arrays.stream(s.split(" "))
              .forEach(word -> wordCount.compute(word, (k, v) -> v == null ? 1 : ++v));
    }
}
