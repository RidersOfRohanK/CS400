// --== CS400 File Header Information ==--
// Name: Peilin Lu
// Email: plu39@wisc.edu
// Team: Blue
// Group: ED
// TA: Yelun
// Lecturer: Gary
// Notes to Grader:

import java.util.List;
import java.util.LinkedList;
import java.util.NoSuchElementException;

class Node<K, V> {
    K key;
    V value;

    public Node(K key, V value) { 
        this.key = key; 
        this.value = value; 
    } 
}

class HashTableMap<K, V> implements MapADT<K, V> {
    private LinkedList<Node<K,V>>[] array;

    public HashTableMap(int capacity) {
        array = (LinkedList<Node<K,V>>[]) new LinkedList[capacity];
        for (int i=0;i<capacity;++i) {
            array[i] = new LinkedList<Node<K,V>>();
        }
    }

    public HashTableMap() {
        this(10);
    }

    public int capacity() {
        return array.length;
    }

	public boolean put(K key, V value) {
        if (key == null) return false;
        if (((size() + 1) * 1.0) / (array.length * 1.0) >= 0.85) grow();
        List<Node<K,V>> a = array[(key.hashCode() % array.length + array.length) % array.length];
        for (int i = 0; i < a.size(); i++) {
            Node<K,V> node = a.get(i);
            if (node.key.equals(key)) {
                return false;
            }
        }
        a.add(new Node<K,V>(key, value));
        return true;
    }

    private void grow() {
        LinkedList<Node<K,V>>[] old = array;
        int capacity = old.length * 2;
        array = (LinkedList<Node<K,V>>[]) new LinkedList[capacity];
        for (int i=0;i<capacity;++i) {
            array[i] = new LinkedList<Node<K,V>>();
        }
        for (int i=0; i<old.length; ++i) {
            List<Node<K,V>> a = old[i];
            for (int j=0; j<a.size(); ++j) {
                Node<K,V> node = a.get(j);
                put(node.key, node.value);
            }
        }
    }

	public V get(K key) throws NoSuchElementException {
        List<Node<K,V>> a = array[(key.hashCode() % array.length + array.length) % array.length];
        for (int i = 0; i < a.size(); i++) {
            Node<K,V> node = a.get(i);
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        throw new NoSuchElementException();
    }

	public int size() {
        int total = 0;
        for (int i = 0; i < array.length; i++) {
            total += array[i].size();
        }
        return total;
    }

	public boolean containsKey(K key) {
        try {
            get(key);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

	public V remove(K key) {
        List<Node<K,V>> a = array[(key.hashCode() % array.length + array.length) % array.length];
        for (int i = 0; i < a.size(); i++) {
            Node<K,V> node = a.get(i);
            if (node.key.equals(key)) {
                V value = node.value;
                a.remove(i);
                return value;
            }
        }
        return null;
    }

	public void clear() {
        for (int i=0;i<array.length;++i) {
            array[i] = new LinkedList<Node<K,V>>();
        }
    }
}