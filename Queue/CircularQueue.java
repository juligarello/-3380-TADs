package Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularQueue<T> implements Queue<T> {
    
    protected static final int initial_CAPACITY = 8;
    protected T[] queue;
    protected int size;
    protected int first;
    protected int last;

    public CircularQueue() {
        queue = (T[]) new Object[initial_CAPACITY];
        size = 0;
        first = 0;
        last = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private void resultize(int capacity) {
        if (size >= capacity)
            throw new IllegalArgumentException();

        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = queue[(first + i) % queue.length];
        }
        queue = copy;
        first = 0;
        last = size;
    }

    public void enqueue(T item) {
        if (size == queue.length)
            resultize(2 * queue.length);

        queue[last] = item;
        last = (last + 1) % queue.length;
        size++;
    }

    public T dequeue() {
        if (isEmpty())
            throw new NoSuchElementException();

        T dequeue = queue[first];
        queue[first] = null;
        first = (first + 1) % queue.length;
        size--;

        return dequeue;
    }

    public T peek() {
        if (isEmpty())
            throw new NoSuchElementException();

        return queue[last - 1];
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
        private int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T item = queue[(first + index) % queue.length];
            index++;
            return item;
        }
        };
    }

    public boolean repOK() {
        if (first < 0 || first >= queue.length)
            return false;
        if (last < 0 || last >= queue.length)
            return false;
        if (size < 0 || size > queue.length)
            return false;

        int i = first;
        int count = size;
        while (count > 0) {
            if (queue[i] == null)
                return false;
            i = (i+1) % queue.length;
            count--;
        }
        if (i != last)
            return false;

        return true;
    }


    public String toString() {
        String result = "[ ";
        for (int i = 0; i < size; i++) {
            result += queue[(first + i) % queue.length];
            if (i < size -1)
                result += ", ";
        }
        result += "]";
        return result;
    }
}