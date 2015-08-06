/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 4/9/2015
    Assignment: Project 5, Sorting algorithms
    Files: Timer.java, InstrumentedSorter.java, Comparator.java

    Entry class implementing comparable so that it may compare itself to
      other Entry objects
*/

// The Entries in the BinaryTree are <key, value> pairs
// The key (K) type must be Comparable
// Entry must impelement Comparable
public class Entry<K extends Comparable<K>, V> implements Comparable<Entry<K, V>> {

    protected K key;
    protected V value;

    protected Entry(K k, V v) {
        key = k;
        value = v;
    }

    public K key() {
        return key;
    }

    public V value() {
        return value;
    }
    
    // override toString method
    // toString method should print the entry as:
    // <key,value>
    @Override
    public String toString() {
        return "<" + key + ", " + value + ">";
    }
    
    // override compareTo method for Entry
    @Override
    public int compareTo(Entry<K, V> other) {
        if (this.key().compareTo(other.key()) > 0)
        {
            return 1;
        }
        else if (this.key().compareTo(other.key()) < 0)
        {
            return -1;
        }
        else
        {
            return 0;
        }
    }
}
