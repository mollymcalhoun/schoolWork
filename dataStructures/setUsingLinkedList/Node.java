/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 3/3/2015
    Assignment: 3, Set implementation with linked list
    Files: Node.java, LinkedList.java, Set.java, Main.java

    This class represents a doubly-linked linked list node.
    Each node has two references that can point to another node or to null if
      the node is at the end of the linked list.
*/

public class Node<E>
{
    public E value; // Generic value that the node holds.
    public Node<E> previous;
    public Node<E> next;

    // Constructor for when there are no arguments.
    public Node()
    {
        this(null, null, null);
    }

    // Creates node with no references to other nodes. Used for first list element.
    public Node(E data)
    {
        this(data, null, null);
    }
    
    // Create node with value and references to previous and next nodes in the list.
    public Node(E data, Node<E> previous, Node<E> next)
    {
        value = data;
        this.previous = previous;
        this.next = next;
    }

    // Prints the value of the node plus one whitespace character.
    // Used when printing a linked list.
    @Override
    public String toString()
    {
        return value + " ";
    }
}