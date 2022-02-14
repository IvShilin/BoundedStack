package com.main.java;

public interface CircularBoundedQueueI<T> {

    void offer(T value); // insert an element to the rear of the queue

    // overwrite the oldest elements
    // when the queue is full
    T poll() throws IllegalStateException; // remove an element from the front of the queue

    //
    T peek() throws IllegalStateException; // look at the element at the front of the queue

    // (without removing it)
    void flush(); // remove all elements from the queue boolean

    boolean isEmpty(); // is the queue empty?

    boolean isFull(); // is the queue full?

    int size(); // number of elements

    int capacity(); // maximum capacity
}
