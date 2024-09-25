package Trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SumOfPrefixScoresOfStrings {
    public static void main(String[] args) {
        var ob = new SumOfPrefixScoresOfStrings();
        System.out.println(Arrays.equals(ob.sumPrefixScores(new String[]{"abc", "ab", "bc", "b"}), new int[]{5, 4, 3, 2}));
        System.out.println(Arrays.equals(ob.sumPrefixScores(new String[]{"abcd"}), new int[]{4}));
    }

    public int[] sumPrefixScores(String[] words) {
        var trie = new Trie(words);
        return Arrays.stream(words)
                     .mapToInt(trie::search)
                     .toArray();
    }

    public static class Trie {
      
        public static class TrieNode {
            private int score;
            private final Map<Character, TrieNode> children = new HashMap<>();
        }

        private final TrieNode root = new TrieNode();

        public Trie(String[] words) {
            Arrays.stream(words)
                  .forEach(this::insert);
        }

        public void insert(String word) {
            var node = root;

            for (var i = 0; i < word.length(); i++) {
                node = node.children.computeIfAbsent(word.charAt(i), _ -> new TrieNode());
                node.score++;
            }
        }

        public int search(String word) {
            var score = 0;
            var node = root;

            for (var i = 0; i < word.length(); i++) {
                var key = word.charAt(i);
                node = node.children.get(key);

                if (node == null)
                    break;

                score += node.score;
            }

            return score;
        }
    }
}
