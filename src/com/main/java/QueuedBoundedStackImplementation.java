package com.main.java;

public class QueuedBoundedStackImplementation<T> implements IBoundedStack<T> {
    private int top;
    private int size;
    private final Object[] stack;
    private final int capacity;

    CircularBoundedQueueI<Object> q1;
    CircularBoundedQueueI<Object> q2;

    public QueuedBoundedStackImplementation(int capacity) {
        this.capacity = capacity;

        q1 = new CircularBoundedQueueImplementation<>(capacity);
        q2 = new CircularBoundedQueueImplementation<>(capacity);
        stack = new Object[capacity];

        size = 0;
    }

    @Override
    public void push(T value) {
        if (stack.isFull()) {
            stack[top] = value;
            top = (top + 1) % capacity;
        } else {
            size += 1;
        }
        stack[top] = value;
    }

    @Override
    public T pop() {
        if (q1.isEmpty())
            return null;
        else {
            while (q1.size() != 1) {
                q2.offer(q1.peek());
                q1.poll();
            }
            q1.poll();
            size = size - 1;

            CircularBoundedQueueI<Object> q = q1;
            q1 = q2;
            q2 = q;
            return (T) q1.peek();
        }

    }

    @Override
    public T top() throws IllegalStateException {
        if (q1.isEmpty()) {
            return null;
        } else {
            return (T) q1.peek();
        }
    }

    @Override
    public void flush() {
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public int capacity() {
        return capacity;
    }
}
