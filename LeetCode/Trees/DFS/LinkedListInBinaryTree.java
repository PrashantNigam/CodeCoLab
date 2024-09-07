package Trees.DFS;

import LinkedList.ListNode;
import Trees.TreeNode;

import static LinkedList.ListNode.construct;
import static Trees.SerializeAndDeserializeBinaryTree.deserializeLC;

// SoundHound
public class LinkedListInBinaryTree {
    public static void main(String[] args) {
        var ob = new LinkedListInBinaryTree();
        System.out.println(ob.isSubPath(construct(4, 2, 8),
                                        deserializeLC("[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]")));
        System.out.println(ob.isSubPath(construct(1, 4, 2, 6),
                                        deserializeLC("[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]")));
        System.out.println(!ob.isSubPath(construct(1, 4, 2, 6, 8),
                                         deserializeLC("[1,4,4,null,2,2,null,1,null,6,8,null,null,null,null,1,3]")));
    }

    public boolean isSubPath(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        return dfs(head, root) 
            || isSubPath(head, root.left) 
            || isSubPath(head, root.right);
    }

    private boolean dfs(ListNode head, TreeNode root) {
        if (head == null)
            return true;
        if (root == null)
            return false;
        if (root.val != head.val)
            return false;
        return dfs(head.next, root.left) 
            || dfs(head.next, root.right);
    }
}
