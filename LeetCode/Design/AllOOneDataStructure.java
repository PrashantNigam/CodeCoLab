package Design;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// Airbnb Amazon Atlassian Facebook Google LinkedIn Nvidia Snap Snowflake Uber VMware
public class AllOOneDataStructure {
    public static void main(String[] args) {
        var ob = new AllOne();
        ob.inc("hello");
        ob.inc("hello");
        System.out.println(ob.getMaxKey().equals("hello"));
        System.out.println(ob.getMinKey().equals("hello"));
        ob.inc("leet");
        System.out.println(ob.getMaxKey().equals("hello"));
        System.out.println(ob.getMinKey().equals("leet"));
    }

    public static class AllOne {
        private final DoublyLinkedList list = new DoublyLinkedList();
        private final Map<String, Integer> keyCounter = new HashMap<>();
        private final Map<Integer, Node> nodeFrequency = new HashMap<>(Map.of(0, list.dummyHead));

        /**
         * Increments the count of the string key by 1.
         * If key does not exist in the data structure, insert it with count 1.
         */
        public void inc(String key) {
            var currentFrequency = keyCounter.compute(key, (_, v) -> v == null ? 1 : ++v);
            var prevFrequency = currentFrequency - 1;

            if (!nodeFrequency.containsKey(currentFrequency))
                nodeFrequency.put(currentFrequency, list.insertAfter(nodeFrequency.get(prevFrequency)));

            nodeFrequency.get(currentFrequency).addKey(key);

            if (prevFrequency > 0)
                removeKeyFromPreviousFrequencyNode(prevFrequency, key);
        }

        /**
         * Decrements the count of the string key by 1.
         * If the count of key is 0 after the decrement, remove it from the data structure.
         * It is guaranteed that key exists in the data structure before the decrement
         */
        public void dec(String key) {
            var currentFrequency = keyCounter.compute(key, (_, v) -> --v);
            var prevFrequency = currentFrequency + 1;

            if (currentFrequency == 0) {
                keyCounter.remove(key);
            } else {
                if (!nodeFrequency.containsKey(currentFrequency))
                    nodeFrequency.put(currentFrequency, list.insertBefore(nodeFrequency.get(prevFrequency)));

                nodeFrequency.get(currentFrequency).addKey(key);
            }

            removeKeyFromPreviousFrequencyNode(prevFrequency, key);
        }

        /**
         * @return one of the keys with the maximal count.
         * If no element exists, return an empty string ""
         */
        public String getMaxKey() {
            var set = list.getTail().set;
            return set.isEmpty() ? "" : set.iterator().next();
        }

        /**
         * @return one of the keys with the minimum count.
         * If no element exists, return an empty string ""
         */
        public String getMinKey() {
            var set = list.getHead().set;
            return set.isEmpty() ? "" : set.iterator().next();
        }

        private void removeKeyFromPreviousFrequencyNode(int prevFreq, String key) {
            var node = nodeFrequency.get(prevFreq);
            node.removeKey(key);

            if (node.isEmpty()) {
                list.remove(node);
                nodeFrequency.remove(prevFreq);
            }
        }
    }

    private static class Node {
        private Node prev;
        private Node next;
        private final Set<String> set = new HashSet<>();

        private Node() {
        }

        private Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }

        public void addKey(String key) {
            set.add(key);
        }

        public void removeKey(String key) {
            set.remove(key);
        }

        public boolean isEmpty() {
            return set.isEmpty();
        }
    }

    private static class DoublyLinkedList {
      
        private final Node dummyHead = new Node();
        private final Node dummyTail = new Node();

        public DoublyLinkedList() {
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        public Node insertAfter(Node before) {
            var tempNext = before.next;
            var after = new Node(before, tempNext);
            return before.next = tempNext.prev = after;
        }

        public Node insertBefore(Node after) {
            var tempPrev = after.prev;
            var before = new Node(tempPrev, after);
            return tempPrev.next = after.prev = before;
        }

        public void remove(Node node) {
            var tempPrev = node.prev;
            tempPrev.next = node.next;
            node.next.prev = tempPrev;
        }

        public Node getHead() {
            return dummyHead.next;
        }

        public Node getTail() {
            return dummyTail.prev;
        }
    }
}
