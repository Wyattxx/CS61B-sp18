package synthesizer;
import java.util.Iterator;

public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] items;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     * invariants
     * the first item is at index first
     * the last item is at index last - 1 (except zero length)
     * the newly added item is at index last
     */
    public ArrayRingBuffer(int capacity) {
        // TODO: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        items = (T[]) new Object[capacity];
        this.fillCount = 0;
        this.capacity = capacity;
        first = 0;
        last = 0;
    }

    /** helper method */
    private int plusOne(int index) {
        return (index + 1) % capacity;
    }

    @Override
    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    public void enqueue(T x) {
        // TODO: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring buffer overflow");
        }
        items[last] = x;
        last = plusOne(last);
        fillCount += 1;
    }

    @Override
    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    public T dequeue() {
        // TODO: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        T returnItem = items[first];
        //items[first] = null;
        first = plusOne(first);
        fillCount -= 1;
        return returnItem;
    }

    @Override
    /**
     * Return oldest item, but don't remove it.
     */
    public T peek() {
        // TODO: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring buffer underflow");
        }
        return items[first];
    }

    // TODO: When you get to part 5, implement the needed code to support iteration.

    private class ArrayRingBufferIterator implements Iterator<T> {
        private int wisPos;

        public ArrayRingBufferIterator() {
            wisPos = first;
        }
        public boolean hasNext() {
            return wisPos < fillCount;
        }

        public T next() {
            if (!hasNext()) {
                throw new RuntimeException("Ring buffer underflow");
            }
            T returnItem = items[wisPos];
            wisPos = plusOne(wisPos);
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new  ArrayRingBufferIterator();
    }

}
