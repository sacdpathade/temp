package com.sachin;

// A class to represent a node of a linked list
class Entry<K, V> {
    K key; // the key of the entry
    V value; // the value of the entry
    Entry<K, V> next; // the next node in the list

    // A constructor to create a new node with a given key and value
    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        this.next = null;
    }
}

// A class to represent a hashtable using an array and separate chaining
class HashTable<K, V> {
    private static final int SIZE = 10; // the size of the array
    private Entry<K, V>[] table; // the array of linked lists

    // A constructor to initialize the array and fill it with null values
    public HashTable() {
        table = new Entry[SIZE];
        for (int i = 0; i < SIZE; i++) {
            table[i] = null;
        }
    }

    // A method to compute the hash value of a given key
    public int getHash(K key) {
        return Math.abs(key.hashCode() % SIZE);
    }

    // A method to insert a new entry to the hashtable
    public void put(K key, V value) {
        // Compute the hash value and find the index in the array
        int hash = getHash(key);
        int index = hash % SIZE;

        // Create a new node with the key and value
        Entry<K, V> newEntry = new Entry<>(key, value);

        // If the array element is null, assign the new node to it
        if (table[index] == null) {
            table[index] = newEntry;
        } else {
            // Otherwise, append the new node to the front of the linked list
            newEntry.next = table[index];
            table[index] = newEntry;
        }
    }

    // A method to get the value of an entry by its key
    public V get(K key) {
        // Compute the hash value and find the index in the array
        int hash = getHash(key);
        int index = hash % SIZE;

        // If the array element is null, return null
        if (table[index] == null) {
            return null;
        } else {
            // Otherwise, traverse the linked list and compare the keys
            Entry<K, V> current = table[index];
            while (current != null) {
                // If the keys match, return the value
                if (current.key.equals(key)) {
                    return current.value;
                }
                // Move to the next node in the list
                current = current.next;
            }
            // If no match is found, return null
            return null;
        }
    }

    // A method to remove an entry by its key
    public void remove(K key) {
        // Compute the hash value and find the index in the array
        int hash = getHash(key);
        int index = hash % SIZE;

        // If the array element is null, do nothing
        if (table[index] == null) {
            return;
        } else {
            // Otherwise, traverse the linked list and compare the keys
            Entry<K, V> current = table[index];
            Entry<K, V> previous = null;
            while (current != null) {
                // If the keys match, remove the node from the list
                if (current.key.equals(key)) {
                    // If the node is the first one, assign the next node to the array element
                    if (previous == null) {
                        table[index] = current.next;
                    } else {
                        // Otherwise, link the previous node to the next node
                        previous.next = current.next;
                    }
                    // Break the loop
                    break;
                }
                // Move to the next node in the list
                previous = current;
                current = current.next;
            }
        }
    }
}

