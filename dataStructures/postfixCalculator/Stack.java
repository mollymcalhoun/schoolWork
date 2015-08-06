/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 3/26/2015
    Assignment: Project 4, infix to postfix conversion and calculation
      using stacks.
    Files: StackNode.java, StackInterface.java, Stack.java,
      Calculator.java, expressions.txt

    This class represents a stack and has a field for the reference to
      the node at the top of the stack.
*/

import java.util.EmptyStackException;

class Stack <E> implements StackInterface <E>
{
    StackNode<E> top;

    // If a stack is created with no arguments it will be empty,
    //   with the top node being null.
    public Stack()
    {
        this(null);
    }

    // Create a new stack with the specified node as the top
    public Stack(StackNode<E> n)
    {
        this.top = n;
    }

    // The only time the stack is empty is when the top is null, so
    //   this just returns the result of the comparison top == null
    public boolean empty()
    {
        return top == null;
    }

    // Get the value of the top node of the stack (if it exists) and
    //   return it without deleting the node.
    public E peek() throws EmptyStackException
    {
        E value = null;
        try
        {
            if (!empty())
            {
                value = top.value;
            }
        }
        catch (NullPointerException ex)
        {
            System.out.println("Stack is empty");
            throw new EmptyStackException();
        }
        return value;
    }

    // Add a new value to the top of the stack.
    public void push(E value)
    {
        StackNode<E> pushNode = new StackNode<E>(value, top);
        top = pushNode;
    }

    // Delete the top of the stack and return its value.
    public E pop() throws EmptyStackException
    {
        E value = null;
        try
        {
            if (!empty())
            {
                value = top.value;
                top = top.next;
            }
        }
        catch (NullPointerException ex)
        {
            System.out.println("Stack is empty");
            throw new EmptyStackException();
        }
        return value;
    }

    // Empties the stack by popping until nothing is left.
    public void clearStack()
    {
        while (!empty())
        {
            pop();
        }
    }
}