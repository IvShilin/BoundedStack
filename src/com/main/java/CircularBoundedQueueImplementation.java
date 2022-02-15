package com.main.java;

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
     *
     * @param value is a value of element that we need to insert to the queue
     */
    @Override
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
     *
     * @return remote element
     */
    @Override
    public T poll() throws IllegalStateException {
        if (isEmpty()) {
            return null;
        } else {
            var res = (T) queue[front];
            front = (front + 1) % capacity;
            size = size - 1;
            //return (T) queue[front];
            return res;
        }

    }

    /**
     * function look at the element at the front of the queue
     *
     * @return front element
     * @throws IllegalStateException used to indicate that queue is empty, we can't use this function
     */
    @Override
    public T peek() throws IllegalStateException {
        if (isEmpty())
            return null;
        else return (T) queue[front];
    }

    /**
     * the function remove all elements from the queue
     */
    @Override
    public void flush() {
        size = 0;
        front = 0;
        rear = 0;
    }

    /**
     * the function checks if queue is empty
     *
     * @return true if the queue is empty otherwise returns true
     */
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * the function checks if queue is full
     *
     * @return true if the queue is full otherwise returns false
     */
    @Override
    public boolean isFull() {
        return size == capacity;
    }

    /**
     * size is number of elements
     *
     * @return size
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * capacity is maximum capacity of the queue
     *
     * @return capacity
     */
    @Override
    public int capacity() {
        return capacity;
    }
}
