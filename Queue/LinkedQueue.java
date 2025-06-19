package Queue;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedQueue<T> implements Queue<T> {

    private class Node {
        T item;
        Node next;
    }

    protected Node first;
//  protected Node last;
    private int size;

    public LinkedQueue() {
        first = null;
    //  last = null;
        size = 0;
    }
    
    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
    
    public void enqueue(T e) {
        Node aux = new Node();
        aux.item = e;

        if (isEmpty()) {
            first = aux;
        }   else    {
            Node current = first;
            while (current.next != null) {
                current = current.next;
            }
            current.next = aux;
        }

        size++;
    }
    
    public T dequeue() {
        if(isEmpty())
            throw new NoSuchElementException();
        
        T dequeue = first.item;
        first = first.next;
        size--;

        return dequeue;
    }
 
    public T peek() {
        if(isEmpty())
            throw new NoSuchElementException();

        return first.item;
    }
    
    public Iterator<T> iterator() {
        return new Iterator<T>() {
        private Node current = first;

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
    
    public boolean repOK() {
        if (size < 0) {
            return false;
        }

        int countNodes = 0;
        Node current = first;
        while (current != null) {
        countNodes++;
        current = current.next;
        }

        return countNodes == size;
    }
    
    public String toString() {
        String result = "[ ";
        Node current = first;
        for (int i = 0; i < size; i++) {
            result += current.item;
            if (i < size - 1) {
                result += ", ";
            }
            current = current.next;
        }
        result += "]";

        return result;
    }
}
