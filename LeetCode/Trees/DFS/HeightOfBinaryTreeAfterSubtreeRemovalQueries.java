package Trees.DFS;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import static Trees.SerializeAndDeserializeBinaryTree.deserializeLC;

public class HeightOfBinaryTreeAfterSubtreeRemovalQueries {
    public static void main(String[] args) {
        var ob = new HeightOfBinaryTreeAfterSubtreeRemovalQueries();
        System.out.println(Arrays.equals(ob.treeQueries(deserializeLC("[1,3,4,2,null,6,5,null,null,null,null,null,7]"), new int[]{4}), 
                                         new int[]{2}));
        System.out.println(Arrays.equals(ob.treeQueries(deserializeLC("[5,8,9,2,1,3,7,4,6]"), new int[]{3, 2, 4, 8}), 
                                         new int[]{3, 2, 3, 2}));
    }

    public int[] treeQueries(TreeNode root, int[] queries) {
        var nodeToLevel = new HashMap<Integer, Integer>();
        var nodeToHeight = new HashMap<Integer, Integer>();
        var levelToMaxHeights = new HashMap<Integer, Queue<Integer>>();
        findHeight(root, nodeToLevel, nodeToHeight, levelToMaxHeights, 0);
        return Arrays.stream(queries)
                     .map(query -> getTreeHeightAfterNodeRemoval(query, nodeToLevel, levelToMaxHeights, nodeToHeight))
                     .toArray();
    }

    private int findHeight(TreeNode node, Map<Integer, Integer> nodeToLevel, Map<Integer, Integer> nodeToHeight, Map<Integer, Queue<Integer>> levelToMaxHeights, int level) {
        if (node == null)
            return 0;

        nodeToLevel.put(node.val, level);
        var leftSubHeight = findHeight(node.left, nodeToLevel, nodeToHeight, levelToMaxHeights, level + 1);
        var rightSubHeight = findHeight(node.right, nodeToLevel, nodeToHeight, levelToMaxHeights, level + 1);
        var height = 1 + Math.max(leftSubHeight, rightSubHeight);
        nodeToHeight.put(node.val, height);
        addInSizeTwoHeap(levelToMaxHeights, level, height);
        return height;
    }

    private void addInSizeTwoHeap(Map<Integer, Queue<Integer>> levelToMaxHeights, int level, int height) {
        levelToMaxHeights.computeIfAbsent(level, _ -> new PriorityQueue<>())
                         .add(height);

        if (levelToMaxHeights.get(level).size() > 2)
            levelToMaxHeights.get(level).poll();
    }

    private int getTreeHeightAfterNodeRemoval(int query, Map<Integer, Integer> nodeToLevel, Map<Integer, Queue<Integer>> levelToMaxHeights, Map<Integer, Integer> nodeToHeight) {
        var queryLevel = nodeToLevel.get(query);
        var queryHeight = nodeToHeight.get(query);
        var maxHeights = levelToMaxHeights.get(queryLevel);
        var copyMaxHeights = new PriorityQueue<>(maxHeights);
        var levelSecondMaxHeight = maxHeights.isEmpty() ? 0 : maxHeights.poll();
        var levelMaxHeight = maxHeights.isEmpty() ? 0 : maxHeights.poll();
        var queryNodeNewHeight = queryHeight == levelMaxHeight ? levelSecondMaxHeight : levelMaxHeight;
        levelToMaxHeights.put(queryLevel, copyMaxHeights);
        return queryNodeNewHeight + queryLevel - 1;
    }
}
