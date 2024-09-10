package LinkedList;

import static LinkedList.ListNode.construct;

public class InsertGreatestCommonDivisorsInLinkedList {
    public static void main(String[] args) {
        var ob = new InsertGreatestCommonDivisorsInLinkedList();
        System.out.println(ob.insertGreatestCommonDivisors(construct(18, 6, 10, 3)).equals(
                                                           construct(18, 6, 6, 2, 10, 1, 3)));
        System.out.println(ob.insertGreatestCommonDivisors(construct(7)).equals(construct(7)));
    }

    public ListNode insertGreatestCommonDivisors(ListNode head) {
        for (var current = head; current.next != null; current = current.next.next)
            current.next = new ListNode(gcd(current.val, current.next.val), current.next);

        return head;
    }

    private int gcd(int a, int b) {
        return a == 0 ? b : gcd(b % a, a);
    }
}
