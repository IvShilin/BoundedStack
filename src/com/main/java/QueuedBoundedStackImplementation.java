package com.main.java;

public class QueuedBoundedStackImplementation<T> implements IBoundedStack<T> {
    private int top;
    private final int capacity;

    CircularBoundedQueueI<Object> q1;
    CircularBoundedQueueI<Object> q2;

    public QueuedBoundedStackImplementation(int capacity) {
        this.capacity = capacity;
        q1 = new CircularBoundedQueueImplementation<>(capacity);
        q2 = new CircularBoundedQueueImplementation<>(capacity);
    }

    @Override
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


    @Override
    public T pop() {
        if (q1.isEmpty()) {
            return null;
        }
        Object element = q1.peek();
        q1.poll();
        return (T) element;

    }


    @Override
    public T top() throws IllegalStateException {
        if (q1.isEmpty()) {
            return null;
        }
        Object element = q1.peek();
        return (T) element;


    }

    @Override
    public void flush() {
        q1.flush();
    }

    @Override
    public boolean isEmpty() {
        return q1.size() == 0;
    }

    @Override
    public boolean isFull() {
        return q1.size() == capacity;
    }

    @Override
    public int size() {
        return q1.size();
    }

    @Override
    public int capacity() {
        return capacity;
    }
}
