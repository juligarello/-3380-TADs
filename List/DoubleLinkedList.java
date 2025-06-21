package List;

import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements List<T> {

    private class Node {
        T data;
        Node prev;
        Node next;

        Node(T data) {
            this.data = data;
        }
    }

    protected Node head;
    protected Node tail;
    private int size;
    public DoubleLinkedList() {
        head = null;
        tail = null;
        size = 0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean contains(T e) {
        Node aux = head;
        while(aux != null) {
            if(aux.data.equals(e)) {
                return true;
            }
            aux = aux.next;
        }

        return false;
    }

    public void add(T e) {
        Node add = new Node(e);
        if(isEmpty()) {
            head = add;
            tail = add;
        }   else    {
            tail.next = add;
            add.prev = tail;
            tail = add;
        }
        size++;
    }

    public void add(int index, T e) {
        if(index < 0 || index > size)
        throw new IndexOutOfBoundsException();

        Node add = new Node(e);
        
        if(index == 0) {
            add.next = head;
            if(head != null) {
                head.prev = add;
                head = add;
            }
            if(size == 0)
                tail = add;
        } else if(index == size) {
            add.prev = tail;
            if(tail != null) {
                tail.next = add;
                tail = add;
            }
            if(size == 0)
                head = add;
        }   else    {
            Node aux;
            if(index < size / 2) {
                aux = head;
                for(int i = 0; i < index; i++) {
                    aux = aux.next;
                }
            }   else    {
                aux = tail;
                for(int i = size - 1; i >= index; i--) {
                    aux = aux.prev;
                }
            }

        add.next = aux;
        add.prev = aux.prev;
        aux.prev.next = add;
        aux.prev = add;
        }
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

    public void clear() {
        head = null;
        tail = null;
        size = 0;
    }

    public T get(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if(isEmpty())
            throw new NoSuchElementException();
        
        Node aux;
        if(index < size / 2) {
            aux = head;
            for(int i = index; 0 < i; i--) {
                aux = aux.next;
            }
        }   else    {
            aux = tail;
            for(int i = index; i < size - 1; i++) {
                aux = aux.prev;
            }
        }

        return aux.data;
    }

    public T set(int index, T e) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if(isEmpty())
            throw new NoSuchElementException();

        Node aux;
        if(index < size / 2) {
            aux = head;
            for(int i = 0; i < index; i++) {
                aux = aux.next;
            }
        }   else    {
            aux = tail;
            for(int i = size - 1; i > index; i--) {
                aux = aux.prev;
            }
        }

        T old = aux.data;
        aux.data = e;

        return old;
    }

    public T remove(int index) {
        if(index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        if(isEmpty())
            throw new NoSuchElementException();
        
        Node aux = head;
        T old = null;
        if(size == 1) {
            old = head.data;
            head = tail = null;
        } else if(index == 0) {
            old = head.data;
            head = head.next;
            head.prev = null;
        } else if(index == size - 1) {
            old = tail.data;
            tail = tail.prev;
            tail.next = null;
        }   else    {
            for(int i = 0; i < index; i++) {
                aux = aux.next;
            }
            old = aux.data;

            aux.prev.next = aux.next;
            aux.next.prev = aux.prev;
        }
        size--;

        return old;
    }

    public int indexOf(T e) {
        Node aux = head;
        int index = 0;
        while(aux != null) {
            if(aux.data.equals(e)) {
                return index;
            }
            aux = aux.next;
            index++;
        }

        return -1;
    }
}
