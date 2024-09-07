package Trees;

import java.util.LinkedList;
import java.util.List;

public class SerializeAndDeserializeBinaryTree {
    public static void main(String[] args) {
        System.out.println(serialize(deserialize(lcConvert("[1,2,3,null,null,4,5]"))).equals("1 2 3 null null 4 5"));
        System.out.println(serialize(deserialize(lcConvert("[]"))).isEmpty());
        System.out.println(serialize(deserialize(lcConvert("[1]"))).equals("1"));
        System.out.println(serialize(deserialize(lcConvert("[1,2]"))).equals("1 2"));
    }

    public static String serialize(TreeNode root) {
        if (root == null)
            return "";

        var nodes = new LinkedList<String>();
        var q = new LinkedList<>(List.of(root));

        while (!q.isEmpty()) {
            var node = q.poll();
            if (node == null) {
                nodes.add(null);
            } else {
                nodes.add(String.valueOf(node.val));
                q.add(node.left);
                q.add(node.right);
            }
        }

        // trim trailing leaf nodes
        while (nodes.peekLast() == null)
            nodes.removeLast();

        return String.join(" ", nodes);
    }

    public static TreeNode deserialize(String data) {
        if (data.isEmpty())
            return null;

        var i = 1;
        var nodes = data.split(" ");
        var root = getNode(nodes[0]);
        var q = new LinkedList<>(List.of(root));        

        while (!q.isEmpty() && i < nodes.length) {
            var node = q.poll();
            var left = getNode(nodes[i++]);

            if (left != null) {
                node.left = left;
                q.offer(left);
            }
            if (i < nodes.length) {
                var right = getNode(nodes[i++]);
                if (right != null) {
                    node.right = right;
                    q.offer(right);
                }
            }
        }

        return root;
    }

    private static TreeNode getNode(String val) {
        return "null".equals(val) 
             ? null 
             : new TreeNode(Integer.parseInt(val));
    }

    public static TreeNode deserializeLC(String s) {
        return deserialize(lcConvert(s));
    }

    public static String lcConvert(String s) {
        return s.replace("[", "")
                .replace("]", "")
                .replaceAll(",", " ");
    }
}
