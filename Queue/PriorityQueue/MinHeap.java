public class MinHeap<T extends Comparable<? super T>> {

    protected T[] queue;

    public MinHeap(T[] heap) {
        queue = heap;
    }

    /**
     * @post Returns true iff v is smaller than w.
     */
    protected boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public boolean repOk() {
        return ordered(0);
    }

    private boolean ordered(int k) {
        if(k > queue.length)
            return true;

        int left = 2*k + 1;
        int right = 2*k + 2;
        if(left < queue.length && less(queue[left], queue[k]))
            return false;
        if(right < queue.length && less(queue[right], queue[k]))
            return false;

        return ordered(left) && ordered(right);
    }
}
