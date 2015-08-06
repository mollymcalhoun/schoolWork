/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 3/26/2015
    Assignment: Project 4, infix to postfix conversion and calculation
      using stacks.
    Files: StackNode.java, StackInterface.java, Stack.java,
      Calculator.java, expressions.txt

    This class represents a node of a stack. It has fields for a value
      and a reference to the node below it.
*/

class StackNode <E>
{
    E value;
    StackNode<E> next;

    // If the node is created with no arguments to the constructor, it
    //   has no value and has nothing below it.
    StackNode()
    {
        this(null, null);
    }

    // Constructor for a node with no reference to the next node,
    //   Mostly used to create the top of the stack.
    StackNode(E value)
    {
        this(value, null);
    }

    // Constructor for a node that takes a value and a reference to
    //   the node below it in the stack.
    StackNode(E value, StackNode<E> next)
    {
        this.value = value;
        this.next = next;
    }

    // Prints the value of the node.
    @Override
    public String toString()
    {
        return value.toString();
    }
}