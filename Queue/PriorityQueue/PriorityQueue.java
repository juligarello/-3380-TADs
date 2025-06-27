public interface PriorityQueue<T extends Comparable<? super T>> {

    /**
     * @post Inserts e to the queue.
     */
    public void insert(T e);

    /**
     * @post Returns the number of elements in the queue.
     *   More formally, it satisfies: result = #this.
     */
    public int size();

    /**
     * @post Returns true iff the queue contains no elements.
     *   More formally, it satisfies: result = #this = 0.
     */
    public boolean isEmpty(); 
    
    /**
     * @pre !isEmpty() (throws NoSuchElementException)
     * @post Removes and returns the largest element of this queue.
     */
    public T removeMax(); 
 
    /**
     * @pre !isEmpty() (throws NoSuchElementException)
     * @post Return the largest element of this queue.
     */
    public T max();

    /**
     * @post Returns true if and only if the structure is a 
     *   valid queue.
     */
    public boolean repOK();
}

