package com.main.java;

import java.util.Scanner;

public class BoundedCommandsQueue<T> {
    private final int capacity;

    CircularBoundedQueueI<Object> q1;
    CircularBoundedQueueI<Object> q2;

    public BoundedCommandsQueue(int capacity) {
        this.capacity = capacity;
        q1 = (CircularBoundedQueueI<Object>) new QueuedBoundedStackImplementation<Object>(capacity);
        q2 = (CircularBoundedQueueI<Object>) new QueuedBoundedStackImplementation<Object>(capacity);
    }

    public static void main(String arg[]) {
        String input;
        try (Scanner sc = new Scanner(System.in)) {
            input = sc.nextLine();
        }
        String[] numbers = input.split("\\s");

        int n = Integer.parseInt(numbers[0]);
        int k = Integer.parseInt(numbers[1]);
        for (int i = 0; i < n; i++) {
            q1.
        }
    }
}
