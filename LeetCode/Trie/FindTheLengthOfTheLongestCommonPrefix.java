package Trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FindTheLengthOfTheLongestCommonPrefix {
    public static void main(String[] args) {
        var ob = new FindTheLengthOfTheLongestCommonPrefix();
        System.out.println(ob.longestCommonPrefix(new int[]{1, 10, 100}, new int[]{1000}) == 3);
        System.out.println(ob.longestCommonPrefix(new int[]{1, 2, 3}, new int[]{4, 4, 4}) == 0);
        System.out.println(ob.longestCommonPrefix(new int[]{1, 3}, new int[]{32, 22}) == 1);
    }

    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        var maxPrefixLength = 0;
        var trie = new Trie(arr1);

        for (var val : arr2)
            maxPrefixLength = Math.max(maxPrefixLength, trie.longestPrefixLength(val));

        return maxPrefixLength;
    }

    private static class Trie {
        private final TrieNode root = new TrieNode();

        public Trie(int[] arr1) {
            Arrays.stream(arr1)
                  .forEach(this::insert);
        }

        public void insert(int val) {
            var node = root;
            var newNode = Integer.toString(val);

            for (var i = 0; i < newNode.length(); i++)
                node = node.children.computeIfAbsent(newNode.charAt(i), _ -> new TrieNode());
        }

        private int longestPrefixLength(int val) {
            var length = 0;
            var node = root;
            var query = Integer.toString(val);

            for (var i = 0; i < query.length(); i++) {
                var key = query.charAt(i);

                if (!node.children.containsKey(key))
                    break;

                length++;
                node = node.children.get(key);
            }

            return length;
        }

        private static class TrieNode {
            private final Map<Character, TrieNode> children = new HashMap<>();
        }
    }
}
