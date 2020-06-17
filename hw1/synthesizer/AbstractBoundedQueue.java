package synthesizer;

abstract class AbstractBoundedQueue<T> implements BoundedQueue<T> {

    protected int fillCount;
    protected int capacity;

    @Override
    public int capacity() {
        return capacity;
    }

    @Override
    public int fillCount() {
        return fillCount;
    }

    @Override
    public boolean isEmpty() {
        return fillCount() == 0;
    }

    @Override
    public boolean isFull() {
        return fillCount() == capacity();
    }

    @Override
    /** return (but do not delete) item from the front */
    public abstract T peek();

    @Override
    /** delete and return item from the front */
    public abstract T dequeue();

    @Override
    /** add item x to the end */
    public abstract void enqueue(T x);

}
