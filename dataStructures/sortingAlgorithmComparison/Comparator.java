/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 4/9/2015
    Assignment: Project 5, Sorting algorithms
    Files: Timer.java, InstrumentedSorter.java, Comparator.java

    This class contains the implementations of the sorting algorithms
      and also handles reporting their times and number of instructions.
*/

import java.util.Arrays;
import java.util.Random;
import java.io.IOException;

public class Comparator extends InstrumentedSorter
{
    int problemSize = 10000;
    int numArrays = 20;
    // Arrays to hold times and number of instructions
    int[] bubbleTimes = new int[numArrays];
    int[] bubbleInstructions = new int[numArrays];
    int[] selectionTimes = new int[numArrays];
    int[] selectionInstructions = new int[numArrays];
    int[] insertionTimes = new int[numArrays];
    int[] insertionInstructions = new int[numArrays];
    int[] mergeTimes = new int[numArrays];
    int[] mergeInstructions = new int[numArrays];
    int[] quickTimes = new int[numArrays];
    int[] quickInstructions = new int[numArrays];
    int[] randomQuickTimes = new int[numArrays];
    int[] randomInstructions = new int[numArrays];
    // Variables for average times and instructions
    int bubbleAverage;
    long bubbleInstructionAvg;
    int selectionAverage;
    long selectionInstructionAvg;
    int insertionAverage;
    long insertionInstructionAvg;
    int mergeAverage;
    long mergeInstructionAvg;
    int quickAverage;
    long quickInstructionAvg;
    int randomQuickAverage;
    long randomInstructionAvg;

    /*
        Bubble sort

        My implementation is adapted from the pseudocode on the Wikipedia
          page for bubble sort:
          http://en.wikipedia.org/wiki/Bubble_sort#Pseudocode_implementation

        Traverses the list repeatedly, comparing each two consecutive values
          and swapping them if they are out of order. The sort is completed
          when the list is traversed without any values being swapped.
    */
    public void bubbleSort(int[] array)
    {
        //System.out.printf("Original array:\n%s\n", Arrays.toString(array));
        int length = array.length;
        boolean swapped = true;
        int temp;

        // The algorithm runs as long as at least one swap happens per loop
        while (swapped)
        {
            incrementInstructionCounter(); // for "hidden" == of while condition

            swapped = false;
            incrementInstructionCounter(); // for =

            incrementInstructionCounter(); // for = of loop
            for (int i = 1; i < length; i++)
            {
                incrementInstructionCounter(); // for < of loop
                incrementInstructionCounter(); // for ++ of loop

                if (array[i-1] > array[i])
                {
                    // Swap the values
                    temp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = temp;
                    swapped = true;
                    incrementInstructionCounter(); // for =
                    incrementInstructionCounter(); // for =
                    incrementInstructionCounter(); // for =
                    incrementInstructionCounter(); // for =
                }
                incrementInstructionCounter(); // for > of if
            }
            incrementInstructionCounter(); // for < of loop
            incrementInstructionCounter(); // for ++ of loop
        }
        incrementInstructionCounter(); // for "hidden" == of while condition

        //System.out.printf("Bubble sorted array:\n%s\n", Arrays.toString(array));
        //System.out.printf(" Bubble sort instructions: %d\n", getInstructionCounter());
        //resetInstructionCounter();
    }

    /*
        Selection sort

        Adapted from the pseudocode on the Wikipedia page for selection sort:
          http://en.wikipedia.org/wiki/Selection_sort#Implementation

        Sorts the list by placing the minimum value at index 0, then the
          second smallest value at index 1, and so on until the whole list
          has been sorted.
    */
    public void selectionSort(int[] array)
    {
        //System.out.printf("Original array:\n%s\n", Arrays.toString(array));
        int length = array.length;
        int min;
        int temp;

        incrementInstructionCounter(); // for = of loop
        for (int i = 0; i < length; i++)
        {
            incrementInstructionCounter(); // for < of loop
            incrementInstructionCounter(); // for ++ of loop

            // Start with the element at i as the smallest
            min = i;
            incrementInstructionCounter(); // for =

            incrementInstructionCounter(); // for = of loop
            // We only want to iterate over the values after the value at i
            //   because the values before i are sorted
            for(int j = i + 1; j < length; j++)
            {
                incrementInstructionCounter(); // for < of loop
                incrementInstructionCounter(); // for ++ of loop
                // Check if we've found a new minimum
                if (array[j] < array[min])
                {
                    min = j;
                    incrementInstructionCounter(); // for =
                }
                incrementInstructionCounter(); // for < of if
            }
            incrementInstructionCounter(); // for < of loop
            incrementInstructionCounter(); // for ++ of loop
            // If the index of the minimum has changed, we swap it with
            //   the element at index i
            if (min != i)
            {
                temp = array[min];
                array[min] = array[i];
                array[i] = temp;
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for =
            }
            incrementInstructionCounter(); // for != of loop
        }
        incrementInstructionCounter(); // for < of loop
        incrementInstructionCounter(); // for ++ of loop

        //System.out.printf("Selection sorted array:\n%s\n", Arrays.toString(array));
        //System.out.printf(" Selection sort instructions: %d\n", getInstructionCounter());
        //resetInstructionCounter();
    }

    /*
        Insertion sort

        Adapted from pseudocode on the Wikipedia page for insertion sort:
          http://en.wikipedia.org/wiki/Insertion_sort#Algorithm

        Traverses the list and swaps the value at index i with any value
          to the left of it that is larger until a smaller value or the
          end of the list is encountered.
    */
    public void insertionSort(int[] array)
    {
        //System.out.printf("Original array:\n%s\n", Arrays.toString(array));

        int length = array.length;
        int temp;

        incrementInstructionCounter(); // for = of loop
        for (int i = 1; i < length; i++)
        {
            incrementInstructionCounter(); // for < of loop
            incrementInstructionCounter(); // for ++ of loop

            int j = i;
            incrementInstructionCounter(); // for =
            // We want to compare the value at j with every value before it
            //   that is larger and swap them with the value at j until
            //   encountering a value that is smaller or hitting index 0
            while ((j > 0) && (array[j-1] > array[j]))
            {
                incrementInstructionCounter(); // for > of loop
                incrementInstructionCounter(); // for && of loop
                incrementInstructionCounter(); // for > of loop
                // Swap the value at j and the value just before it
                temp = array[j];
                array[j] = array[j-1];
                array[j-1] = temp;
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for =

                j--;
                incrementInstructionCounter(); // for --
            }   
            incrementInstructionCounter(); // for > of loop
            incrementInstructionCounter(); // for && of loop
            incrementInstructionCounter(); // for > of loop 
        }
        incrementInstructionCounter(); // for < of loop
        incrementInstructionCounter(); // for ++ of loop

        //System.out.printf("Insertion sorted array:\n%s\n", Arrays.toString(array));
        //System.out.printf(" Insertion sort instructions: %d\n", getInstructionCounter());
        //resetInstructionCounter();
    }

    /*
        Merge sort

        Adapted from pseudocode on the Wikipedia merge sort page:
          http://en.wikipedia.org/wiki/Merge_sort#Algorithm

        Recursively divides the array into smaller and smaller sub-arrays
          until they reach a size of one, then the algorithm moves up the
          call chain merging the sub-arrays into sorted order until the
          final two arrays are merged into a fully sorted version of the
          initial array.

    */
    // Basically a dummy method to make it less clumsy to call
    public void mergeSort(int[] array)
    {
        //System.out.printf("Original array:\n%s\n", Arrays.toString(array));

        // Array2 stores the values in sorted order before they are copied
        //   back to the original array
        int[] array2 = new int[array.length];
        mergeSort(array, array2, array.length);

        //System.out.printf(" Merge sort instructions: %d\n", getInstructionCounter());
        //System.out.printf("Merge sorted array:\n%s\n", Arrays.toString(array));
        //resetInstructionCounter();
    }

    public void mergeSort(int[] a1, int[] a2, int end)
    {
        // The initial bounds are going to be 0 and the length of the array.
        splitMerge(a1, a2, 0, end);
    }

    // Splits the portion of the array bounded by the indexes "begin" and
    //   "end" in half if it is more than 1 element long, then calls itself
    //   with the halves of the sub-array.
    public void splitMerge(int[] a1, int[] a2, int begin, int end)
    {
        incrementInstructionCounter(); // for -
        incrementInstructionCounter(); // for >= of if
        // The function returns when the sub-array reaches a size of one
        if (end - begin >= 2)
        {
            int middle = (begin + end) / 2;
            incrementInstructionCounter(); // for =
            incrementInstructionCounter(); // for +
            incrementInstructionCounter(); // for /

            splitMerge(a1, a2, begin, middle);
            splitMerge(a1, a2, middle, end);
            merge(a1, a2, begin, middle, end);
            // We need to copy the sorted values from the preceding merge
            //   back into a1, which is the original array.
            copyArray(a1, a2, begin, end);
        }
        else
        {
            return;
        }
    }

    // Merges two sub-arrays delimited by 'begin', 'middle' and 'end'
    //   by copying their values into sorted order into a2, which is then
    //   copied back to a1 after the method runs.
    public void merge(int[] a1, int[] a2,
                      int begin, int middle, int end)
    {
        // Indices for the two halves of the array being merged
        int left = begin;
        int right = middle;
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =

        incrementInstructionCounter(); // for = of loop
        for (int i = begin; i < end; i++)
        {
            incrementInstructionCounter(); // for < of loop
            incrementInstructionCounter(); // for ++ of loop

            // Only proceed with the if statement if the left pointer has
            //   not advanced past the middle of the sub-array and the value
            //   at index 'left' is less than or equal to the one at 'right'
            boolean condition = left < middle &&
                                (right >= end || a1[left] <= a1[right]);
            incrementInstructionCounter(); // for =
            incrementInstructionCounter(); // for <
            incrementInstructionCounter(); // for &&
            incrementInstructionCounter(); // for >=
            incrementInstructionCounter(); // for ||
            incrementInstructionCounter(); // for <=

            incrementInstructionCounter(); // for checking condition
            if (condition)
            {
                // If the value at 'left' is less than the one at 'right',
                //   we store that value at index i in the second array
                //   and increment the left index...
                a2[i] = a1[left];
                left++;
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for ++
            }
            else
            {
                // ...and if the value at 'left' is greater then we store the
                //   value at 'right' in the second array at index i and
                //   increment the right index.
                a2[i] = a1[right];
                right++;
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for ++
            }
        }
        incrementInstructionCounter(); // for < of loop
        incrementInstructionCounter(); // for ++ of loop
        // At this point, the sub-array delimeted by 'begin' and 'end'
        //   will be sorted and ready to be merged with another
    }

    // The merge() method stores the values in sorted order in the array 'a2',
    //   so we want to overrwrite the values of 'a1' (the original array)
    //   at the indices between 'begin' and 'end' with the sorted values
    public void copyArray(int[] a1, int[] a2, int begin, int end)
    {
        incrementInstructionCounter(); // for == of loop
        for (int i = begin; i < end; i++)
        {
            incrementInstructionCounter(); // for < of loop
            incrementInstructionCounter(); // for ++ of loop

            a1[i] = a2[i];
            incrementInstructionCounter(); // for =
        }
        incrementInstructionCounter(); // for < of loop
        incrementInstructionCounter(); // for ++ of loop
    }

    /*
        Quicksort

        Adapted from pseudocode on the Wikipedia page for quicksort:
          http://en.wikipedia.org/wiki/Quicksort#Algorithm

        Recursively divides the array into partitions, sorts the partitions
    */
    // Method for convenience
    public void quickSort(int[] array)
    {
        //System.out.printf("Original array:\n%s\n", Arrays.toString(array));

        // Initial bounds are 0 and the last index of the array
        quickSort(array, 0, array.length-1);
        incrementInstructionCounter(); // for -

        //System.out.printf("Quicksorted array:\n%s\n", Arrays.toString(array));
        // System.out.printf(" Quicksort instructions: %d\n", getInstructionCounter());
        //resetInstructionCounter();
    }

    public void quickSort(int[] array, int low, int high)
    {
        incrementInstructionCounter(); // for < of if
        // The lower bound has to be less than the upper bound
        if (low < high)
        {
            // Partition returns a midpoint index to be used to derive new
            //   bounds for successive recursive quickSort() calls
            int p = partition(array, low, high);
            quickSort(array, low, p - 1);
            quickSort(array, p + 1, high);
            incrementInstructionCounter(); // for =
            incrementInstructionCounter(); // for -
            incrementInstructionCounter(); // for +
        }
    }

    // Sort everything smaller than the pivot value to the left of it,
    //   then return an index to be used for further partitioning
    public int partition(int[] array, int low, int high)
    {
        // Pivot is always the first element of this segment of the array
        int pivot = low;
        int value = array[pivot];
        // Swap the value at the pivot with the value at 'high'
        int temp = array[high];
        array[high] = array[pivot];
        array[pivot] = temp;
        int wall = low;
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =

        incrementInstructionCounter(); // for = of loop
        for (int i = low; i < high; i++)
        {
            incrementInstructionCounter(); // for < of loop
            incrementInstructionCounter(); // for ++ of loop

            incrementInstructionCounter(); // for < of if
            if (array[i] < value)
            {
                // Swap the value at i with the value at 'wall' if it is
                //   less than the value at the pivot
                temp = array[i];
                array[i] = array[wall];
                array[wall] = temp;
                // Increment the index of 'wall' by one because everything
                //   left of it is less than the pivot value and therefore
                //   dealt with
                wall += 1;
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for +=
            }
        }
        incrementInstructionCounter(); // for ++ of loop
        incrementInstructionCounter(); // for < of loop
        // The value at 'high' is the pivot value, so it belongs where 'wall'
        //   is pointing, since this is between all the values the pivot value
        //   is less than and the values it is greater than
        temp = array[wall];
        array[wall] = array[high];
        array[high] = temp;
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        // 'wall' is returned to be used to divide sub-arrays for the next
        //   partition call
        return wall;
    }

    /*
        Randomized quicksort

        This is the same as the quicksort method, except that the pivot
          for the partitioning step is chosen at random from the appropriate
          range of indices in the sub-array
    */
    public void randomizedQuickSort(int[] array)
    {
        //System.out.printf("Original array:\n%s\n", Arrays.toString(array));
        randomizedQuickSort(array, 0, array.length-1);
        incrementInstructionCounter(); // for -
        //System.out.printf("Randomized quicksorted array:\n%s\n", Arrays.toString(array));
        //System.out.printf(" Randomized quicksort instructions: %d\n", getInstructionCounter());
        //resetInstructionCounter();
    }

    public void randomizedQuickSort(int[] array, int low, int high)
    {
        incrementInstructionCounter(); // for < of if
        if (low < high)
        {
            int p = randomPartition(array, low, high);
            randomizedQuickSort(array, low, p - 1);
            randomizedQuickSort(array, p + 1, high);
            incrementInstructionCounter(); // for =
            incrementInstructionCounter(); // for -
            incrementInstructionCounter(); // for +
        }
    }

    public int randomPartition(int[] array, int low, int high)
    {
        Random rand = new Random(System.currentTimeMillis());
        int pivot = rand.nextInt((high - low) + 1) + low;
        incrementInstructionCounter(); // for -
        incrementInstructionCounter(); // for +
        incrementInstructionCounter(); // for +
        int value = array[pivot];
        int temp = array[high];
        array[high] = array[pivot];
        array[pivot] = temp;
        int wall = low;
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =

        incrementInstructionCounter(); // for = of loop
        for (int i = low; i < high; i++)
        {
            incrementInstructionCounter(); // for < of loop
            incrementInstructionCounter(); // for ++ of loop

            incrementInstructionCounter(); // for < of if
            if (array[i] < value)
            {
                temp = array[i];
                array[i] = array[wall];
                array[wall] = temp;
                wall += 1;
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for =
                incrementInstructionCounter(); // for +=
            }
        }
        incrementInstructionCounter(); // for < of loop
        incrementInstructionCounter(); // for ++ of loop

        temp = array[wall];
        array[wall] = array[high];
        array[high] = temp;
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        incrementInstructionCounter(); // for =
        return wall;
    }

    // Creates 20 random arrays each of size 1000 - 10000 in increments of
    //   1000, running all algorithms on copies of the random arrays and
    //   collecting the number of instructions and time taken.
    public void runManyTimes()
    {
        int[] randomArray;
        int[] sortArray;
        for (int size = 1000; size <= 10000; size += 1000)
        {
            problemSize = size;
            for (int i = 0; i < numArrays; i++)
            {
                // Make and clone a random array
                randomArray = createRandomArray(size);
                sortArray = randomArray.clone();

                // For each algorithm, the timer is started, the sort runs,
                //   the timer stops, then the total time and number of
                //   instructions are stored into an array and also added
                //   to a value to be used for averaging later
                timer.start();
                bubbleSort(sortArray);
                timer.stop();
                bubbleTimes[i] = (int)timer.getTotalTime();
                bubbleAverage += bubbleTimes[i];
                timer.reset();
                bubbleInstructions[i] = getInstructionCounter();
                bubbleInstructionAvg += bubbleInstructions[i];
                resetInstructionCounter();

                sortArray = randomArray.clone();
                timer.start();
                selectionSort(sortArray);
                timer.stop();
                selectionTimes[i] = (int)timer.getTotalTime();
                selectionAverage += selectionTimes[i];
                timer.reset();
                selectionInstructions[i] = getInstructionCounter();
                selectionInstructionAvg += selectionInstructions[i];
                resetInstructionCounter();

                sortArray = randomArray.clone();
                timer.start();
                insertionSort(sortArray);
                timer.stop();
                insertionTimes[i] = (int)timer.getTotalTime();
                insertionAverage += insertionTimes[i];
                timer.reset();
                insertionInstructions[i] = getInstructionCounter();
                insertionInstructionAvg += insertionInstructions[i];
                resetInstructionCounter();

                sortArray = randomArray.clone();
                timer.start();
                mergeSort(createRandomArray(size));
                timer.stop();
                mergeTimes[i] = (int)timer.getTotalTime();
                mergeAverage += mergeTimes[i];
                timer.reset();
                mergeInstructions[i] = getInstructionCounter();
                mergeInstructionAvg += mergeInstructions[i];
                resetInstructionCounter();

                sortArray = randomArray.clone();
                timer.start();
                quickSort(sortArray);
                timer.stop();
                quickTimes[i] = (int)timer.getTotalTime();
                quickAverage += quickTimes[i];
                timer.reset();
                quickInstructions[i] = getInstructionCounter();
                quickInstructionAvg += quickInstructions[i];
                resetInstructionCounter();

                sortArray = randomArray.clone();
                timer.start();
                randomizedQuickSort(sortArray);
                timer.stop();
                randomQuickTimes[i] = (int)timer.getTotalTime();
                randomQuickAverage += randomQuickTimes[i];
                timer.reset();
                randomInstructions[i] = getInstructionCounter();
                randomInstructionAvg += randomInstructions[i];
                resetInstructionCounter();

                //System.out.println("Finished array " + i);
            }
            // Get averages and standard deviations for this round of
            //   twenty random arrays
            calculateAverages();
            calculateStdDeviation();
            reset();
            pressToContinue();
        }
    }

    // Calculates and prints the average times and instructions
    public void calculateAverages()
    {
        System.out.printf("\nAverages for array size %d:\n", problemSize);
        //System.out.printf("Number of arrays: %d\n", numArrays);

        bubbleAverage /= numArrays;
        bubbleInstructionAvg /= numArrays;
        System.out.printf(" Bubble sort\n  average time: %d ms\n", bubbleAverage);
        System.out.printf("  average instructions: %d\n", bubbleInstructionAvg);

        selectionAverage /= numArrays;
        selectionInstructionAvg /= numArrays;
        System.out.printf(" Selection sort\n  average time: %d ms\n", selectionAverage);
        System.out.printf("  average instructions: %d\n", selectionInstructionAvg);

        insertionAverage /= numArrays;
        insertionInstructionAvg /= numArrays;
        System.out.printf(" Insertion sort\n  average time: %d ms\n", insertionAverage);
        System.out.printf("  average instructions: %d\n", insertionInstructionAvg);

        mergeAverage /= numArrays;
        mergeInstructionAvg /= numArrays;
        System.out.printf(" Merge sort\n  average time: %d ms\n", mergeAverage);
        System.out.printf("  average instructions: %d\n", mergeInstructionAvg);

        quickAverage /= numArrays;
        quickInstructionAvg /= numArrays;
        System.out.printf(" Quicksort\n  average time: %d ms\n", quickAverage);
        System.out.printf("  average instructions: %d\n", quickInstructionAvg);

        randomQuickAverage /= numArrays;
        randomInstructionAvg /= numArrays;
        System.out.printf(" Randomized quicksort\n  average time: %d ms\n", randomQuickAverage);
        System.out.printf("  average instructions: %d\n", randomInstructionAvg);
    }

    // Calculates and prints standard deviations for times and instructions
    public void calculateStdDeviation()
    {
        System.out.printf("\nStandard deviations for array size %d:\n", problemSize);
        double stdDeviationTime = 0;
        double stdDeviationInstr = 0;
        for (int i = 0; i < numArrays; i++)
        {
            stdDeviationTime += Math.pow(bubbleTimes[i] - bubbleAverage, 2);
            stdDeviationInstr += Math.pow(bubbleInstructions[i] - bubbleInstructionAvg, 2);
        }
        stdDeviationTime = Math.sqrt(stdDeviationTime / numArrays);
        stdDeviationInstr = Math.sqrt(stdDeviationInstr / numArrays);
        System.out.printf(" Bubble sort\n  time: %.2f ms\n  instructions: %.2f\n", stdDeviationTime, stdDeviationInstr);

        stdDeviationTime = 0;
        stdDeviationInstr = 0;
        for (int i = 0; i < numArrays; i++)
        {
            stdDeviationTime += Math.pow(selectionTimes[i] - selectionAverage, 2);
            stdDeviationInstr += Math.pow(selectionInstructions[i] - selectionInstructionAvg, 2);
        }
        stdDeviationTime = Math.sqrt(stdDeviationTime / numArrays);
        stdDeviationInstr = Math.sqrt(stdDeviationInstr / numArrays);
        System.out.printf(" Selection sort\n  time: %.2f ms\n  instructions: %.2f\n", stdDeviationTime, stdDeviationInstr);

        stdDeviationTime = 0;
        stdDeviationInstr = 0;
        for (int i = 0; i < numArrays; i++)
        {
            stdDeviationTime += Math.pow(insertionTimes[i] - insertionAverage, 2);
            stdDeviationInstr += Math.pow(insertionInstructions[i] - insertionInstructionAvg, 2);
        }
        stdDeviationTime = Math.sqrt(stdDeviationTime / numArrays);
        stdDeviationInstr = Math.sqrt(stdDeviationInstr / numArrays);
        System.out.printf(" Insertion sort\n  time: %.2f ms\n  instructions: %.2f\n", stdDeviationTime, stdDeviationInstr);

        stdDeviationTime = 0;
        stdDeviationInstr = 0;
        for (int i = 0; i < numArrays; i++)
        {
            stdDeviationTime += Math.pow(mergeTimes[i] - mergeAverage, 2);
            stdDeviationInstr += Math.pow(mergeInstructions[i] - mergeInstructionAvg, 2);
        }
        stdDeviationTime = Math.sqrt(stdDeviationTime / numArrays);
        stdDeviationInstr = Math.sqrt(stdDeviationInstr / numArrays);
        System.out.printf(" Merge sort\n  time: %.2f ms\n  instructions: %.2f\n", stdDeviationTime, stdDeviationInstr);

        stdDeviationTime = 0;
        stdDeviationInstr = 0;
        for (int i = 0; i < numArrays; i++)
        {
            stdDeviationTime += Math.pow(quickTimes[i] - quickAverage, 2);
            stdDeviationInstr += Math.pow(quickInstructions[i] - quickInstructionAvg, 2);
        }
        stdDeviationTime = Math.sqrt(stdDeviationTime / numArrays);
        stdDeviationInstr = Math.sqrt(stdDeviationInstr / numArrays);
        System.out.printf(" Quicksort\n  time: %.2f ms\n  instructions: %.2f\n", stdDeviationTime, stdDeviationInstr);

        stdDeviationTime = 0;
        stdDeviationInstr = 0;
        for (int i = 0; i < numArrays; i++)
        {
            stdDeviationTime += Math.pow(randomQuickTimes[i] - randomQuickAverage, 2);
            stdDeviationInstr += Math.pow(randomInstructions[i] - randomInstructionAvg, 2);
        }
        stdDeviationTime = Math.sqrt(stdDeviationTime / numArrays);
        stdDeviationInstr = Math.sqrt(stdDeviationInstr / numArrays);
        System.out.printf(" Randomized quicksort\n  time: %.2f ms\n  instructions: %.2f\n", stdDeviationTime, stdDeviationInstr);
        System.out.println();
    }

    // Resets average variables to zero for the next run of arrays
    public void reset()
    {
        bubbleAverage = 0;
        selectionAverage = 0;
        insertionAverage = 0;
        mergeAverage = 0;
        quickAverage = 0;
        randomQuickAverage = 0;
        bubbleInstructionAvg = 0;
        selectionInstructionAvg = 0;
        insertionInstructionAvg = 0;
        mergeInstructionAvg = 0;
        quickInstructionAvg = 0;
        randomInstructionAvg = 0;
    }

    // Halts until enter is pressed
    public void pressToContinue()
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
        Comparator c = new Comparator();
        boolean testing = false;

        if (testing)
        {
            int size = 20000;
            int[] randomArray = c.createRandomArray(size);
            int[] sortArray = randomArray.clone();
            
            c.timer.start();
            c.bubbleSort(sortArray);
            c.timer.stop();
            System.out.printf("Bubble sort time: %d ms\n\n", c.timer.getTotalTime());
            c.timer.reset();

            sortArray = randomArray.clone();
            c.timer.start();
            c.selectionSort(sortArray);
            c.timer.stop();
            System.out.printf("Selection sort time: %d ms\n\n", c.timer.getTotalTime());
            c.timer.reset();

            sortArray = randomArray.clone();
            c.timer.start();
            c.insertionSort(sortArray);
            c.timer.stop();
            System.out.printf("Insertion sort time: %d ms\n\n", c.timer.getTotalTime());
            c.timer.reset();
            
            sortArray = randomArray.clone();
            c.timer.start();
            c.quickSort(sortArray);
            c.timer.stop();
            System.out.printf(" Quicksort sort time: %d ms\n\n", c.timer.getTotalTime());
            c.timer.reset();

            sortArray = randomArray.clone();
            c.timer.start();
            c.randomizedQuickSort(sortArray);
            c.timer.stop();
            System.out.printf(" Randomized quicksort sort time: %d ms\n\n", c.timer.getTotalTime());
            c.timer.reset();

            sortArray = randomArray.clone();
            c.timer.start();
            c.mergeSort(sortArray);
            c.timer.stop();
            System.out.printf(" Merge sort time: %d ms\n\n", c.timer.getTotalTime());
            c.timer.reset();
        }
        else
        {
            c.runManyTimes();
        }
    }
}