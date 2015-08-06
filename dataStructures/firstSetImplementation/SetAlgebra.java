/**
 * Name: Molly Calhoun
 * Class: CSc3410
 * Assignment: Project 1
 */

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * The SetAlgebra class handles user input and then creates sets
 * and performs operations based on said input.
 */
public class SetAlgebra {
    // userNames holds the names of all created sets, preventing two
    //   sets with the same name.
    static ArrayList<String> usedNames;
    static ArrayList<Set> allSets;
    static Scanner scan = new Scanner(System.in);

    private static void printMenu() {
        System.out.println("\n=====MAIN MENU=====\n" +
                           "\n1) Create a set\n" +
                           "2) Print the list of created sets\n" +
                           "3) Print a set\n" +
                           "4) Union\n" +
                           "5) Intersection\n" +
                           "6) Subtraction\n" +
                           "7) Exit\n" +
                           "\nPlease enter the number of your command.");
        System.out.print("> ");
    }

    /**
     * Executes functions based on user input.
     */
    private static boolean parseChoice(int option) {
        boolean exit = true;
        switch (option) {
            case 1: getSetFromUser();
                    break;
            case 2: printCreatedSets();
                    break;
            case 3: printASet();
                    break;
            case 4: performUnion();
                    break;
            case 5: performIntersection();
                    break;
            case 6: performSubtraction();
                    break;
            case 7: exit = false;
                    break;
        }
        return exit;
    }

    /**
     * Obtains menu choice from user.
     */
    private static boolean getChoiceFromUser() {
        boolean gettingChoice = true;
        int choice = 0;
        while (gettingChoice) {
            try {
                choice = scan.nextInt();
            } catch (NumberFormatException e) {
                System.out.print("\nYou must enter your choice as a number.");
            } catch (InputMismatchException e) {
                System.out.print("\nYou must enter your choice as a number.");
            }
            scan.nextLine();
            if (choice > 7 || choice < 1) {
                System.out.print("\nPlease choose from the available " +
                                 "options.\n> ");
            } else {
                gettingChoice = false;
            }
        }
        boolean exit = parseChoice(choice);
        return exit;
    }

    /**
     * Calls functions for getting a new set's name and values, then creates
     * a new set and adds it to the ArrayList of sets.
     */
    private static void getSetFromUser() {
        String setName = getNewSetName();
        String[] rawString = getData();
        ArrayList<Integer> rawData = parseToArrayList(rawString);
        Set newSet = new Set(setName, rawData);
        usedNames.add(setName);
        allSets.add(newSet);
    }

    /**
     * Obtains the name of a new set from the user.
     */
    private static String getNewSetName() {
        String setName = "";
        boolean gettingName = true;
        while (gettingName) {
            System.out.print("\nPlease enter the name of your set.\n> ");
            setName = scan.next().toLowerCase();
            scan.nextLine();
            if (usedNames.contains(setName)) {
                System.out.println("\nThat name is already in use.");
            } else {
                gettingName = false;
            }
        }
        return setName;
    }

    /**
     * Obtains the set values from the user as a sequence of numbers ending
     * with an X, then splits the input and returns the string array.
     */
    public static String[] getData() {
        String rawString;
        String[] rawStringArray = {};
        ArrayList<Integer> rawData = new ArrayList<Integer>();
        boolean getting = true;
        boolean syntaxError = false;

        while (getting) {
            if (syntaxError) {
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
            if (finalChar.equals("X") || finalChar.equals("x")) {
                syntaxError = false;
                getting = false;
            } else {
                syntaxError = true;
            }
        }
        return rawStringArray;
    }

    /**
     * Prints the contents of the usedNames ArrayList, representing
     * all existing sets.
     */
    private static void printCreatedSets() {
        System.out.println("\nCurrent list of sets:");
        if (usedNames.isEmpty()) {
            System.out.println("\nThere are currently no sets.");
        } else {
            for (String s : usedNames) {
                System.out.println(s);
            }
        }
    }

    /**
     * Obtains the name of an existing set from the user.
     */
    private static String getSetName() {
        String setName = "";
        boolean gettingName = true;
        while (gettingName) {
            System.out.print("\nEnter the name of a set.\n> ");
            setName = scan.next().toLowerCase();
            scan.nextLine();
            if (!usedNames.contains(setName)) {
                System.out.printf("\nSet %s does not exist, " +
                                  "please try again.\n", setName);
            } else {
                gettingName = false;
            }
        }
        return setName;
    }

    /**
     * Prints the specified set to the console.
     */
    private static void printASet() {
        String setName = getSetName();
        Set s = getSetByName(setName);
        System.out.println(s);
    }

    /**
     * Takes two sets and prints a set representing all of their values.
     */
    private static void performUnion() {
        ArrayList<Set> bothSets = getTwoSets();
        Set first = bothSets.get(0);
        Set second = bothSets.get(1);
        Set union = first.union(second);
        System.out.println(union);
    }

    /**
     * Takes two sets and prints a set representing common elements.
     */
    private static void performIntersection() {
        ArrayList<Set> bothSets = getTwoSets();
        Set first = bothSets.get(0);
        Set second = bothSets.get(1);
        Set intersection = first.intersection(second);
        System.out.println(intersection);
    }

    /**
     * Takes two sets and prints a set representing the values unique to
     * the first set.
     */
    private static void performSubtraction() {
        ArrayList<Set> bothSets = getTwoSets();
        Set first = bothSets.get(0);
        Set second = bothSets.get(1);
        Set subtraction = first.subtraction(second);
        System.out.println(subtraction);
    }

    /**
     * Calls functions to obtain two set names from the user for set algebra
     * operations.
     */
    private static ArrayList<Set> getTwoSets() {
        System.out.println("\nFirst set: ");
        String firstSetName = getSetName();
        System.out.println("\nSecond set: ");
        String secondSetName = getSetName();
        Set firstSet = getSetByName(firstSetName);
        Set secondSet = getSetByName(secondSetName);
        ArrayList<Set> bothSets = new ArrayList<Set>();
        bothSets.add(firstSet);
        bothSets.add(secondSet);
        return bothSets;
    }

    /**
     * Takes a name and returns the set it belongs to.
     */
    private static Set getSetByName(String name) {
        String currentSetName;
        Set setToGet = new Set();
        for (Set s : allSets) {
            currentSetName = s.getName();
            if (currentSetName.equals(name)) {
                setToGet = s;
            }
        }
        return setToGet;
    }

    /**
     * Takes a string array and parses all acceptable values to integers,
     * then adds them to an ArrayList if it does not already contain them.
     */
     private static ArrayList<Integer> parseToArrayList(String[] s) {
        ArrayList<Integer> rawData = new ArrayList<Integer>();
        int setLength = s.length;
        String currentItem;
        int currentToInt;
        for (int i=0;i<setLength;i++) {
            currentItem = s[i].toLowerCase();
            if (!currentItem.equals("x")) {
                try {
                    currentToInt = Integer.parseInt(currentItem);
                    if (!rawData.contains(currentToInt)) {
                        rawData.add(currentToInt);
                    }
                } catch (NumberFormatException e) {
                    System.out.printf("The value %s is not an integer " +
                                      "and will be ignored.\n", currentItem);
                }
            } else {
                i = setLength;
            }
        }
        return rawData;
    }

    public static void main(String[] args) {
        usedNames = new ArrayList<String>();
        allSets = new ArrayList<Set>();
        boolean running = true;
        while (running) {
            printMenu();
            running = getChoiceFromUser();
        }
        System.out.println("\nThank you for using SetAlgebra! Goodbye.\n");
        System.exit(0);
    }
}