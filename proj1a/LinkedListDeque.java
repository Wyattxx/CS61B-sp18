/**
 * deque 2020.0618
 *
 */
public class LinkedListDeque<T> {

    /** linked list based deque */
    private class StuffNode {
        public T item;
        public StuffNode next;
        public StuffNode prev;
        public StuffNode(StuffNode pre, T i, StuffNode nex) {
            prev = pre;
            item = i;
            next = nex;
        }
    }

    private int size;
    private StuffNode sentinel;

    /** create an empty DLList*/
    public LinkedListDeque() {
        sentinel = new StuffNode(null, (T) "63", null); //我觉得需要一个cast
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** create a DLList*/
    public LinkedListDeque(T x) {
        sentinel = new StuffNode(null, (T) "63", null);
        sentinel.next = new StuffNode(sentinel, x, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    /** add item to the front of the list */
    public void addFirst(T item) {
        sentinel.next = new StuffNode(sentinel, item, sentinel.next);
        sentinel.next.next.prev = sentinel.next;//更新原来的first的prev!
        //也可以这样写
        //sentinel.next.prev = new StuffNode(sentinel, item, sentinel.next);
        //sentinel.next = sentinel.next.prev;
        size += 1;
    }

    /** add item to the end of the list */
    public void addLast(T item) {
        sentinel.prev = new StuffNode(sentinel.prev, item, sentinel);
        sentinel.prev.prev.next = sentinel.prev; //更新原来的end的next!
        size += 1;
    }

    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -=1;
        return value;
    }

    /**
     * Removes and returns the item at the end of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T value = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -=1;
        return value;
    }

    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        StuffNode p = sentinel;
        for (int i = 0; i <= index; i++) {
            p = p.next;
        }
        return p.item;

    }

    /**
     * same as get, but uses recursion
     * first, we need a private helper method
     * github
     */
    private T getRecursive(int index, StuffNode curr_sentinel) {
        if (index >= size) {
            return null;
        }
        if (index == 0) {
            return curr_sentinel.next.item;
        }
        return getRecursive(index-1, curr_sentinel.next);
    }

    public T getRecursive(int index) {
        return getRecursive(index, sentinel);
    }

//    public T getRecursive(int index) {
//        if (index >= size) {
//            return null;
//        }
//        StuffNode p = sentinel;
//        if (index == 0) {
//            return p.next.item;
//        }
//        this.removeFirst(); //destructive
//        return getRecursive(index - 1);
//    }

    public LinkedListDeque(LinkedListDeque other) {
        sentinel = new StuffNode(null, (T) "63", null); //我觉得需要一个cast
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        for (int i = 0; i < other.size; i++) {
            this.addLast((T) other.get(i));
        }
        size = other.size;
    }

    /**
     * Prints the items in the deque from first to last,
     * separated by a space.
     */
    public void printDeque() {
        StuffNode p = sentinel;
        for (int i = 0; i < size; i++) {
            p = p.next;
            System.out.print(p.item + " ");
        }
    }

    /** return true if the list is empty */
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    /** return the size of the list */
    public int size() {
        return size;
    }
}
