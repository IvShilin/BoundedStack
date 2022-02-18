package com.main.java;

/**
 * An implementation of CircularBounded Stack based on two bounded queues
 * CircularBounded Stack is an extension of a regular Stack ADT with two extra properties
 * <li> the stack has bounded capacity (fixed number of elements) </li>
 * <li> when the stack is full (its size reaches its maximum capacity),
 * pushing new elements forces the removal of elements that were pushed the oldest </li>
 *
 * @param <T> any object
 * @author Elina Akimchenkova (BS21-07 e.akimchenkova@innopolis.university)
 */

interface IBoundedStack<T> {
    void push(T value); // push an element onto the stack remove the oldest element when if stack is full

    T pop() throws IllegalStateException; // remove an element from the top of the stack

    T top() throws IllegalStateException; // look at the element at the top of the stack (without removing it)

    void flush(); // remove all elements from the stack

    boolean isEmpty(); // is the stack empty?

    boolean isFull(); // is the stack full?

    int size(); // number of elements

    int capacity(); // maximum capacity
}

public class QueuedBoundedStackImplementation<T> implements IBoundedStack<T> {
    private final int capacity;

    CircularBoundedQueueI<Object> q1;
    CircularBoundedQueueI<Object> q2;

    /**
     * constructor of two circular queues (in q1 stack is stored)
     *
     * @param capacity is maximum capacity of the queues
     */
    public QueuedBoundedStackImplementation(int capacity) {
        this.capacity = capacity;
        q1 = new CircularBoundedQueueImplementation<>(capacity);
        q2 = new CircularBoundedQueueImplementation<>(capacity);
    }

    /**
     * the function push an element onto the stack remove the oldest element when if stack is full
     * time complexity O(1)
     *
     * @param value is a value of element that we need to insert to the queue
     */

    public void push(T value) {
        if (q1.isEmpty()) {
            q1.offer(value);
        } else {
            while (!q1.isEmpty()) {
                q2.offer(q1.poll());
            }
            q1.offer(value);
            while (!q2.isEmpty() && !q1.isFull()) {
                q1.offer(q2.poll());
            }
        }
    }

    /**
     * the function remove an element from the top of the stack
     * time complexity O(1)
     *
     * @return remote element
     * @throws IllegalStateException if q1 (queue, where we store stack) is empty
     */
    public T pop() throws IllegalStateException {
        if (q1.isEmpty()) {
            throw new IllegalStateException();
        }
        Object element = q1.peek();
        q1.poll();
        return (T) element;

    }

    /**
     * the function look at the element at the top of the stack
     * time complexity O(1)
     *
     * @return front element
     * @throws IllegalStateException if q1 (queue, where we store stack) is empty
     */
    public T top() throws IllegalStateException {
        if (q1.isEmpty()) {
            throw new IllegalStateException();
        }
        Object element = q1.peek();
        return (T) element;
    }

    /**
     * the function remove all elements from the stack
     * time complexity O(1)
     */
    public void flush() {

        q1.flush();
    }

    /**
     * the function checks if queue is empty
     * time complexity O(1)
     *
     * @return true if the queue is empty otherwise returns false
     */
    public boolean isEmpty() {

        return q1.size() == 0;
    }

    /**
     * the function checks if queue is full
     * time complexity O(1)
     *
     * @return true if the queue is full otherwise returns false
     */
    public boolean isFull() {

        return q1.size() == capacity;
    }

    /**
     * size is number of elements
     * time complexity O(1)
     *
     * @return size
     */
    public int size() {

        return q1.size();
    }

    /**
     * capacity is maximum capacity of the stack
     * time complexity O(1)
     *
     * @return capacity
     */
    public int capacity() {

        return capacity;
    }
}
