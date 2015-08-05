#include <stdio.h>

int sumOfDivisors(int n)
{
    int sum = 0;
    int half = (n / 2) + 1;
    int i;
    for (i = 1; i <= half; i++)
    {
        if (n % i == 0)
        {
            sum += i;
        }
    }
    return sum;
}

bool checkAmicable(int n, int m)
{
    int mSum = sumOfDivisors(m);
    bool isAmicable = mSum == n;
    return isAmicable;
}

int main(int argc, char const *argv[])
{
    int amicableSum = 0;
    int limit = 10000;
    bool nums[10000] = {false};
    bool isSumSuitable;
    bool isAmicable = false;
    printf("\n");

    int iSum = 0;
    int i;
    for (i = 2; i < limit; i++)
    {
        if (nums[i] == false)
        {
            iSum = sumOfDivisors(i);
            isSumSuitable = (iSum != i) && (iSum < limit) && (iSum > 1);
            if (isSumSuitable)
            {
                isAmicable = checkAmicable(i, iSum);    
            }
        }
        if (isAmicable)
        {
            printf("%d and %d are an amicable pair\n", i, iSum);
            nums[i] = true;
            nums[iSum] = true;
            amicableSum += i + iSum;
        }
        isAmicable = false;
    }

    printf("\nSum of amicable numbers under %d:\n\t%d\n", limit, amicableSum);

    return 0;
}