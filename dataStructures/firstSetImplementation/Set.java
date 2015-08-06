/**
 * Name: Molly Calhoun
 * Class: CSc3410
 * Assignment: Project 1
 */

import java.util.ArrayList;

/**
 * Set data structure, a collection of unique integers.
 */
public class Set {
    private String name;
    private ArrayList<Integer> values;

    /**
     * Accepts a name and ArrayList of values to create a set from.
     */
    public Set(String setName, ArrayList<Integer> data) {
        name = setName;
        values = data;
    }

    /**
     * Constructor for an empty set.
     */
    public Set() {
        name = "";
        values = new ArrayList<Integer>();
    }

    /**
     * Returns the name of the set.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the ArrayList holding the set's data.
     */
    public ArrayList<Integer> getValues() {
        return values;
    }

    /**
     * Checks if the supplied value is already a member of the set,
     * and adds it if it is not.
     */
    public void addValue(int value) {
        ArrayList<Integer> data = getValues();
        if (!data.contains(value)) {
            data.add(value);
        } else {
            System.out.println("Set already contains this value!");
        }
    }

    /**
     * Performs a union operation and returns a set representing all of the
     * elements in both sets.
     */
    public Set union(Set s) {
        ArrayList<Integer> data = getValues();
        ArrayList<Integer> otherData = s.getValues();
        ArrayList<Integer> unionList = new ArrayList<Integer>();
        for (int i : data) {
            if (!unionList.contains(i)) {
                unionList.add(i);
            }
        }
        for (int j : otherData) {
            if (!unionList.contains(j)) {
                unionList.add(j);
            }
        }
        Set unionSet = new Set("union", unionList);
        return unionSet;
    }

    /**
     * Performs an intersection operation and returns a set representing
     * the elements that are present in both sets.
     */
    public Set intersection(Set s) {
        ArrayList<Integer> data = getValues();
        ArrayList<Integer> otherData = s.getValues();
        ArrayList<Integer> intersectionList = new ArrayList<Integer>();
        boolean condition;
        for (int i : data) {
            condition = otherData.contains(i) && !intersectionList.contains(i);
            if (condition) {
                intersectionList.add(i);
            }
        }
        Set intersectionSet = new Set("intersection", intersectionList);
        return intersectionSet;
    }

    /**
     * Performs a subtraction operation that returns a set containing the
     * difference of the two sets.
     */
    public Set subtraction(Set s) {
        ArrayList<Integer> data = getValues();
        ArrayList<Integer> otherData = s.getValues();
        ArrayList<Integer> subtractionList = new ArrayList<Integer>();
        boolean condition;
        for (int i : data) {
            condition = !otherData.contains(i) && !subtractionList.contains(i);
            if (condition) {
                subtractionList.add(i);
            }
        }
        Set subtractionSet = new Set("subtraction", subtractionList);
        return subtractionSet;
    }

    /**
     * Converts the set to printable format, including its name.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder();
        ArrayList<Integer> data = getValues();
        String setName = getName();
        strb.append("\nSet \"" + setName + "\":\n");
        strb.append("  [");
        if (data.isEmpty()){
            strb.append("Empty");
        } else {
            for (int i : data) {
                strb.append(i + ", ");
            }
            strb.append("\b\b");
        }
        strb.append("]");
        return strb.toString();
    }
}