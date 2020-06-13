/**
 * @author Wyatt
 * Invariants:
 * the first item is always at items[nextFirst + 1]
 * the last item is always at items[nextLast - 1]
 */
public class ArrayDeque<T> implements Deque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8]; //grammar request
        size = 0;
        nextFirst = 0; //on the left of nextLast
        nextLast = 1;
    }

    /**
     * helper method:
     * calculate the index after the given index circularly
     */
    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    /**
     * helper method:
     * calculate the index before the given index circularly
     */
    private int minusOne(int index) {
        return (index - 1 + items.length) % items.length; //
    }

    /**
     * resize the oldItems array to length = capacity
     * oldItems locate at the beginning of the newItems, following the index order
     */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        int oldIndex = plusOne(nextFirst); //first item, oldIndex 0
        for (int i = 0; i < size; i++) {
            newItems[i] = items[oldIndex];
            oldIndex = plusOne(oldIndex);
        }
        items = newItems;
        nextFirst = items.length - 1; //newItems stars from index 0
        nextLast = size;
    }

    /** expand the array when it's full */
    private void expandCheck() {
        if (size == items.length) {
            resize(size * 2);
        }
    }

    /** shrink the array when it's sparse */
    private void shrinkCheck() {
        if (items.length >= 16 && ((float) size / items.length) < 0.25) {
            resize(items.length / 2); //should be the length of array, not size
        }
    }

    @Override
    /** Adds an item of type T to the front of the deque. */
    public void addFirst(T item) {
        expandCheck();
        items[nextFirst] = item;
        nextFirst = minusOne(nextFirst);
        size += 1;
    }

    @Override
    /** Adds an item of type T to the back of the deque. */
    public void addLast(T item) {
        expandCheck();
        items[nextLast] = item;
        nextLast = plusOne(nextLast);
        size += 1;
    }

    @Override
    /**
     * Removes and returns the item at the front of the deque.
     * If no such item exists, returns null.
     */
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        nextFirst = plusOne(nextFirst);
        T xtoRemove = items[nextFirst];
        items[nextFirst] = null; //optional, loitering problem
        size -= 1;
        shrinkCheck();
        return xtoRemove;
    }

    @Override
    /**
     * Removes and returns the item at the back of the deque.
     * If no such item exists, returns null.
     */
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        nextLast = minusOne(nextLast);
        T xtoRemove = items[nextLast];
        items[nextLast] = null;
        size -= 1;
        shrinkCheck();
        return xtoRemove;
    }

    @Override
    /**
     * Gets the item at the given index,
     * where 0 is the front, 1 is the next item, and so forth.
     * If no such item exists, returns null. Must not alter the deque!
     */
    public T get(int index) {
        if (index >= size) {
            return null;
        }
        int p = plusOne(nextFirst); //index of the first item, don't modify nextFirst!
        return items[(p + index) % items.length];
    }

    @Override
    /** Prints the items in the deque from first to last, separated by a space */
    public void printDeque() {
        int p = plusOne(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[p] + " ");
            p = plusOne(p);
        }
        System.out.println();
    }

    @Override
    /** Returns true if deque is empty, false otherwise.*/
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    /** Returns the number of items in the deque.*/
    public int size() {
        return size;
    }

}
