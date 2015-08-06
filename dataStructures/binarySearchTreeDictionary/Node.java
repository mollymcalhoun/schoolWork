/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 4/9/2015
    Assignment: Project 5, Sorting algorithms
    Files: Timer.java, InstrumentedSorter.java, Comparator.java

    Class representing a node of the binary tree
*/

// The nodes of the dictionary tree are <key, value> pairs
class Node<K extends Comparable<K>, V> {

    // The necessary class members
    // The parent reference points to the parent of each node
    // and it's null for root of the dictionary tree
    Entry<K, V> entry;
    Node<K, V> parent;
    Node<K, V> leftChild;
    Node<K, V> rightChild;

    Node(Entry<K, V> entry) {
        this(entry, null, null, null);
    }

    Node(Entry<K, V> entry, Node<K, V> parent) {
        this(entry, parent, null, null);
    }

    Node(Entry<K, V> entry, Node<K, V> parent,
            Node<K, V> left, Node<K, V> right) {
        this.entry = entry;
        this.parent = parent;
        leftChild = left;
        rightChild = right;
    }
    
    // returns true if this node is a leaf
    public boolean isLeaf() {
        return leftChild == null && rightChild == null;
    }
}
