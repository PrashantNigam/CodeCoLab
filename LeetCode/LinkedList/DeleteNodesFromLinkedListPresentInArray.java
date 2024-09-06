package LinkedList;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static LinkedList.ListNode.construct;

public class DeleteNodesFromLinkedListPresentInArray {
    public static void main(String[] args) {
        var ob = new DeleteNodesFromLinkedListPresentInArray();
        System.out.println(ob.modifiedList(new int[]{1, 2, 3}, construct(1, 2, 3, 4, 5)).equals(construct(4, 5)));
        System.out.println(ob.modifiedList(new int[]{1}, construct(1, 2, 1, 2, 1, 2)).equals(construct(2, 2, 2)));
        System.out.println(ob.modifiedList(new int[]{5}, construct(1, 2, 3, 4)).equals(construct(1, 2, 3, 4)));
    }

    public ListNode modifiedList(int[] nums, ListNode head) {
        var toDelete = setOf(nums);
        var dummyHead = new ListNode(0, head);
        var prev = dummyHead;

        for (var current = head; current != null; current = current.next)
            if (toDelete.contains(current.val))
                prev.next = current.next;
            else
                prev = current;

        return dummyHead.next;
    }

    private Set<Integer> setOf(int[] nums) {
        var toDelete = new HashSet<Integer>();
        Arrays.stream(nums)
              .forEach(toDelete::add);
        return toDelete;
    }
}