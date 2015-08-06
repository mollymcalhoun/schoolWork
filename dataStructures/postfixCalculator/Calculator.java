/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 3/26/2015
    Assignment: Project 4, infix to postfix conversion and calculation
      using stacks.
    Files: StackNode.java, StackInterface.java, Stack.java,
      Calculator.java, expressions.txt

    This class takes infix expressions from a text file, converts them
      to postfix format and then evaluates them.
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

class Calculator
{
    Scanner file;
    String expression;
    Stack<String> operators;
    Stack<Integer> operands;

    // Takes the name of a file, finds that file, then loads it into
    //   the scanner so it can be read line-by-line.
    Calculator(String filename)
    {
        operators = new Stack<String>();
        operands = new Stack<Integer>();
        File expFile = new File(filename);
        try
        {
           file = new Scanner(expFile); 
        }
        catch (FileNotFoundException ex)
        {
            System.out.println("File not found");
        }
    }

    // If the line is not blank, the expression is converted to a format
    //   where there are spaces between each token, then it is checked
    //   for correctness of format and matched delimeters.
    boolean validateFormat(String expr)
    {
        boolean correctFormat = false;
        boolean delimitersMatched = false;
        if (!expr.equals(""))
        {
            expr = reformatExpression(expr);
            this.expression = expr;
            //System.out.printf("Reformatted expression:\n  %s\n", expr);
            correctFormat = checkCharacters(expr);
            delimitersMatched = false;
            if (correctFormat)
            {
                delimitersMatched = checkDelimiters(expr);
            }
        }
        return correctFormat && delimitersMatched;
    }

    // This method checks if the infix expression is in the proper format,
    //   ensuring that it begins and ends with a number or parentheses,
    //   among other format requirements.
    boolean checkCharacters(String expr)
    {
        String[] exprList = expr.split(" ");
        boolean formatCorrect = true;
        String currentChunk;
        String nextChunk;
        boolean notLast;
        for (int i = 0; i < exprList.length; i++)
        {
            currentChunk = exprList[i];
            // If we're at the beginning of the expression, we have to make sure
            //   that it begins with a number or open parentheses.
            if (i == 0)
            {
                if (!(currentChunk.matches("[0-9]+") ||
                    currentChunk.equals("(")))
                {
                    System.out.println("First character is not int or (");
                    formatCorrect = false;
                }
            }
            // Similarly, if we're at the end we have to ensure it ends with
            //   a number or closing parentheses.
            else if (i == exprList.length - 1)
            {
                if (!(currentChunk.matches("[0-9]+") ||
                    currentChunk.equals(")")))
                {
                    System.out.println("Last character is not int or )");
                    formatCorrect = false;
                }
            }

            notLast = (i != exprList.length - 1);
            // If we're not at the end of the expression we need to
            //   check a few things.
            if (notLast)
            {
                nextChunk = exprList[i+1];
                // If the current piece of the expression is a number,
                //   it must be followed by an operator or closing paren.
                if (currentChunk.matches("[0-9]+"))
                {
                    if (!(nextChunk.matches("[+*-/]") || nextChunk.equals(")")))
                    {
                        System.out.println("Int not followed by operator or )");
                        formatCorrect = false;
                    }
                }
                // If the current chunk is an opening paren, it has to be
                //   followed by a number or another opening paren.
                else if (currentChunk.equals("("))
                {
                    if (!(nextChunk.matches("[0-9]+") || nextChunk.equals("(")))
                    {
                        System.out.println("Open paren not followed by int or (");
                        formatCorrect = false;
                    }
                }
                // If the current chunk is a closing paren, it must be followed
                //   by an operator or another closing paren.
                else if (currentChunk.equals(")"))
                {
                    if (!(nextChunk.matches("[+*-/]") || nextChunk.equals(")")))
                    {
                        System.out.println("Close paren not followed by operator or )");
                        formatCorrect = false;
                    }
                }
                // If the current chunk is an operator, it must be followed by
                //   a number or an opening paren.
                else if (currentChunk.matches("[+*-/]"))
                {
                    if (!(nextChunk.matches("[0-9]+") || nextChunk.equals("(")))
                    {
                        System.out.println("Operator not followed by int or (");
                        formatCorrect = false;
                    }
                }
            }

            // If there is some sort of error, we break from the loop and
            //   return false.
            if (!formatCorrect)
            {
                i = exprList.length;
            }
        }
        return formatCorrect;
    }

    // Takes the expression and reformats it to have a space in between
    //   every chunk, so that it is easier to work with later if it
    //   didn't come pre-formatted.
    String reformatExpression(String expr)
    {
        String[] exprList = expr.split("");
        String currentChunk;
        String nextChunk;
        StringBuilder reformat = new StringBuilder();
        for (int i = 0; i < exprList.length; i++)
        {
            currentChunk = exprList[i];
            // If we encounter a number we need to check if the next the next
            //   thing is also a number. If so, they're both digits of the
            //   same number.
            if (currentChunk.matches("[0-9]+"))
            {
                reformat.append(currentChunk);
                if (i != exprList.length - 1)
                {
                    nextChunk = exprList[i+1];
                    if (nextChunk.matches(" "))
                    {
                        reformat.append(" ");
                        nextChunk = exprList[i+2];
                        i++;
                    }
                    else if (!nextChunk.matches("[0-9]+"))
                    {
                        reformat.append(" ");
                    }
                }
            }
            else if (currentChunk.matches("[\\(\\)]"))
            {
                reformat.append(currentChunk + " ");
            }
            else if (currentChunk.matches("[+*-/]"))
            {
                reformat.append(currentChunk + " ");
            }
        }
        return reformat.toString();
    }

    // Uses a stack to ensure that the parentheses in the expression are matched.
    boolean checkDelimiters(String expr)
    {
        String[] exprList = expr.split(" ");
        String currentChunk;
        boolean delimitersMatched = true;
        for (int i = 0; i < exprList.length; i++)
        {
            currentChunk = exprList[i];
            // If we encounter a ( it goes on the stack.
            if (currentChunk.equals("("))
            {
                //System.out.println("Pushing...");
                operators.push(currentChunk);
            }
            // If we encounter a ) we pop from the stack until we find
            //   a ( or the stack is empty.
            else if (currentChunk.equals(")"))
            {
                if (!operators.empty())
                {
                    //System.out.println("Popping...");
                    currentChunk = operators.pop();
                    while (!operators.empty() && !currentChunk.equals("("))
                    {
                        currentChunk = operators.pop();
                    }
                    if (operators.empty() && !currentChunk.equals("("))
                    {
                        System.out.println("Stack is empty, problem");
                        delimitersMatched = false;
                    }
                }
                // If the stack is empty when we go to match the ), the
                //   delimeters must be mismatched.
                else if (operators.empty())
                {
                    System.out.println(") encountered but stack empty");
                    delimitersMatched = false;
                }
            }

            if (!delimitersMatched)
            {
                i = exprList.length;
            }
        }
        // When the loop finishes, if the stack is not empty then the
        //   parentheses must be mismatched.
        if (!operators.empty())
        {
            System.out.println("Stack is not empty at end, problem");
            delimitersMatched = false;
        }
        operators.clearStack();
        return delimitersMatched;
    }

    // Returns the precedence of the provided operator for use in converting
    //   to postfix format.
    int getPrecedence(String operator)
    {
        int precedence = 0;
        switch (operator)
        {
        case "*":
        case "/":
            precedence = 3;
            break;
        case "+":
        case "-":
            precedence = 2;
            break;
        case "(":
            precedence = 1;
            break;
        }
        return precedence;
    }

    /*
        This postfix conversion is heavily based on the Python implementation
          found at this page:
          http://interactivepython.org/runestone/static/pythonds/BasicDS/InfixPrefixandPostfixExpressions.html

        This method takes the infix expression and converts it to postfix.
    */
    String convertToPostfix(String expr)
    {
        String[] exprList = expr.split(" ");
        StringBuilder postfix = new StringBuilder();
        String currentChunk;
        String topOperator;
        int precedence;

        for (int i = 0; i < exprList.length; i++)
        {
            currentChunk = exprList[i];
            // If the current chunk is a number, we add it to the
            //   string builder.
            if (currentChunk.matches("[0-9]+"))
            {
                postfix.append(currentChunk + " ");
            }
            // If we encounter a (, we push it onto the operators stack.
            else if (currentChunk.equals("("))
            {
                operators.push(currentChunk);
            }
            // If we encounter a ), we pop from the stack and append
            //   the popped value to the string builder until we
            //   pop a (.
            else if (currentChunk.equals(")"))
            {
                topOperator = operators.pop();
                while (!topOperator.equals("("))
                {
                    postfix.append(topOperator + " ");
                    topOperator = operators.pop();
                }
            }
            // If we encounter an operator, we pop all operators with
            //   greater or equal precedence and append them to the string
            //   builder, then push the operator assigned to currentChunk.
            else if (currentChunk.matches("[+*-/]"))
            {
                while (!operators.empty() &&
                      (getPrecedence(operators.peek()) >=
                       getPrecedence(currentChunk)))
                {
                    topOperator = operators.pop();
                    postfix.append(topOperator + " ");
                }
                operators.push(currentChunk);
            }
            // Theoretically the previous cases should catch everything
            //   if the expression passed the format check earlier,
            //   but this is here just in case.
            else
            {
                System.out.println("Something is wrong with conversion.");
            }
        }

        // After reaching the end of the expression, we pop and append
        //   all operators remaining in the stack.
        while(!operators.empty())
        {
            topOperator = operators.pop();
            postfix.append(topOperator + " ");
        }
        // The postfix string builder is returned as a string.
        return postfix.toString();
    }

    // This method takes the postfix expression in string form and
    //   evaluates it, then returns the result.
    int calculate(String expr)
    {
        String[] exprList = expr.split(" ");
        String currentChunk;
        int operand1;
        int operand2;
        int result;
        for (int i = 0; i < exprList.length; i++)
        {
            currentChunk = exprList[i];
            // If we encounter a number, we push it onto the operands
            //   stack.
            if (currentChunk.matches("[0-9]+"))
            {
                operands.push(Integer.parseInt(currentChunk));
            }
            /*
                If we encounter an operator, we pop two operands, then
                  the operator and operands to the doOperation method,
                  which will perform the appropriate operation, return
                  the result and then push it onto the operands stack.
            */
            else if (currentChunk.matches("[+*-/]"))
            {
                operand2 = operands.pop();
                operand1 = operands.pop();
                result = doOperation(operand1, operand2, currentChunk);
                operands.push(result);
            }
        }
        // The only thing left in the stack at the end will be the
        //   result of the full expression.
        return operands.pop();
    }

    // The switch statement selects the correct operation to perform on
    //   the provided operands.
    int doOperation(int o1, int o2, String operator)
    {
        int result = -999;
        switch (operator)
        {
        case "+":
            result = o1 + o2;
            break;
        case "-":
            result = o1 - o2;
            break;
        case "*":
            result = o1 * o2;
            break;
        case "/":
            result = o1 / o2;
        }
        return result;
    }

    /*
        I got this method from this Stack Overflow thread:
        http://stackoverflow.com/a/25095049

        This prevents the program from spitting out too much output at once.
    */
    void pressToContinue()
    { 
        System.out.print("\nPress enter to continue...");
        try
        {
            System.in.read();
            System.in.skip(1);
        }  
        catch(IOException e)
        {
            System.out.println("Something is wrong.");
        }
        System.out.println();
    }

    public static void main(String[] args)
    {
        String filename = args[0]; // Filename is a command line argument.
        Calculator calc = new Calculator(filename);
        int result;
        boolean formatCorrect;
        // The program runs until the end of the file.
        while (calc.file.hasNextLine())
        {
            calc.expression = calc.file.nextLine();
            System.out.printf("Original expression:\n  %s\n", calc.expression);
            formatCorrect = calc.validateFormat(calc.expression);
            // We only evaluate the expression if the format is correct.
            if (formatCorrect)
            {
                calc.expression = calc.convertToPostfix(calc.expression);
                System.out.printf("Expression converted to postfix:\n  %s\n",
                                  calc.expression);
                result = calc.calculate(calc.expression);
                System.out.printf("Calculation result:\n  %d\n", result);
            }
            else
            {
                System.out.println("Expression format error, skipping...");
            }
            // Making sure the stacks are entirely empty.
            calc.operators.clearStack();
            calc.operands.clearStack();
            calc.pressToContinue();
        }
        System.exit(0);
    }
}