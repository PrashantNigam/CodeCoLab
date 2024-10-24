package Trees.BFS;

import Trees.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import static Trees.SerializeAndDeserializeBinaryTree.deserializeLC;

// Amazon Microsoft
public class CousinsInBinaryTreeII {
    public static void main(String[] args) {
        var ob = new CousinsInBinaryTreeII();
        System.out.println(ob.replaceValueInTree(deserializeLC("[5,4,9,1,10,null,7]")).equals(deserializeLC("[0,0,0,7,7,null,11]")));
        System.out.println(ob.replaceValueInTree(deserializeLC("[3,1,2]")).equals(deserializeLC("[0,0,0]")));
    }

    public TreeNode replaceValueInTree(TreeNode root) {
        root.val = 0;
        var q = new ArrayDeque<>(List.of(root));

        for (var prevLevelChildrenSum = 0; !q.isEmpty();) {
            var levelNodesChildrenSum = new int[1];

            for (var i = q.size(); i > 0; i--) {
                var node = q.poll();
                var siblingSum = enqueue(node.left, q, levelNodesChildrenSum) + enqueue(node.right, q, levelNodesChildrenSum);
                var replacementVal = prevLevelChildrenSum - siblingSum;
                setVal(node.left, replacementVal);
                setVal(node.right, replacementVal);
            }

            prevLevelChildrenSum = levelNodesChildrenSum[0];
        }

        return root;
    }

    private int enqueue(TreeNode node, Deque<TreeNode> q, int[] levelNodesChildrenSum) {
        if (node == null)
            return 0;

        q.add(node);
        sumChild(node.left, levelNodesChildrenSum);
        sumChild(node.right, levelNodesChildrenSum);
        return node.val;
    }

    private void sumChild(TreeNode node, int[] levelNodesChildrenSum) {
        if (node == null)
            return;
        levelNodesChildrenSum[0] += node.val;
    }

    private void setVal(TreeNode node, int val) {
        if (node == null)
            return;
        node.val = Math.max(0, val);
    }
}
