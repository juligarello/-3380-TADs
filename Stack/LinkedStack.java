package Stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedStack<T> implements Stack<T> {

    private class Node {
        T item;
        Node next;
    }

    protected Node top;
    private int size;
    public LinkedStack() {
        top = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void push(T e) {
        Node aux = top;
        top = new Node();

        top.item = e;
        top.next = aux;
        size++;

    }

    public T pop() {
        if(isEmpty())
            throw new NoSuchElementException();

        T pop = top.item;
        top = top.next;
        size--;

        return pop;
    }

    public T top() {
        if(isEmpty())
            throw new NoSuchElementException();
        
        return top.item;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
        private Node current = top;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext())
                throw new NoSuchElementException();
            T item = current.item;
            current = current.next;
            return item;
        }
        };
    }

    public String toString() {
        ArrayList<T> list = new ArrayList<>();
        Node current = top;
        while(current != null) {
            list.add(current.item);
            current = current.next;
        }

        String result = "[ ";
        for (int i = size - 1; 0 <= i; i--) {
            if(i != 0) {
                result += list.get(i) + ", ";
            }   else    {
                result += list.get(i);
            }
        }
        result += "]";

        return result;
    }

    public boolean repOK() {
        if(size < 0)
            return false;
        
        int countNodes = 0;
        Node current = top;
        while(current != null && countNodes <= size) {
            countNodes++;
            current = current.next;
        }
        if(countNodes != size || current != null) {
            return false;
        }

        return true;
    }
}
