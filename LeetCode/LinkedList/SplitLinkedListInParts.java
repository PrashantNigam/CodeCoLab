package LinkedList;

import java.util.Arrays;

import static LinkedList.ListNode.construct;

//Adobe Amazon Google Microsoft Oracle
public class SplitLinkedListInParts {
    public static void main(String[] args) {
        var ob = new SplitLinkedListInParts();
        System.out.println(Arrays.equals(ob.splitListToParts(construct(1, 2, 3), 5),
                                         new ListNode[]{construct(1),
                                                        construct(2),
                                                        construct(3),
                                                        construct(),
                                                        construct()}));
        System.out.println(Arrays.equals(ob.splitListToParts(construct(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), 3),
                                         new ListNode[]{construct(1, 2, 3, 4),
                                                        construct(5, 6, 7),
                                                        construct(8, 9, 10)}));
    }

    public ListNode[] splitListToParts(ListNode head, int k) {
        var first = head;
        var last = head;
        var size = length(head);
        var partSize = size / k;
        var extra = size % k;
        var parts = new ListNode[k];

        for (var i = 0; i < k; i++) {
            // initialize head of this sub-list
            parts[i] = first;
            // If there are extra nodes remaining, then assign one to this sub-list
            var currentSize = extra > 0 ? 1 + partSize : partSize;
            extra--;

            for (var j = 1; j < currentSize && last != null; j++)
                last = last.next;

            if (last == null || last.next == null)
                break;

            // point first to the head of the next sub-list
            first = last.next;
            // break the connection of previous sub-list's end to its old-next-node to mark the end of this sub-list
            last.next = null;
            // reset last to point to new first
            last = first;
        }

        return parts;
    }

    private int length(ListNode head) {
        var size = 0;

        for (; head != null; head = head.next)
            size++;

        return size;
    }
}