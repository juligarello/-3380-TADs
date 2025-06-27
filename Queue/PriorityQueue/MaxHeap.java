import java.util.NoSuchElementException;

public class MaxHeap<T extends Comparable<? super T>> implements PriorityQueue<T> {

    protected static final int initial_CAPACITY = 8;
    protected T[] queue;
    protected int size;

    public MaxHeap() {
        queue = (T[]) new Object[initial_CAPACITY + 1];
        size = 0;
    }

    public MaxHeap(T[] keys) {
        size = keys.length;
        queue = (T[]) new Object[size + 1];
        for(int i = 0; i < size; i++) {
            queue[i + 1] = keys[i];
        }
        for(int k = size/2; k >= 1; k--) {
            sink(k);
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int capacity) {
        if (size >= capacity)
            throw new IllegalArgumentException();

        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[i];
        }
        queue = copy;
    }

    /**
     * @post Returns true iff v is smaller than w.
     */
    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    /**
     * @pre {@code 0<=i<a.length && 0<=j<a.length}
     * @post Swaps the elements in positions i and j of a.
     */
    protected static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }
    
    private void swim(int k) {
        while(k > 1 && less(queue[k/2], queue[k])) {
            exch(queue, k/2, k);
            k = k/2;
        }
    }

    private void sink(int k) {
        while(2*k <= size) {
            int j = 2*k;
            if(j < size && less(queue[j], queue[j+1]))
                j++;
            if(!less(queue[k], queue[j]))
                break;

            exch(queue, k, j);
            k = j;
        }
    }

    public void insert(T e) {
        if (size == queue.length)
            resize(2 * queue.length);
        
        size++;
        queue[size] = e;
        swim(size);
    }

    public T removeMax() {
        if(isEmpty())
            throw new NoSuchElementException();
        
        T remove = queue[1];
        exch(queue, 1, size);
        size--;

        queue[size + 1] = null;
        sink(1);

        return remove;
    }

    public T max() {
        if(isEmpty())
            throw new NoSuchElementException();

        return queue[1];
    }

    public boolean repOK() {
        for(int i = 1; i <= size; i++) {
            if(queue[i] == null)
                return false;
        }
        for(int i = size + 1; i < queue.length; i++) {
            if(queue[i] != null)
                return false;
        }
        if(queue[0] != null)
            return false;
        
        return ordered(1);
    }

    private boolean ordered(int k) {
        if(k > size)
            return true;

        int left = 2*k;
        int right = 2*k + 1;
        if(left <= size && less(queue[k], queue[left]))
            return false;
        if(right <= size && less(queue[k], queue[right]))
            return false;


        return ordered(left) && ordered(right);
    }
}
