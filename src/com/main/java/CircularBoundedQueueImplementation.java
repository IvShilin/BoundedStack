package com.main.java;

/**
 * An implementation of CircularBounded Queue based on Arrays node
 * <li> queue has bounded capacity (fixed number of elements) </li>
 * <li> when the queue is full, pushing new elements forces the removal
 * of elements that were pushed the oldest </li>
 *
 * @param <T> any object
 * @author Elina Akimchenkova (BS21-07 e.akimchenkova@innopolis.university)
 */

interface CircularBoundedQueueI<T> {
    void offer(T value); // insert an element to the rear of the queue overwrite the oldest elements when the queue is full

    T poll() throws IllegalStateException; // remove an element from the front of the queue

    T peek() throws IllegalStateException; // look at the element at the front of the queue (without removing it)

    void flush(); // remove all elements from the queue boolean

    boolean isEmpty(); // is the queue empty?

    boolean isFull(); // is the queue full?

    int size(); // number of elements

    int capacity(); // maximum capacity of queue
}

public class CircularBoundedQueueImplementation<T> implements CircularBoundedQueueI<T> {
    private int front;
    private int rear;
    private int size;
    private final Object[] queue;
    private final int capacity;

    /**
     * constructor of the circular queue
     *
     * @param capacity is maximum capacity of the queue
     */
    public CircularBoundedQueueImplementation(int capacity) {
        front = 0;
        rear = 0;
        size = 0;
        this.capacity = capacity;
        queue = new Object[capacity];
    }

    /**
     * the function insert an element to the rear of the queue, overwrite the oldest elements when the queue is full
     * time complexity O(1)
     *
     * @param value is a value of element that we need to insert to the queue
     */
    public void offer(T value) {
        if (isFull()) {
            queue[rear] = value;
            front = (front + 1) % capacity;
        } else {
            size += 1;
        }
        queue[rear] = value;
        rear = (rear + 1) % capacity;
    }

    /**
     * the function remove an element from the front of the queue
     * time complexity O(1)
     *
     * @return remote element
     */
    public T poll() throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        } else {
            var res = (T) queue[front];
            front = (front + 1) % capacity;
            size = size - 1;
            return res;
        }

    }

    /**
     * function look at the element at the front of the queue
     * time complexity O(1)
     *
     * @return front element
     * @throws IllegalStateException used to indicate that queue is empty, we can't use this function
     */

    public T peek() throws IllegalStateException {
        if (isEmpty())
            throw new IllegalStateException();
        else return (T) queue[front];
    }

    /**
     * the function remove all elements from the queue
     * time complexity O(1)
     */
    public void flush() {
        size = 0;
        front = 0;
        rear = 0;
    }

    /**
     * the function checks if queue is empty
     * time complexity O(1)
     *
     * @return true if the queue is empty otherwise returns false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * the function checks if queue is full
     * time complexity O(1)
     *
     * @return true if the queue is full otherwise returns false
     */
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * size is number of elements
     * time complexity O(1)
     *
     * @return size
     */
    public int size() {
        return size;
    }

    /**
     * capacity is maximum capacity of the queue
     * time complexity O(1)
     *
     * @return capacity
     */
    public int capacity() {
        return capacity;
    }
}
