package Trees.BFS;

import Trees.TreeNode;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;
import static Trees.SerializeAndDeserializeBinaryTree.deserializeLC;

// Amazon Bloomberg
public class KthLargestSumInABinaryTree {
    public static void main(String[] args) {
        var ob = new KthLargestSumInABinaryTree();
        System.out.println(ob.kthLargestLevelSum(deserializeLC("[5,8,9,2,1,3,7,4,6]"), 2) == 13);
        System.out.println(ob.kthLargestLevelSum(deserializeLC("[1,2,null,3]"), 1) == 3);
        System.out.println(ob.kthLargestLevelSum(deserializeLC("[5,8,9,2,1,3,7]"), 4) == -1);
    }

    public long kthLargestLevelSum(TreeNode root, int k) {
        var level = 0;
        var q = new ArrayDeque<>(List.of(root));
        var minHeap = new PriorityQueue<Long>();

        for (; !q.isEmpty(); level++) {
            minHeap.add(levelSum(q));

            if (minHeap.size() == k + 1)
                minHeap.poll();
        }

        return level < k ? -1 : minHeap.poll();
    }

    private long levelSum(Deque<TreeNode> q) {
        var sum = 0L;

        for (var i = q.size(); i > 0; i--) {
            var node = q.poll();
            sum += node.val;
            enqueue(q, node.left);
            enqueue(q, node.right);
        }

        return sum;
    }

    private void enqueue(Deque<TreeNode> q, TreeNode node) {
        if (node != null)
            q.add(node);
    }
}
