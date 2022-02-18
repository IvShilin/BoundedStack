package com.company;
import java.util.Scanner;

public class BoundedCommandsQueue {
    static CircularBoundedQueueI<Object> q1;

    public static void main(String arg[]) {
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();

        String[] numbers = input.split("\\s");

        int n = Integer.parseInt(numbers[0]);
        int k = Integer.parseInt(numbers[1]);
        q1 = new CircularBoundedQueueImplementation<>(k);
        for (int i = 0; i < n; i++) {
            q1.offer(sc.nextLine()); //add n elements to the queue
        }
        for (int j = 0; j < k; j++) {
            System.out.println((String) q1.poll()); //print k elements of the queue
        }
    }
}

/**
 * An implementation of CircularBounded Queue based on Arrays node
 * <li> queue has bounded capacity (fixed number of elements) </li>
 * <li> when the queue is full, pushing new elements forces the removal
 * of elements that were pushed the oldest </li>
 * @author Elina Akimchenkova (BS21-07 e.akimchenkova@innopolis.university)
 * @param <T> any object
 */

interface CircularBoundedQueueI<T> {
    void offer(T value); // insert an element to the rear of the queue
    T poll(); // remove an element from the front of the queue
    boolean isEmpty(); //is the queue is empty?
    boolean isFull(); //is the queue is full?
}

class CircularBoundedQueueImplementation<T> implements CircularBoundedQueueI<T>{
    private int front;
    private int rear;
    private int size;
    private int var;
    private final Object[] queue;
    private final int capacity;

    public CircularBoundedQueueImplementation(int capacity) {
        front = 0;
        rear = 0;
        size = 0;
        this.capacity = capacity;
        queue = new Object[capacity];
    }

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


    public T poll() {
        if (isEmpty()) {
            return null;
        } else {
            T var;
            var = (T) queue[front];
            front = (front + 1) % capacity;
            size = size - 1;
            return var;
        }
    }

    public boolean isEmpty() {
        return size == 0;
    }
    public boolean isFull() {
        return size == capacity;
    }
}