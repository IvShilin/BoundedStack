package com.main.java;

public class QueuedBoundedStackImplementation<T> implements IBoundedStack<T> {
    private int top;
    private int size;
    private final int capacity;

    CircularBoundedQueueI<Object> q1;
    CircularBoundedQueueI<Object> q2;

    public QueuedBoundedStackImplementation(int capacity) {
        this.capacity = capacity;

        q1 = new CircularBoundedQueueImplementation<>(capacity);
        q2 = new CircularBoundedQueueImplementation<>(capacity);
        size = 0;
    }

    //тут ошибка в пуше, меняю тут код и валится топ()
    @Override
    public void push(T value) {
        if (size < capacity) {
            q1.offer(value);
            size = size + 1;
        } else {
            for (int i = 0; i < q1.size(); i++) {
                q2.offer(q1.poll());
            }
            q1.offer(value);
            for (int j = 0; j < q1.size(); j++) {
                q1.offer(q2.poll());
            }
            size = size + 1;
        }

    }

    @Override
    public T pop() {
        if (q1.isEmpty()){
            return null;
        }
        Object element = q1.peek();
        q1.poll();
        size = size - 1;
        return (T) element;

    }


    @Override
    public T top() throws IllegalStateException {
        if (q1.isEmpty()) {
            return null;
        }
        Object element = q1.peek();
//
//        CircularBoundedQueueI<Object> q = q1;
//        q1 = q2;
//        q2 = q;
        return (T) element;


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
