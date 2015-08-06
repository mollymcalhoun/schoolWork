/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 4/9/2015
    Assignment: Project 5, Sorting algorithms
    Files: Timer.java, InstrumentedSorter.java, Comparator.java

    This is the abstract class for the Instrumented Sorter as specified in
      the instructions.
*/

import java.util.Random;

public abstract class InstrumentedSorter
{
    protected int instructionCounter;
    protected Timer timer;

    public InstrumentedSorter()
    {
        timer = new Timer();
    }

    public void resetInstructionCounter()
    {
        instructionCounter = 0;
    }

    public void incrementInstructionCounter()
    {
        instructionCounter++;
    }

    public int getInstructionCounter()
    {
        return instructionCounter;
    }

    public int[] createRandomArray(int size)
    {
        Random r = new Random(System.currentTimeMillis());
        int[] array = new int[size];
        for (int i = 0; i < size; i++)
        {
            array[i] = r.nextInt(500);
        }
        return array;
    }

    public abstract void bubbleSort(int[] array);
    public abstract void selectionSort(int[] array);
    public abstract void insertionSort(int[] array);
    public abstract void mergeSort(int[] array);
    public abstract void quickSort(int[] array);
    public abstract void randomizedQuickSort(int[] array);
}