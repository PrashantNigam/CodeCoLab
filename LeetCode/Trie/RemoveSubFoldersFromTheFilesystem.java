package Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// Amazon Google
public class RemoveSubFoldersFromTheFilesystem {
    public static void main(String[] args) {
        var ob = new RemoveSubFoldersFromTheFilesystem();
        System.out.println(ob.removeSubfolders(new String[]{"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"}).equals(List.of("/a", "/c/d", "/c/f")));
        System.out.println(ob.removeSubfolders(new String[]{"/a", "/a/b/c", "/a/b/d"}).equals(List.of("/a")));
        System.out.println(ob.removeSubfolders(new String[]{"/a/b/c", "/a/b/ca", "/a/b/d"}).equals(List.of("/a/b/c", "/a/b/ca", "/a/b/d")));
    }

    // Trie
    public List<String> removeSubfolders(String[] folder) {
        var trie = new Trie(folder);
        return Arrays.stream(folder)
                     .filter(path -> !trie.isSubFolder(path))
                     .collect(Collectors.toCollection(ArrayList::new));
    }

    public static class Trie {
        public static class TrieNode {
            private final Map<String, TrieNode> children = new HashMap<>();
            private boolean isSubFolder;

            public TrieNode(boolean isFolder) {
                this.isSubFolder = isFolder;
            }
        }
        
        private final TrieNode root = new TrieNode(false);

        public Trie(String[] folder) {
            Arrays.stream(folder)
                  .forEach(this::insert);
        }

        public void insert(String path) {
            var node = root;

            for (var dir : path.split("/"))
                node = node.children.computeIfAbsent(dir, _ -> new TrieNode(false));

            node.isSubFolder = true;
        }

        public boolean isSubFolder(String path) {
            var node = root;

            for (var dir : path.split("/")) {
                if (node.isSubFolder)
                    return true;

                node = node.children.get(dir);
            }

            return false;
        }
    }

    // Hashing
    public List<String> removeSubfolders2(String[] folder) {
        var set = new HashSet<String>();
        Collections.addAll(set, folder);
        return Arrays.stream(folder)
                     .filter(path -> isTopFolder(path, set))
                     .collect(Collectors.toCollection(ArrayList::new));
    }

    private boolean isTopFolder(String path, HashSet<String> set) {
        while (!path.isEmpty()) {
            path = path.substring(0, path.lastIndexOf('/'));
          
            if (set.contains(path))
                return false;
        }

        return true;
    }
}
