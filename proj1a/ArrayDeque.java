/**
 * Invariants:
 * the first item is always at items[nextFirst + 1]
 * the last item is always at items[nextLast - 1]
 */
public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 1;
    }

    /**
     * resize the items array
     */
    public void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        nextFirst += 1;
        for (int i = 0; i < size; i++) {
            newItems[i] = items[nextFirst];
            nextFirst += 1;
            checkfirstlast();
        }
        items = newItems;
        nextFirst = items.length - 1; //newItems stars from index 0
        nextLast = size;
    }

    /**
     * helper method:
     * check nextFirst and nextLast, make sure their indexes are correct
     */
    private void checkfirstlast() {
        if (nextFirst == -1) {
            nextFirst = items.length - 1;
        } else if (nextFirst == items.length) {
            nextFirst = 0;
        } else if (nextLast == -1) {
            nextLast = items.length - 1;
        } else if (nextLast == items.length) {
            nextLast = 0;
        }
    }


    /**
     * Adds an item of type T to the front of the deque.
     */
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        checkfirstlast();
        size += 1;
    }

    /**
     * Adds an item of type T to the back of the deque.
     */
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2); //0.25
        }
        items[nextLast] = item;
        nextLast += 1;
        checkfirstlast();
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
        if (items.length >= 16 && ((float) size / items.length) < 0.25) {
            resize(size/2);
        }
        nextFirst += 1;
        checkfirstlast();
        T x = items[nextFirst];
        items[nextFirst] = null;
        size -= 1;
        return x;
    }

    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && ((float) size / items.length) < 0.25) {
            resize(size/2);
        }
        nextLast -= 1;
        checkfirstlast();
        T x = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        return x;
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
        int p = nextFirst + 1; //index of the first item
        p = p + index; //index of the look up item
        p = p % items.length;///////
        return items[p];
    }


    /**
     * Returns true if deque is empty, false otherwise..
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns the number of items in the deque..
     */
    public int size() {
        return size;
    }

    /**
     * Prints the items in the deque from first to last, separated by a space
     */
    public void printDeque() {
        int p = nextFirst;
        for (int i = 0; i < size; i++) {
            p += 1;
            if (p == items.length) {
                p = 0;
            }
            System.out.print(items[p] + " ");
        }
    }


}
