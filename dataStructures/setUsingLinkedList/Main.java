/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 3/3/2015
    Assignment: 3, Set implementation with linked list
    Files: Node.java, LinkedList.java, Set.java, Main.java

    This class handles user input, creating sets, and calling
      methods to perform various set operations.
*/

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main
{
    static Scanner scan = new Scanner(System.in);
    static LinkedList<Set> setList = new LinkedList<Set>();
    static LinkedList<String> setNames = new LinkedList<String>();
    static String[] options = {"Create a set", "Print the list of created sets",
                        "Print a set", "Print sorted", "Membership check",
                        "Subset check", "Sorted check", "Union", "Intersection",
                        "Subtraction", "Exit"};

    // Goes through the options list and generates the menu from it,
    //   printing it to the user.
    public static void printMenu()
    {
        System.out.println("\nMAIN MENU\n=========");
        int menuLength = options.length;
        for (int i = 1; i <= menuLength; i++)
        {
            String option = options[i - 1];
            System.out.printf(" %d) %s\n", i, option);
        }
        System.out.print("\nPlease enter the number of your choice\n> ");
    }

    // Request the name of a new set from the user.
    private static String getNewName()
    {
        boolean gettingName = true;
        String setName = null;
        while (gettingName)
        {
            System.out.printf("\nEnter the name of a new set.\n> ");
            setName = scan.next().toLowerCase();
            scan.nextLine();
            if (setNames.isInList(setName) && !setName.equals("(return)"))
            {
                System.out.println("Set by that name already exists. Try again or type '(return) to return.");
            }
            else
            {
                gettingName = false;
            }
        }
        return setName;
    }

    // Request the name of an existing set from the user.
    private static String getSetName()
    {
        boolean gettingName = true;
        String setName = null;
        while (gettingName)
        {
            System.out.printf("\nEnter the name of an existing set.\n> ");
            setName = scan.next().toLowerCase();
            scan.nextLine();
            if (!setNames.isInList(setName) && !setName.equals("(return)"))
            {
                System.out.println("\nSet does not exist. Try again or type '(return)'' to return.");
            }
            else
            {
                gettingName = false;
            }
        }
        return setName;
    }

    // Request a integer value from the user.
    private static int getValue()
    {
        boolean gettingValue = true;
        int value = -1;
        while (gettingValue)
        {
            System.out.printf("\nEnter a value.\n> ");
            try
            {
                value = scan.nextInt();
            }
            catch (NumberFormatException e)
            {
                System.out.print("\nYou must enter your choice as a number.\n");
            }
            catch (InputMismatchException e)
            {
                System.out.print("\nYou must enter your choice as a number.\n");
            }
            scan.nextLine();
            gettingValue = false;
        }
        return value;
    }

    // Prompts the user to enter integer values separated by spaces,
    //   then splits the resulting string and returns the new string array.
    private static String[] getData()
    {
        String rawString;
        String[] rawStringArray = {};
        boolean getting = true;
        boolean syntaxError = false;

        while (getting)
        {
            if (syntaxError)
            {
                System.out.println("\nYou didn't end your input with an X.\n" +
                                   "  Please try again.");
            }
            System.out.print("\nEnter the values to be stored in your set, " +
                             "separated by spaces.\n" +
                             "When you are finished, enter an 'X' and press " +
                             "the Return key.\n> ");
            rawString = scan.nextLine();
            rawStringArray = rawString.split(" ");
            int lastIndex = rawStringArray.length - 1;
            String finalChar = rawStringArray[lastIndex];
            if (finalChar.equals("X") || finalChar.equals("x"))
            {
                syntaxError = false;
                getting = false;
            }
            else
            {
                syntaxError = true;
            }
        }
        return rawStringArray;
    }

    // Takes the string array from the getData() method and parses it
    //   into an integer array. Any non-digit members of 'data' will be parsed
    //   as zeroes.
    private static int[] parseData(String[] data)
    {
        int dataLength = data.length - 1;
        int[] intList = new int[dataLength];
        int intToAdd;
        for (int i = 0; i < dataLength; i++)
        {
            if (isNumber(data[i]))
            {
                intToAdd = Integer.parseInt(data[i]);
                intList[i] = intToAdd;
            }
        }
        return intList;
    }

    // Checks if the provided string is a number or not.
    private static boolean isNumber(String s)
    {
        return s.matches("[0-9]+");
    }

    // Prompts the user to make a choice from the menu options, then
    //   returns the choice as an integer.
    private static int getChoice()
    {
        boolean gettingChoice = true;
        int choice = 0;
        while (gettingChoice)
        {
            try
            {
                choice = scan.nextInt();
            }
            catch (NumberFormatException e)
            {
                System.out.print("\nYou must enter your choice as a number.");
            }
            catch (InputMismatchException e)
            {
                System.out.print("\nYou must enter your choice as a number.");
            }
            scan.nextLine();

            if (choice > 11 || choice < 1)
            {
                System.out.print("\nPlease choose from the available " +
                                 "options.\n> ");
            }
            else
            {
                gettingChoice = false;
            }
        }
        return choice;
    }

    // Takes an integer choice and executes methods based on that using
    //   a switch statement.
    public static boolean parseChoice(int choice)
    {
        boolean exit = false;
        String name;
        String anotherName;
        int value;
        Set s;
        Set s2;
        switch (choice)
        {
            case  1:
                name = getNewName();
                String[] data = getData();
                int[] values = parseData(data);
                createSet(name, values);
                break;

            case  2:
                printAllSets();
                break;

            case  3:
                name = getSetName();
                if (!name.equals("(return)"))
                {
                    s = retrieveSet(name);
                    printASet(s);
                }
                break;

            case  4:
                name = getSetName();
                if (!name.equals("(return)"))
                {
                    printSorted(retrieveSet(name));
                }
                break;

            case  5:
                name = getSetName();
                value = getValue();
                if (!name.equals("(return)"))
                {
                    s = retrieveSet(name);
                    membershipCheck(s, value);
                }
                break;

            case  6:
                name = getSetName();
                if (!name.equals("(return)"))
                {
                    anotherName = getSetName();
                    if (!anotherName.equals("(return)"))
                    {
                        s = retrieveSet(name);
                        s2 = retrieveSet(anotherName);
                        subsetCheck(s, s2);
                    }
                }
                break;

            case  7:
                name = getSetName();
                if (!name.equals("(return)"))
                {
                    s = retrieveSet(name);
                    sortedCheck(s);
                }
                break;

            case  8:
                name = getSetName();
                if (!name.equals("(return)"))
                {
                    anotherName = getSetName();
                    if (!anotherName.equals("(return)"))
                    {
                        s = retrieveSet(name);
                        s2 = retrieveSet(anotherName);
                        performUnion(s, s2);
                    }
                }
                break;

            case  9:
                name = getSetName();
                if (!name.equals("(return)"))
                {
                    anotherName = getSetName();
                    if (!anotherName.equals("(return)"))
                    {
                        s = retrieveSet(name);
                        s2 = retrieveSet(anotherName);
                        performIntersection(s, s2);
                    }
                }
                break;

            case 10:
                name = getSetName();
                if (!name.equals("(return)"))
                {
                    anotherName = getSetName();
                    if (!anotherName.equals("(return)"))
                    {
                        s = retrieveSet(name);
                        s2 = retrieveSet(anotherName);
                        performSubtraction(s, s2);
                    }
                }
                break;

            case 11:
                exit = true;
                break;
        }
        // Prompt user to continue so that the menu doesn't immediately
        //   push output off the screen.
        if (choice != 11)
        {
            System.out.print("\nPress enter to continue.");
            name = scan.nextLine();
        }
        return !exit;
    }
    
    // Takes a string and array of integers and passes them to the Set
    //   constructor to create a new set, then adds it to the set list.
    public static void createSet(String name, int[] values)
    {
        Set newSet = new Set(name, values);
        setList.addToTail(newSet);
        setNames.addToTail(name);
    }

    // Searches the list of sets for one with a name matching the provided
    //   name. Returns null if not found.
    public static Set retrieveSet(String name)
    {
        Set setToGet = null;
        if (!setNames.isInList(name))
        {
            System.out.println("Set does not exist.");
        }
        else
        {
            Node currentSetNode = setList.head;
            Set currentSet = (Set)currentSetNode.value;
            String currentSetName;
            boolean findingSet = true;
            while (findingSet)
            {
                currentSetName = currentSet.name;
                if (currentSetName.equals(name))
                {
                    setToGet = (Set)currentSetNode.value;
                    findingSet = false;
                }
                else
                {
                    currentSetNode = currentSetNode.next;
                    currentSet = (Set)currentSetNode.value;
                }
            }
        }
        return setToGet;
    }

    // Calls the provided set's printSet() method.
    public static void printASet(Set setToPrint)
    {
        if (setToPrint != null)
        {
            setToPrint.printSet();
        }
    }

    // Runs through the list of all created sets and prints them.
    public static void printAllSets()
    {
        Node currentNode = setList.head;
        if (currentNode == null)
        {
            System.out.println("\nThere are no sets.");
        }
        else
        {
            Set currentSet;
            while (currentNode != null)
            {
                currentSet = (Set)currentNode.value;
                printASet(currentSet);
                currentNode = currentNode.next;
            }
        }
    }

    // Sorts the specified set in ascending order and then prints it.
    public static void printSorted(Set setToSort)
    {
        setToSort.values.sortAscending();
        printASet(setToSort);
    }

    // Takes two sets. The first set performs a union operation on the other.
    // The result is then printed.
    public static void performUnion(Set s1, Set s2)
    {
        printASet(s1);
        printASet(s2);
        Set union = s1.union(s2);
        printASet(union);
    }

    // Takes two sets. The first set performs an intersection operation on the other.
    // The result is then printed.
    public static void performIntersection(Set s1, Set s2)
    {
        printASet(s1);
        printASet(s2);
        Set intersection = s1.intersection(s2);
        printASet(intersection);
    }

    // Takes two sets. The first set performs a subtraction operation on the other.
    // The result is then printed.
    public static void performSubtraction(Set s1, Set s2)
    {
        printASet(s1);
        printASet(s2);
        Set subtraction = s1.subtraction(s2);
        printASet(subtraction);
    }

    // Checks if value is a member of the set and prints the result.
    public static boolean membershipCheck(Set s, int value)
    {
        boolean isMember = s.checkMembership(value);
        System.out.printf("\n%d is a member of set %s?\n -> %b\n", value, s.name, isMember);
        return isMember;
    }

    // Checks if set s2 is a subset of s1, then prints the result.
    public static boolean subsetCheck(Set s1, Set s2)
    {
        printASet(s1);
        printASet(s2);
        boolean isSubset = s1.checkSubset(s2);
        System.out.printf("\nSet %s is a subset of set %s?\n -> %b\n",
                          s2.name, s1.name, isSubset);
        return isSubset;
    }

    // Uses the Set checkSorted() method and prints the result.
    public static boolean sortedCheck(Set s)
    {
        boolean isSorted = s.checkSorted();
        System.out.printf("Set %s is sorted?\n -> %b\n", s.name, isSorted);
        return isSorted;
    }

    public static void main(String[] args)
    {
        boolean running = true;
        int choice;
        // Main loop. If user selects exit from the menu, 'running' changes
        //   to false and the program exits.
        while(running)
        {
            printMenu();
            choice = getChoice();
            running = parseChoice(choice);
        }
        System.exit(0);
    }
}