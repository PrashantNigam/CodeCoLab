package Trees.DFS;

import java.util.Arrays;
import java.util.LinkedList;
import static Trees.SerializeAndDeserializeBinaryTree.deserializeLC;

// Amazon Anduril Google Microsoft
public class FlipEquivalentBinaryTrees {
    public static void main(String[] args) {
        var ob = new FlipEquivalentBinaryTrees();
        System.out.println(ob.flipEquiv(deserializeLC("[1,2,3,4,5,6,null,null,null,7,8]"),
                                        deserializeLC("[1,3,2,null,6,4,5,null,null,null,null,8,7]")));
        System.out.println(ob.flipEquiv(deserializeLC("[]"), deserializeLC("[]")));
        System.out.println(!ob.flipEquiv(deserializeLC("[]"), deserializeLC("[1]")));
    }

    public boolean flipEquiv(TreeNode root1, TreeNode root2) {
        var q = new LinkedList<>(Arrays.asList(root1, root2));

        while (!q.isEmpty()) {
            var node1 = q.poll();
            var node2 = q.poll();

            if (!equal(node1, node2))
                return false;
            if (node1 == null) // both are null, so no children to add for this pair
                continue;
            if (equal(node1.left, node2.left) && equal(node1.right, node2.right)) // unflipped
                q.addAll(Arrays.asList(node1.left, node2.left, node1.right, node2.right));
            else if (equal(node1.left, node2.right) && equal(node1.right, node2.left)) // flipped
                q.addAll(Arrays.asList(node1.left, node2.right, node1.right, node2.left));
            else
                return false;
        }

        return true;
    }

    private boolean equal(TreeNode node1, TreeNode node2) {
        if (node1 == null && node2 == null)
            return true;
        if (node1 == null || node2 == null)
            return false;
        return node1.val == node2.val;
    }
}
