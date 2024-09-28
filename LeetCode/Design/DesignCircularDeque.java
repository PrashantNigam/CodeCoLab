package Design;

// Facebook
public class DesignCircularDeque {
    public static void main(String[] args) {
        var ob = new MyCircularDeque(3);
        System.out.println(ob.insertLast(1));
        System.out.println(ob.insertLast(2));
        System.out.println(ob.insertFront(3));
        System.out.println(!ob.insertFront(4));
        System.out.println(ob.getRear() == 2);
        System.out.println(ob.isFull());
        System.out.println(ob.deleteLast());
        System.out.println(ob.insertFront(4));
        System.out.println(ob.getFront() == 4);
    }

    public static class MyCircularDeque {

        public static class Node {
            private final int value;
            private Node prev;
            private Node next;

            private Node(int value) {
                this.value = value;
            }

            private Node(int value, Node prev, Node next) {
                this.value = value;
                this.prev = prev;
                this.next = next;
            }
        }

        private int size;
        private final int maxSize;
        private final Node dummyHead = new Node(0);
        private final Node dummyTail = new Node(0);

        /**
         * @param k Initializes the deque with a maximum size of k
         */
        public MyCircularDeque(int k) {
            maxSize = k;
            dummyHead.next = dummyTail;
            dummyTail.prev = dummyHead;
        }

        /**
         * @param value Adds an item at the front of Deque.
         * @return true if the operation is successful, or false otherwise
         */
        public boolean insertFront(int value) {
            if (isFull())
                return false;

            var oldFront = dummyHead.next;
            var newFront = new Node(value, dummyHead, dummyHead.next);
            dummyHead.next = oldFront.prev = newFront;
            size++;
            return true;
        }

        /**
         * @param value Adds an item at the rear of Deque.
         * @return true if the operation is successful, or false otherwise
         */
        public boolean insertLast(int value) {
            if (isFull())
                return false;

            var oldLast = dummyTail.prev;
            var newLast = new Node(value, dummyTail.prev, dummyTail);
            dummyTail.prev = oldLast.next = newLast;
            size++;
            return true;
        }

        /**
         * Deletes an item from the front of Deque
         *
         * @return true if the operation is successful, or false otherwise.
         */
        public boolean deleteFront() {
            if (isEmpty())
                return false;

            var second = dummyHead.next.next;
            dummyHead.next = second;
            second.prev = dummyHead;
            size--;
            return true;
        }

        /**
         * Deletes an item from the rear of Deque.
         *
         * @return true if the operation is successful, or false otherwise.
         */
        public boolean deleteLast() {
            if (isEmpty())
                return false;

            var secondLast = dummyTail.prev.prev;
            secondLast.next = dummyTail;
            dummyTail.prev = secondLast;
            size--;
            return true;
        }

        /**
         * @return the front item from the Deque. Returns -1 if the deque is empty.
         */
        public int getFront() {
            return isEmpty() ? -1 : dummyHead.next.value;
        }

        /**
         * @return the last item from Deque. Returns -1 if the deque is empty
         */
        public int getRear() {
            return isEmpty() ? -1 : dummyTail.prev.value;
        }

        /**
         * @return true if the deque is empty, or false otherwise.
         */
        public boolean isEmpty() {
            return size == 0;
        }

        /**
         * @return true if the deque is full, or false otherwise
         */
        public boolean isFull() {
            return size == maxSize;
        }
    }
}
