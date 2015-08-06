/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 3/3/2015
    Assignment: 3, Set implementation with linked list
    Files: Node.java, LinkedList.java, Set.java, Main.java

    This class represents a set, with the set values being stored
      in a linked list.
*/

public class Set implements Comparable<Object>
{
    String name;
    LinkedList<Integer> values;

    // Creates a set with an empty linked list.
    public Set(String name)
    {
        this.name = name;
        values = new LinkedList<Integer>();
    }

    // Takes an integer list of values and then passes them to
    //   parseToLinkedList to create the list
    public Set(String name, int[] rawValues)
    {
        this.name = name;
        parseToLinkedList(rawValues);
    }

    // Accepts an integer list and creates a linked list from it.
    public void parseToLinkedList(int[] rawValues)
    {
        values = new LinkedList<Integer>();
        int listLength = rawValues.length;
        for (int i = 0; i < listLength; i++)
        {
            if (!values.isInList(rawValues[i]))
            {
                values.addToTail(rawValues[i]);
            }
        }
    }

    // Prints the name of the set and then calls the linked list
    //   printAll() method.
    public void printSet() {
        System.out.printf("\nSet %s:\n  ", name);
        values.printAll();
    }

    // Simply calls the linked list isInList() method.
    public boolean checkMembership(int value)
    {
        return values.isInList(value);
    }

    // Checks if all the values of otherSet are present in this set
    //   and returns true if so.
    public boolean checkSubset(Set otherSet)
    {
        boolean isSubset = true;
        Node currentNode = otherSet.values.head;
        if (currentNode == null)
        {
            isSubset = true;
        }
        else
        {
            boolean checkingSubset = true;
            boolean isMember;
            int value;
            while (checkingSubset)
            {
                value = (int)currentNode.value;
                isMember = checkMembership(value);
                if (!isMember)
                {
                    isSubset = false;
                    checkingSubset = false;
                }
                else if (currentNode == otherSet.values.tail)
                {
                    checkingSubset = false;
                }
                currentNode = currentNode.next;
            }
        }
        return isSubset;
    }

    /*
        Not a very efficient implementation. This method clones the
          current set's linked list, then calls both sortAscending() and
          sortDescending() on the cloned list and compares the values one-by-one
          to determine if the set is sorted.
    */
    public boolean checkSorted()
    {
        boolean isSorted = false;
        if (values.isEmpty())
        {
            //System.out.println("\nSet is empty.");
            isSorted = true;
        }
        else
        {
            LinkedList<Integer> dummyList = new LinkedList<Integer>();
            Node currentNode = values.head;
            int value;
            // Clone the list we're checking
            while (currentNode != null)
            {
                value = (int)currentNode.value;
                dummyList.addToTail(value);
                currentNode = currentNode.next;
            }

            currentNode = values.head;
            dummyList.sortAscending();
            Node currentDummyNode = dummyList.head;
            boolean isSortedAscending = true;
            // Compare the sorted dummy list node by node to check if sorted
            while (currentNode != null) 
            {
                if (!currentNode.value.equals(currentDummyNode.value))
                {
                    isSortedAscending = false;
                    currentNode = null;
                }
                else
                {
                    currentNode = currentNode.next;
                    currentDummyNode = currentDummyNode.next;
                }
            }
            if (isSortedAscending) {
                System.out.println("\nList is sorted in ascending order.");
            }

            currentNode = values.head;
            dummyList.sortDescending();
            currentDummyNode = dummyList.head;
            boolean isSortedDescending = true;
            // Doing it again for descending
            while (currentNode != null) 
            {
                if (!currentNode.value.equals(currentDummyNode.value))
                {
                    isSortedDescending = false;
                    currentNode = null;
                }
                else
                {
                    currentNode = currentNode.next;
                    currentDummyNode = currentDummyNode.next;
                }
            }
            if (isSortedDescending) {
                System.out.println("\nList is sorted in descending order.");
            }
            isSorted = isSortedDescending || isSortedAscending;
        }
        return isSorted;
    }

    // Takes another set and returns a new set containing all unique
    //   values present in either set.
    public Set union(Set otherSet)
    {
        Set unionSet = new Set("union");
        Node currentNode = values.head;
        int value;
        // Loop through this set
        while (currentNode != null)
        {
            value = (int)currentNode.value;
            unionSet.values.addToTail(value);
            currentNode = currentNode.next;
        }

        currentNode = otherSet.values.head;
        // Loop through otherSet.
        while (currentNode != null)
        {
            value = (int)currentNode.value;
            if (!unionSet.checkMembership(value))
            {
                unionSet.values.addToTail(value);
            }
            currentNode = currentNode.next;
        }
        return unionSet;
    }

    // Takes another set and returns a new set containing all values
    //   that this set and otherSet have in common.
    public Set intersection(Set otherSet)
    {
        Set intersectionSet = new Set("intersection");
        Node currentNode = values.head;
        int value;
        while (currentNode != null)
        {
            value = (int)currentNode.value;
            if (otherSet.checkMembership(value))
            {
                intersectionSet.values.addToTail(value);
            }
            currentNode = currentNode.next;
        }
        return intersectionSet;
    }

    // Takes another set and returns a new set containing all values
    //   contained in this set but not otherSet.
    public Set subtraction(Set otherSet)
    {
        Set subtractionSet = new Set("subtraction");
        Node currentNode = values.head;
        int value;
        while (currentNode != null)
        {
            value = (int)currentNode.value;
            if (!otherSet.checkMembership(value))
            {
                subtractionSet.values.addToTail(value);
            }
            currentNode = currentNode.next;
        }
        return subtractionSet;
    }

    /*
        Basically a dummy method to appease the compiler.
        It was impossible to create a linked list of sets unless
          the Set class implemented the comparable interface.
    */
    @Override
    public int compareTo(Object o)
    {
        return 0;
    }
}