package List;

import java.util.NoSuchElementException;

public class ArrayList<T> implements List<T> {

    protected T[] list;
    private int size;
    public ArrayList(int length) {
        if(length <= 0)
            throw new IllegalArgumentException();

        list = (T[]) new Object[length];
        size = 0;
    }

    public void resize(int capacity) {
        if(capacity <= size)
            throw new IllegalArgumentException();

        T[] copy = (T[]) new Object[capacity];
        for(int i = 0; i < list.length; i++) {
            copy[i] = list[i];
        }

        list = copy;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T e) {
        boolean contains = false;
        for(int i = 0; i < list.length; i++) {
            if(list[i].equals(e)) {
                contains = true;
                break;
            }
        }

        return contains;
    }

    public void add(T e) {
        if (size == list.length)
            resize(2 * list.length);
        
        list[size] = e;
        size++;
    }

    public void add(int index, T e) {
        if (index < 0)
            throw new IndexOutOfBoundsException();
        if (size == list.length)
            resize(2 * list.length);

        for (int i = size; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = e;
        size++;
    }

    public boolean remove(T e) {
        if(isEmpty())
            throw new NoSuchElementException();
        if(!contains(e))
            return false;
        
        int index = indexOf(e);
        remove(index);

        return true;
    }

    public T remove(int index) {
        if(index < 0)
            throw new IndexOutOfBoundsException();
        if(isEmpty())
            throw new NoSuchElementException();
        
        T remove = list[index];
        for(int i = index; i < size - 1; i++) {
            list[i] = list[i + 1];
        }
        list[size - 1] = null;
        size--;

        return remove;
    }

    public void clear() {
        for (int i = 0; i < list.length; i++) {
            list[i] = null;
        }
        size = 0;
    }

    public T get(int index) {
        if(index < 0)
            throw new IndexOutOfBoundsException();
        if(isEmpty())
            throw new NoSuchElementException();
        
        return list[index];
    }

    public T set(int index, T e) {
        if(index < 0)
            throw new IndexOutOfBoundsException();
        if(isEmpty())
            throw new NoSuchElementException();

        T set = list[index];
        list[index] = e;

        return set;
    }

    public int indexOf(T e) {   
        int indexOf = -1;
        for(int i = 0; i < list.length; i++) {
            if(list[i].equals(e)) {
                indexOf = i;
                break;
            }
        }

        return indexOf;
    }
}
