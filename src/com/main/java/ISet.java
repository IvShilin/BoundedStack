package com.main.java;

public interface ISet<T>{
    void add(T item);
    void remove(T item);
    boolean contains(T item); // check if a item belongs to a set
    int size();
    boolean isEmpty();
}
