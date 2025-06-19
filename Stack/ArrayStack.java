package Stack;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ArrayStack<T> implements Stack<T> {

    private static final int initial_CAPACITY = 8;
    protected T[] stack;
    private int size;
    public ArrayStack() {
        stack = (T[]) new Object[initial_CAPACITY];
        size = 0;
    }

    public void resize(int capacity) {
        if(capacity <= size)
            throw new IllegalArgumentException();

        T[] copy = (T[]) new Object[capacity];
        for(int i = 0; i < stack.length; i++) {
            copy[i] = stack[i];
        }

        stack = copy;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T e) {
        if(size == stack.length)
            resize(2 * stack.length);

        stack[size] = e;
        size++;
    }

    public T pop() {
        if(isEmpty())
            throw new NoSuchElementException();

        T pop = stack[size - 1];
        stack[size - 1] = null;
        size--;

        if(size > 0 && size == stack.length / 4)
            resize(stack.length / 2);
        return pop;
    }

    public T top() {
        if(isEmpty())
            throw new NoSuchElementException();
        
        return stack[size - 1];
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
        private int current = size - 1;

        @Override
        public boolean hasNext() {
            return current >= 0;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            return stack[current--];
        }
        };
    }

    public String toString() {
        String result = "[ ";
        for(int i = 0; i < size; i++) {
            result += stack[i];
            if (i < size - 1) {
                result += ", ";
            }
        }
        result += "]";

        return result;
    }

    public boolean repOK() {
        if(size < 0 && size > stack.length)
            return false;
        for(int i = 0; i < size; i++) {
            if(stack[i] == null)
                return false;
        }

        return true;
    }
}
