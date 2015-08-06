/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 4/27/2015
    Assignment: Project 6, Binary tree
    Files: Entry.java, Node.java, Dictionary.java, TreeDictionary.java
             BinaryTree.java, TestDict.java

    This class implements all of the operations specified in the Dictionary
      and TreeDictionary interfaces, including all four iterators.
*/

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.NoSuchElementException;

/*
    Binary search tree class with various iterators and operations
*/
public class BinaryTree<K extends Comparable<K>, V> 
            implements TreeDictionary<K, V>, Iterable<Entry<K, V>>
{
    private int size;
    private Node<K, V> root;
    private String iterationOrder;
    
    public BinaryTree()
    {
        this.iterationOrder = "preorder";
    }
    
    /*
        Private class for pre-order iterator utilizing a stack
          for traversal
    */
    private class PreOrder implements Iterator<Entry<K, V>>
    {

        private Node<K, V> nextNode;
        private Stack<Node<K, V>> stack;

        PreOrder() {
            nextNode = root;
            stack = new Stack<Node<K, V>>();
            stack.push(root);
        }

        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        /*
            I got the method of using a stack here from this page:
            http://www.geeksforgeeks.org/iterative-preorder-traversal/
        */
        @Override
        public Entry<K, V> next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            else
            {
                // Whatever is on the top of the stack is what we want
                Node<K, V> n = stack.pop();
                // Push the children of n onto the stack
                // We want to traverse the left subtrees first, so the
                //   left child is always pushed second
                if (n.rightChild != null)
                {
                    stack.push(n.rightChild);
                }
                if (n.leftChild != null)
                {
                    stack.push(n.leftChild);
                }
                return n.entry;
            }
        }

        @Override
        public void remove()
        {
            // Dummy method
        }
    }

    /*
        Private class for in-order iterator
    */
    private class InOrder implements Iterator<Entry<K, V>>
    {
        private Node<K, V> nextNode;

        InOrder()
        {
            nextNode = root;
            if (nextNode != null)
            {
                // We want to start with the leftmost element
                goDownAndLeft();
            }
        }

        // Small method to go to the leftmost child of the current subtree
        private void goDownAndLeft()
        {
            while (nextNode.leftChild != null)
            {
                nextNode = nextNode.leftChild;    
            }
        }

        /*
            Adapted from this Stack Overflow thread:
            http://stackoverflow.com/a/12851421
        */
        private Node<K, V> findNext()
        {
            Node<K, V> returnNode = nextNode;
            // Go right if possible
            if (nextNode.rightChild != null)
            {
                nextNode = nextNode.rightChild;
                goDownAndLeft();
                //System.out.println("Returning " + returnNode.entry.toString());
                //System.out.println("Nextnode is " + nextNode.entry.toString());
                return returnNode;
            }
            else // Go up if we can't go right
            {
                boolean goingUp = true;
                while (goingUp)
                {
                    // Check if we're at the root. This condition will
                    //   only be passed when we're coming from the right,
                    //   so the tree will be fully traversed.
                    if (nextNode.parent == null)
                    {
                        nextNode = null;
                        goingUp = false;
                        //System.out.println("Returning " + returnNode.entry.toString());
                        //System.out.println("Nextnode is " + nextNode.entry.toString());
                        return returnNode;
                    }
                    // Are we coming from the left? If so, we want to return this.
                    else if (nextNode.parent.leftChild == nextNode)
                    {
                        nextNode = nextNode.parent;
                        goingUp = false;
                        //System.out.println("Returning " + returnNode.entry.toString());
                        //System.out.println("Nextnode is " + nextNode.entry.toString());
                        return returnNode;
                    }
                    // Keep going up if we didn't return anything
                    nextNode = nextNode.parent;
                }
            }
            //System.out.println("Nextnode is " + nextNode.entry.toString());
            //System.out.println("Returning " + returnNode.entry.toString());
            return returnNode;
        }

        @Override
        public boolean hasNext()
        {
            //System.out.println("checking if has next");
            return nextNode != null;
        }

        @Override
        public Entry<K, V> next()
        {
            Node<K, V> n = nextNode;
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            else
            {
                n = findNext();
            }
            return n.entry;
        }

        @Override
        public void remove()
        {
            // Dummy method
        }
    }

    /*
        Private class for post-order iterator 
    */
    private class PostOrder implements Iterator<Entry<K, V>>
    {
        private Node<K, V> nextNode;
        private Stack<Node<K, V>> stack;

        PostOrder()
        {
            nextNode = root;
            stack = new Stack<Node<K, V>>();
            if (nextNode != null)
            {
                fillStack(nextNode);    
            }
        }

        // Pre-fill the stack with the nodes in the correct order
        // Not exactly the most efficient solution and it will
        //   break if you remove anything while iterating
        private void fillStack(Node<K, V> n)
        {
            if (n != null)
            {   
                stack.push(n);
                if (n.rightChild != null)
                {
                    fillStack(n.rightChild);
                }
                if (n.leftChild != null)
                {
                    fillStack(n.leftChild);
                }
            }
        }

        @Override
        public boolean hasNext()
        {
            return !stack.isEmpty();
        }

        // Just pop from the stack and return the top node's entry
        @Override
        public Entry<K, V> next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            else
            {
                return stack.pop().entry;
            }
        }

        @Override
        public void remove()
        {
            // Dummy method
        }
    }

    /*
        Private class for breadth-first iterator
    */
    private class BreadthFirst implements Iterator<Entry<K, V>>
    {
        private Node<K, V> nextNode;
        private Queue<Node<K, V>> queue;

        BreadthFirst()
        {
            nextNode = root;
            queue = new LinkedList<Node<K, V>>();
            if (nextNode != null)
            {
                queue.add(root);
            }
        }

        @Override
        public boolean hasNext()
        {
            return !queue.isEmpty();
        }

        /*
            This is adapted from the pseudocode here:
            http://en.wikipedia.org/wiki/Tree_traversal#Breadth-first_2

            Dequeue the element at the end of the queue, then enqueue
              its left and right children. After all elements from a given
              level have been dequeued, the queue will represent all of
              the elements on the next level.
        */
        @Override
        public Entry<K, V> next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            }
            else
            {
                nextNode = queue.remove();
                if (nextNode.leftChild != null)
                {
                    // If a left child exists, we want to enqueue it
                    queue.add(nextNode.leftChild);
                }
                if (nextNode.rightChild != null)
                {
                    // Same thing for the right child
                    queue.add(nextNode.rightChild);
                }
                return nextNode.entry;
            }
        }

        @Override
        public void remove()
        {
            // Dummy method
        }
    }

    // Kills the tree from the root and makes its size 0
    @Override
    public void makeEmpty()
    {
        size = 0;
        root = null;
    }

    // Return the number of elements in the tree
    @Override
    public int size()
    {
        return size;
    }

    // If we don't have a root we don't have anything
    @Override
    public boolean isEmpty()
    {
        return root == null;
    }

    // Find the proper place for the <k, v> pair and insert it
    @Override
    public void insert(K k, V v)
    {
        if (isEmpty()) // Make it the root
        {
            Entry<K, V> e = new Entry<K, V>(k, v);
            root = new Node<K, V>(e);
            size++;
        }
        else // Make a new node and find a spot for it
        {
            Entry<K, V> e = new Entry<K, V>(k, v);
            Node<K, V> n = new Node<K, V>(e);
            Node<K, V> current = root;
            insert(n, current);
        }
    }

    // This method does the actual heavy lifting for insertion
    public void insert(Node<K, V> n, Node<K, V> current)
    {
        // If our new node's key is less than the current one...
        if (n.entry.compareTo(current.entry) < 0)
        {
            if (current.leftChild == null)
            {
                // If there's room, make n the current node's left child
                current.leftChild = n;
                n.parent = current;
                size++;
            }
            else
            {
                // If there's something there, move down and left
                //   and call insert again
                current = current.leftChild;
                insert(n, current);
            }
        }
        else if (n.entry.compareTo(current.entry) > 0) // is n > current?
        {
            if (current.rightChild == null)
            {
                // Put n at current's right child if possible
                current.rightChild = n;
                n.parent = current;
                size++;
            }
            else
            {
                // If there's no room, go right and try again
                current = current.rightChild;
                insert(n, current);
            }
        }
        else
        {
            // If a node with the same key as n already exists, replace
            //   its value with the value of n
            current.entry.value = n.entry.value;
        }
    }

    // Convenience method that calls the real one
    @Override
    public V find(K key)
    {
        return find(root, key);
    }

    // Find a node with the provided key if it exists
    public V find(Node<K, V> n, K key)
    {
        V target = null;
        while (n != null)
        {
            // Is the current node what we're looking for? If not...
            if (key.equals(n.entry.key()))
            {
                target = n.entry.value();
                n = null;
            }
            else if (key.compareTo(n.entry.key()) < 0) // key < n's key
            {
                // Go left and continue the loop
                n = n.leftChild;
            }
            else // Only remaining possiblity is (key > n's key)
            {
                // Go right and continue the loop
                n = n.rightChild;
            }
        }
        //System.out.println("Entry not found.");
        return target;
    }

    // findNode is exactly the same as find, but it returns the whole node
    public Node<K, V> findNode(K key)
    {
        return findNode(root, key);
    }

    // Leaving this uncommented because it's the same as find()
    public Node<K, V> findNode(Node<K, V> n, K key)
    {
        Node<K, V> target = null;
        while (n != null)
        {
            if (key.equals(n.entry.key()))
            {
                target = n;
                n = null;
            }
            else if (key.compareTo( n.entry.key()) < 0)
            {
                n = n.leftChild;
            }
            else
            {
                n = n.rightChild;
            }
        }
        //System.out.println("Entry not found.");
        return target;
    }    
    
    // Store the keys of the tree in a hash set and return it
    @Override
    public Set<K> getKeys()
    {
        Set<K> keys = new HashSet<K>(size);
        Iterator<Entry<K, V>> it = new InOrder();
        // Iterate over the entries using the in order iterator,
        //   adding each key
        while (it.hasNext())
        {
            //System.out.println("Adding " + it.next().key());
            keys.add(it.next().key());
        }
        return keys;
    }

    // Store the values of the tree in an array list and return it    
    @Override
    public List<V> getValues()
    {
        List<V> values = new ArrayList<V>();
        Iterator<Entry<K, V>> it = new InOrder();
        // Iterate over the tree using the in order iterator,
        //   adding each value
        while (it.hasNext())
        {
            //System.out.println("Adding " + it.next().key());
            values.add(it.next().value());
        }
        return values;
    } 
    
    // Store the entries of the tree in a hash set and return it
    @Override
    public Set<Entry<K, V>> getEntries()
    {
        Set<Entry<K, V>> entries = new HashSet<Entry<K, V>>(size);
        Iterator<Entry<K, V>> it = new InOrder();
        // Iterate over the tree using the in order iterator,
        // adding each entry
        while (it.hasNext())
        {
            //System.out.println("Adding " + it.next().key());
            entries.add(it.next());
        }
        return entries;
    }
    
    // Set the default iterator that will be returned by the iterator() method
    @Override
    public void defaultIterator(String order)
    {
        boolean legal = order.matches("(preorder|inorder|postorder|bfs)");
        if (legal)
        {
            iterationOrder = order;    
        }
        else
        {
            System.out.println("Illegal iterator type: " + order);
        }
    }
    
    // Returns a pre order iterator
    @Override
    public Iterator<Entry<K, V>> preorder()
    {
        this.iterationOrder = "preorder";
        return new PreOrder();
    }
    
    // Returns an in order iterator
    @Override
    public Iterator<Entry<K, V>> inorder()
    {
        this.iterationOrder = "inorder";
        return new InOrder();
    }
    
    // Returns a post order iterator
    @Override
    public Iterator<Entry<K, V>> postorder()
    {
        this.iterationOrder = "postorder";
        return new PostOrder();
    }
    
    // Returns a breadth first iterator
    @Override
    public Iterator<Entry<K, V>> breadthFirst()
    {
        this.iterationOrder = "bfs";
        return new BreadthFirst();
    }
    
    // Returns an iterator based on whatever is the current default
    //   type of iterator
    @Override
    public Iterator<Entry<K, V>> iterator()
    {
        Iterator<Entry<K, V>> it = null;
        switch (iterationOrder)
        {
            case "preorder":
                it = preorder();
                break;
            case "inorder":
                it = inorder();
                break;
            case "postorder":
                it = postorder();
                break;
            case "bfs":
                it = breadthFirst();
                break;
        }
        return it;
    }
    
    // Finds a node by its key and removes it
    @Override
    public void remove(K key)
    {
        Node<K, V> target = findNode(key);
        if (target == null) // No node with this key exists
        {
            System.out.println("No node to remove");
        }
        else if (target == root && root.isLeaf()) // Root is the only node
        {
            size--;
            root = null;
        }
        else if (target.isLeaf()) // The target is a leaf
        {
            size--;
            // Make the target's appropriate child null
            if (target.parent.leftChild == target)
            {
                target.parent.leftChild = null;
            }
            else if (target.parent.rightChild == target)
            {
                target.parent.rightChild = null;
            }
        }
        // The target has one child
        else if ((target.leftChild != null || target.rightChild != null)
                && !(target.leftChild != null && target.rightChild != null))
        {
            size--;
            if (target == root) // Target is root
            {
                if (target.leftChild != null) // Put left child in target's place
                {
                    root = target.leftChild;
                    target.leftChild.parent = null;
                }
                else if (target.rightChild != null) // Same, but for right child
                {
                    root = target.rightChild;
                    target.rightChild.parent = null;
                }
            }
            else if (target.parent.leftChild == target) // Target is a left child
            {
                if (target.leftChild != null) // Put left child in target's place
                {
                    target.parent.leftChild = target.leftChild;
                    target.leftChild.parent = target.parent;
                }
                else if (target.rightChild != null) // Same, but for right child
                {
                    target.parent.leftChild = target.rightChild;
                    target.rightChild.parent = target.parent;
                }
            }
            else if (target.parent.rightChild == target) // Target is right child
            {
                if (target.leftChild != null) // Put left child in target's place
                {
                    target.parent.rightChild = target.leftChild;
                    target.leftChild.parent = target.parent;
                }
                else if (target.rightChild != null) // Same but for right child
                {
                    target.parent.rightChild = target.rightChild;
                    target.rightChild.parent = target.parent;
                }
            }
        }
        // Target has two children
        else if (target.leftChild != null && target.rightChild != null)
        {
            size--;
            Node<K, V> n = target.rightChild;
            
            while (n.leftChild != null)
            {
                // We want the leftmost child of target's right subtree,
                //   this is where we will attach the target's former left
                //   child when it is deleted
                n = n.leftChild;
            }
            n.leftChild = target.leftChild;
            n.leftChild.parent = n;
            // Make target's right child the target's parent
            target.rightChild.parent = target.parent;
            if (target != root)
            {
                if (target.parent.leftChild == target)
                {
                    // If target is a left child, make its right child
                    //   the new left child of its parent
                    target.parent.leftChild = target.rightChild;
                }
                else
                {
                    // If target is a right child, make its right child
                    //   the new right child of its parent
                    target.parent.rightChild = target.rightChild;
                }
            }
            else
            {
                // If we're deleting the root then we need to set the new root
                root = n;
            }
        }
    }
    
    // Convenience method
    @Override
    public int height()
    {
        Node<K, V> n = root;
        return height(n);
    }

    /*
        Basic method adapted from the question of this Stack Overflow thread
        http://stackoverflow.com/q/16677378

        Recursively explore the tree to find its height.
    */
    public int height(Node<K, V> n)
    {
        if (n.isLeaf()) // Base case, n is a leaf
        {
            // This subtree is a leaf of height 1
            return 1;
        }
        else if (n.rightChild == null) // If n has no right child
        {
            return height(n.leftChild) + 1;
        }
        else if (n.leftChild == null) // If n has no left child
        {
            return height(n.rightChild) + 1;
        }
        else // n has left and right children
        {
            return Math.max(height(n.leftChild), height(n.rightChild)) + 1;
        }
    }

    // Find the depth of the node with the specified key,
    //   used to calculate the offset of the entry for prettyPrint()
    public int findNodeDepth(K key)
    {
        return findNodeDepth(root, key);
    }

    // This is almost exactly the same as the find() method except
    //   that it calculates depth as it searches and returns the depth
    public int findNodeDepth(Node<K, V> n, K key)
    {
        int depth = 0;
        while (n != null)
        {
            if (key.equals(n.entry.key()))
            {
                return depth;
            }
            else if (key.compareTo(n.entry.key()) < 0)
            {
                depth++;
                n = n.leftChild;
            }
            else
            {
                depth++;
                n = n.rightChild;
            }
        }
        //System.out.println("Entry not found.");
        return depth;
    }
    
    // Uses the in order iterator to traverse the tree, printing it
    //   to the console in a readable format as it goes
    @Override
    public void prettyPrint()
    {
        Iterator<Entry<K, V>> it = inorder();
        Entry<K, V> e;
        Node<K, V> n;
        int depth;
        while (it.hasNext())
        {
            e = it.next();
            n = findNode(e.key());
            // Find out how deep the node is in the tree
            depth = findNodeDepth(e.key());
            // Print out the appropriate number of "-" characters
            if (depth > 0)
            {
                prettyOffset(depth);
                if (n.parent.leftChild == n)
                {
                    System.out.print("  ");
                }
                else
                {
                    System.out.print("  ");
                }
            }
            System.out.println(e);
        }
    }

    // Prints out (offset * 5) "-" characters
    public void prettyOffset(int offset)
    {
        String chunk = "    ";
        for (int i = 0; i < offset; i++)
        {
            System.out.print(chunk);
        }
    }
}
