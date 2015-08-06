import java.util.List;
import java.util.Set;

// This is the interface for a dictionary of <key, value> pairs
public interface Dictionary<K extends Comparable<K>, V> {

    // the number of entries in dictionary
    public int size();

    // checks if dictionary is empty
    public boolean isEmpty();

    // insert a <key, value> pair
    // if key exists, updates value
    public void insert(K k, V v);

    // finds a value based on a key
    // if key not found, return null
    public V find(K k);

    // removes an entry based on a key
    public void remove(K k);

    // make the dictionary empty
    public void makeEmpty();
    
    // returns a java.util.Set of all keys in dictionary
    public Set<K> getKeys();
    
    // returns a java.util.Set of all entries in dictionary
    public Set<Entry<K, V>> getEntries();
    
    // returns a java.util.List of all values in dictionary
    public List<V> getValues();
}
