public class HeapSort<T extends Comparable<? super T>> {

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

    private void sink(Comparable[] queue, int k, int size) {
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

    public void sort(Comparable[] a) {
        int N = a.length;

        for(int k = N/2; k >= 1; k--) {
            sink(a, k, N);
        }
        while(N > 1) {
            exch(a, 1, N);
            N--;
            sink(a, 1, N);
        }
    }
}
