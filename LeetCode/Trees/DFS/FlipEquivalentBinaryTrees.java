package Trees.DFS;

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
        if (root1 == null && root2 == null)
            return true;
        if (root1 == null || root2 == null || root1.val != root2.val)
            return false;
        return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right)  // unflipped
            || flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left); // flipped
    }
}
