package com.company;

import java.util.Scanner;

public class CommandShellWithoutRollbacks {
    static ISet<Object> setFiles;
    static ISet<Object> setDirectories;

    public static void main(String arg[]) {
        String input;
        Scanner sc = new Scanner(System.in);
        input = sc.nextLine();
        int n = Integer.parseInt(input);
        setFiles = new DoubleHashSet<Object>(n);
        setDirectories = new DoubleHashSet<Object>(n);

        for (int i = 0; i < n; i++) {
            String inputString = sc.nextLine();
            if (!inputString.equals("LIST")) {
                String command = inputString.split(" ")[0];
                String fd = inputString.split(" ")[1];

                if (command.equals("NEW")) {
                    if (fd.contains("/")) {
                        setDirectories.add(fd);
                    } else {
                        setFiles.add(fd);
                    }
                } else if (command.equals("REMOVE")) {
                    if (setDirectories.contains(fd)) {
                        setDirectories.remove(fd);
                    } else if (setFiles.contains(fd)) {
                        setFiles.remove(fd);
                    } else {
                        System.out.println("ERROR: cannot execute " + inputString);
                    }
                }
            } else {
                setFiles.printing();
                setDirectories.printing();
            }
        }

    }
}

interface ISet<T> {
    void add(T item) throws IllegalStateException; //// add item in the set

    void remove(T item) throws IllegalStateException; // remove an item from a set

    boolean contains(T item); // check if an item belongs to a set

    int size(); //return current number of elements

    boolean isEmpty(); // check if the set is empty

    int getPrime();

    int hashFunc1(T item);

    int hashFunc2(T item);

    int getNextPrime(int minN);

    boolean isPrime(int num);

    void printing();
}

class Defunct{
}

class DoubleHashSet<T> implements ISet<T> {

    private static final Defunct defunct = new Defunct();
    private int arraySize;
    private int size = 0;
    Object[] hashTable;

    /**
     * constructor of the set with double hashing
     *
     * @param n is maximum capacity of the set
     */

    public DoubleHashSet(int n) {
        arraySize = getNextPrime(n);
        int primeCount = getNextPrime(n);
        hashTable = new Object[primeCount];

    }

    /**
     * the function checks if number is prime or not
     *
     * @param num - number to check
     *            Time complexity O(n)
     * @return true if number is prime and false if not
     */
    public boolean isPrime(int num) {
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * function that find the next prime number
     *
     * @param minN - original number
     *             Time complexity O(n)
     * @return next prime number
     */
    public int getNextPrime(int minN) {
        for (int i = minN+1; true; i++) {
            if (isPrime(i)) {
                return i;
            }
        }
    }

    /**
     * function that find the largest prime number less than the set size
     * Time complexity O(n^2)
     *
     * @return the largest prime number less than the set size
     */

    public int getPrime() {
        int prime = 0;
        for (int i = arraySize - 1; i >= 1; i--) {
            for (int j = 2; j <= (int) Math.sqrt(i); j++) {
                if (i % j == 0) {
                    prime++;
                }
            }
            if (prime == 0) {
                return i;
            }
        }
        return prime;
    }

    /**
     * hash function that find preferred index position
     *
     * @param item - object from a set
     *             time complexity O(1)
     * @return ideal index position
     */
    public int hashFunc1(T item) {
        int hasVal = item.hashCode();
        hasVal = hasVal % arraySize;
        if (hasVal < 0) {
            hasVal += arraySize;
        }
        return hasVal;
    }

    /**
     * function to calculate second hash
     *
     * @param item - object from a set
     *             time complexity O(1)
     * @return step size
     */
    public int hashFunc2(T item) {
        int hasVal = item.hashCode();
        hasVal %= arraySize;
        if (hasVal < 0) {
            hasVal += arraySize;
        }
        int prime = getPrime();
        int res = prime - (hasVal % prime);
        return res;

    }

    /**
     * function to insert item into hash table
     *
     * @param item - object from a set
     *             time complexity O(1)
     * @throws IllegalStateException if current number of elements bigger or equal size of hash table
     */
    public void add(T item) throws IllegalStateException {
        if (arraySize <= size) {
            throw new IllegalStateException();
        } else {

            int hasVal = hashFunc1(item); // get index from first hash
            int stepSize = hashFunc2(item); // get index from second hash

            // in case a collision occurs
            if (hashTable[hasVal] != null) {
                while (hashTable[hasVal] != null) {
                    int newIndex = getNextPrime(hasVal);  // obtaining the new index.
                    if (hashTable[newIndex] == null || hashTable[newIndex] == defunct) {
                        hashTable[newIndex] = item;
                        size += 1;
                        break;
                    }
                    hasVal = (hasVal + stepSize) % arraySize;
                }
            }

            //if no collision occurs
            else if (hashTable[hasVal] == null || hashTable[hasVal] == defunct){
                hashTable[hasVal] = item;
                ++size;
            }
        }
    }

    /**
     * function remove an item from a set
     *
     * @param item - object from a set
     *             time complexity O(1)
     * @throws IllegalStateException if set is empty
     */
    public void remove(T item) throws IllegalStateException {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        if (!contains(item)){
            throw new IllegalStateException();
        }

        int hasVal = hashFunc1(item); // get index from first hash
        int stepSize = hashFunc2(item); // get index from second hash
        int newIndex = (hasVal + stepSize) % arraySize; //obtaining the new index in case of collision

        if (item.equals(hashTable[hasVal])) {
            hashTable[hasVal] = defunct;
            size -= 1;
        }

        if (item.equals(hashTable[newIndex])) {
            hashTable[newIndex] = defunct;
            size -= 1;
        }
    }

    /**
     * function check if an item belongs to a set
     *
     * @param item - object from a set
     *             time complexity O(1)
     * @return true if set contains item otherwise returns false
     */
    public boolean contains(T item) {

        int hasVal = hashFunc1(item);
        int stepSize = hashFunc2(item);

        boolean flag = false;
        if (item.equals(hashTable[hasVal])) {
            flag = true;
        } else {
            int newIndex = (hasVal + stepSize) % arraySize;
            if (item.equals(hashTable[newIndex])) {
                flag = true;
            }
        }
        return flag;
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
     * the function checks if set is empty
     * time complexity O(1)
     *
     * @return true if the set is empty otherwise returns false
     */
    public boolean isEmpty() {
        return size == 0;
    }

    public void printing() {
        for (int i = 0; i < arraySize; i++) {
            if (hashTable[i] != null) {
                System.out.print(hashTable[i] + " ");
            }
        }
    }
}

