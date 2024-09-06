package LinkedList;

import java.util.Objects;

public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {}

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode construct(int... nums) {
        ListNode head = null;
        ListNode temp = null;

        for (var num : nums) {
            var node = new ListNode(num);
            if (head == null) {
                head = node;
                temp = head;
            } else {
                temp.next = node;
                temp = temp.next;
            }
        }
        
        return head;
    }

    public static int size(ListNode head) {
        var size = 0;

        while (head != null) {
            head = head.next;
            size++;
        }

        return size;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that)
            return true;
        if (that == null || getClass() != that.getClass())
            return false;

        var listNode = (ListNode) that;
        return val == listNode.val && Objects.equals(next, listNode.next);
    }

    @Override
    public int hashCode() {
        return Objects.hash(val, next);
    }

    @Override
    public String toString() {
        return Integer.toString(val);
    }
}