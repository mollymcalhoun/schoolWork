/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 3/26/2015
    Assignment: Project 4, infix to postfix conversion and calculation
      using stacks.
    Files: StackNode.java, StackInterface.java, Stack.java,
      Calculator.java, expressions.txt

    This class is the interface for the implementation of the
      stack used for this project;
*/

interface StackInterface <E>
{
    boolean empty();
    E peek() throws java.util.EmptyStackException;
    void push(E x);
    E pop() throws java.util.EmptyStackException;
    void clearStack();
}