/*
    CSc3410 - Spring 2015
    Molly Calhoun - lowpolymolly@gmail.com
    Date due - 4/9/2015
    Assignment: Project 5, Sorting algorithms
    Files: Timer.java, InstrumentedSorter.java, Comparator.java

    This class handles recording the run time of the sorting algorithms.
*/

public class Timer
{
    private long startTime;
    private long endTime;

    public Timer()
    {
        startTime = 0;
        endTime = 0;
    }

    public long getStartTime()
    {
        return startTime;
    }

    public long getEndTime()
    {
        return endTime;
    }

    public long getTotalTime()
    {
        return endTime - startTime;
    }

    public void start()
    {
        startTime = System.currentTimeMillis();
    }

    public void stop()
    {
        endTime = System.currentTimeMillis();
    }

    public void reset()
    {
        startTime = 0;
        endTime = 0;
    }
}