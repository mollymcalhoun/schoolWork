import java.util.Iterator;

// This is the interface for a tree-based dictionary of <key, value> pairs
// This interface extends the generic dictionary interface
public interface TreeDictionary<K extends Comparable<K>, V> extends Dictionary<K, V> {

    // the height of the tree
    public int height();
    
    // a pre order traversal iterator over entries of the dictionary tree
    public Iterator<Entry<K, V>> preorder();

    // a breadth first traversal iterator over entries of the dictionary tree
    public Iterator<Entry<K, V>> breadthFirst();

    // BONUS method!!!
    // an in order traversal iterator over entries of the dictionary tree
    public Iterator<Entry<K, V>> inorder();
    
    // if bonus inorder method is not implemented, you should implement
    // this method instead.
    // prints in order traversal of entries
    //public void inorder();

    // BONUS method!!!
    // a post order traversal iterator over entries of the dictionary tree
    public Iterator<Entry<K, V>> postorder();
    
    // if bonus postorder method is not implemented, you should implement
    // this method instead.
    // prints post order traversal of entries
    //public void postorder();

    // sets the default iterator traversal type
    // only 4 values are accepted: "preorder", "inorder", "postorder", "bfs"
    public void defaultIterator(String order);

    // pretty prints the dictionary tree (similar to in order traversal)
    // for the input sequence of <key, value> pairs: 
    // <5, may>, <3, mar>, <2, feb>, <1, jan>, <6, jun>, 
    // <12, dec>, <10, oct>, <11, nov>, <4, apr>
    // the output must look like this:
    /*
    ---------------<1,jan>
    ----------<2,feb>
    -----<3,mar>
    ----------<4,apr>
    <5,may>
    -----<6,jun>
    ---------------<10,oct>
    --------------------<11,nov>
    ----------<12,dec>
    */
    public void prettyPrint();
}
