/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 3/3/2015
    Assignment: 3, Set implementation with linked list
    Files: Node.java, LinkedList.java, Set.java, Main.java

    This class represents a doubly-linked linked list and has a number of
      methods for adding, removing, finding and sorting.
*/

public class LinkedList<E extends Comparable<? super E>>
{
    protected Node<E> head, tail;

    public LinkedList()
    {
        head = null;
        tail = null;
    }

    // List can only be empty if the head (or tail) is null,
    //   therefore this method returns the result of head == null.
    public boolean isEmpty(){
        return head == null;
    }

    // Takes a value and creates a new node from it, then assigns
    //   it to both head and tail.
    public void addToEmpty(E data)
    {
        Node<E> node = new Node<E>(data);
        head = node;
        tail = node;
    }

    // Takes a value, creates a new node for it and then makes it the head.
    public void addToHead(E data)
    {
        Node<E> node;
        if (isEmpty())
        {
            addToEmpty(data);
        }
        else
        {
            node = new Node<E>(data, null, head);
            head.previous = node;
            head = node;
        }
    }

    // Takes a value, creates a new node for it and then makes it the tail.
    public void addToTail(E data)
    {
        if (isEmpty())
        {
            addToEmpty(data);
        }
        else
        {
            Node<E> node = new Node<E>(data, tail, null);
            tail.next = node;
            tail = node;
        }
    }

    // Takes a value, creates a new node for it, then finds the node for the
    //   value 'x' and places the new node after it.
    public void addAfter(E newValue, E x)
    {
        Node<E> current;
        Node<E> newNode;
        if (isEmpty())
        {
            System.out.println("List is empty.\n");
        }
        else
        {
            boolean findingNode = true;
            current = head;
            // This loop iterates through the list to find the node
            //   with the value matching the parameter 'x.'
            while (findingNode)
            {
                if (current.value.equals(x))
                {
                    if (current == tail)
                    {
                        addToTail(newValue);
                    }
                    else
                    {
                        newNode = new Node<E>(newValue, current, current.next);
                        current.next.previous = newNode;
                        current.next = newNode;
                    }
                    findingNode = false;
                }
                // If we arrive at the last node and haven't found
                //   a node with value 'x,' it must not be in the list.
                else if (!current.value.equals(x) && current == tail)
                {
                    System.out.println("Node not found.\n");
                    findingNode = false;
                }
                current = current.next;
            }
        }
    }

    // Removes the head of the list and returns its value.
    public E deleteFromHead()
    {
        if (isEmpty())
        {
            System.out.println("List is empty.\n");
            return null;
        }
        else
        {
            E value = head.value;
            head = head.next;
            head.previous = null;
            return value;
        }
    }

    // Removes the tail of the list and returns its value.
    public E deleteFromTail()
    {
        if (isEmpty())
        {
            System.out.println("List is empty.\n");
            return null;
        }
        else
        {
            E value = tail.value;
            tail = tail.previous;
            tail.next = null;
            return value;
        }
    }

    // Finds the node with the value matching 'item,' then deletes it
    //   and returns its value.
    public E delete(E item)
    {
        E value = null;
        if (isEmpty())
        {
            System.out.println("List is empty.\n");
        }
        else
        {
            boolean findingNode = true;
            Node<E> currentNode = head;
            // This loop iterates through the list to find the node
            //   with the value matching the parameter 'item.'
            while (findingNode)
            {
                if (currentNode.value.equals(item))
                {
                    value = currentNode.value;
                    findingNode = false;
                    if (currentNode == head)
                    {
                        deleteFromHead();
                    }
                    else if (currentNode == tail)
                    {
                        deleteFromTail();
                    }
                    else
                    {
                        currentNode.previous.next = currentNode.next;
                        currentNode.next.previous = currentNode.previous;
                    }
                }
                // If we arrive at the last node and haven't found
                //   a node with value 'x,' it must not be in the list.
                else if (currentNode == tail && !currentNode.value.equals(item))
                {
                    findingNode = false;
                    System.out.printf("Value %d not found.\n", item);
                }
                currentNode = currentNode.next;
            }
        }
        return value;
    }

    // Checks if the specified item is in the list and returns true
    //   if it is present.
    public boolean isInList(E item)
    {
        boolean isInList = false;
        if (isEmpty())
        {
            //System.out.println("List is empty.\n");
        }
        else
        {
            boolean findingNode = true;
            Node<E> currentNode = head;
            // This loop iterates through the list to find the node
            //   with the value matching the parameter 'item.'
            // I just realized having essentially the same loop in here
            //   several times is very sloppy and this could be refactored.
            while (findingNode)
            {
                if (currentNode.value.equals(item))
                {
                    findingNode = false;
                    isInList = true;
                }
                // If we arrive at the last node and haven't found
                //   a node with value 'x,' it must not be in the list.
                else if (!currentNode.value.equals(item) && currentNode == tail)
                {
                    findingNode = false;
                }
                currentNode = currentNode.next;
            }
        }
        return isInList;
    }

    // Calls min(), passing it the tail node reference.
    public Node<E> min()
    {
        return min(tail);
    }

    // Iterates through the list until it reaches lastNode, then returns
    //   the node with the minimum value.
    public Node<E> min(Node<E> lastNode)
    {
        if (isEmpty())
        {
            System.out.println("\nList is empty, no minimum.");
            return null;
        } 
        boolean iterating = true;
        int comparison;
        Node<E> min = head;
        Node<E> current = head;
        while (iterating)
        {
            comparison = current.value.compareTo(min.value);
            if (comparison < 0)
            {
                min = current;
            }
            if (current == lastNode)
            {
                iterating = false;
            }
            current = current.next;
        }
        return min;
    }

    // Calls max(), passing it the tail node reference.
    public Node<E> max()
    {
        return max(tail);
    }

    // Iterates through the list until it reaches lastNode, then returns
    //   the node with the maximum value.
    public Node<E> max(Node<E> lastNode)
    {
        if (isEmpty())
        {
            System.out.println("\nList is empty, no maximum.");
            return null;
        } 
        boolean iterating = true;
        int comparison;
        Node<E> max = head;
        Node<E> current = head;
        while (iterating)
        {
            comparison = current.value.compareTo(max.value);
            if (comparison > 0)
            {
                max = current;
            }
            if (current == lastNode)
            {
                iterating = false;
            }
            current = current.next;
        }
        return max;
    }

    // Uses the min() function to brute force sort the linked list
    //   in ascending order by moving the minimum to the tail repeatedly.
    public void sortAscending()
    {
        if (isEmpty())
        {
            System.out.println("List is empty, nothing to sort.\n");
        }
        else if (head == tail)
        {
            return;
        }
        else
        {
            Node<E> min = head;
            Node<E> last = tail;
            // This loop finds the minimum value node, adds it to the
            //   end of the list, then deletes the previous instance of it.
            while (last != null)
            {
                min = min(last);
                // If the minimum node is the last node, the last node
                //   is shifted toward the head by one.
                if (last == min)
                {
                    last = last.previous;
                }
                addToTail(min.value);
                delete(min.value);
            }
        }
    }

    // Uses the max() function to brute force sort the linked list
    //   in descending order by moving the maximum to the tail repeatedly.
    public void sortDescending() 
    {
        if (isEmpty())
        {
            System.out.println("List is empty, nothing to sort.\n");
        }
        else if (head == tail)
        {
            return;
        }
        else
        {
            Node<E> max = head;
            Node<E> last = tail;
            // This loop finds the maximum value node, adds it to the
            //   end of the list, then deletes the previous instance of it.
            while (last != null)
            {
                max = max(last);
                // If the maximum node is the last node, the last node
                //   is shifted toward the head by one.
                if (last == max)
                {
                    last = last.previous;
                }
                addToTail(max.value);
                delete(max.value);
            }
        }
    }

    // Prints a an open bracket, then calls printAll with the head node.
    public void printAll()
    {
        System.out.print("[ ");
        printAll(head);
    }

    // Prints the current node, then calls printAll() again with
    //   that node's next reference until the tail is reached.
    public void printAll(Node<E> currentNode)
    {
        if (isEmpty())
        {
            System.out.print("Empty");
            System.out.printf(" ]\n");
        }
        else if (currentNode == tail)
        {
            System.out.print(tail);
            System.out.printf("]\n");
        }
        else
        {
            System.out.print(currentNode);
            printAll(currentNode.next);
        }
    }
}