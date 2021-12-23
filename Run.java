import java.lang.reflect.Array;
import java.util.ArrayList;

public class Run
{
    public static void main(String args[])
    {
        // 1D Problem
        ArrayList<Integer> testArrayList = new ArrayList<>();
        for (int i = 0; i < 100000000; i++)
        {
            testArrayList.add(i);
        }

        long start =  java.lang.System.currentTimeMillis();
        System.out.println("1D problem solved iteratively. The peak found was: " + Solution.FindPeakIterative(testArrayList));
        long end =  java.lang.System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " millseconds, or " + (end - start) / 1000.0 + " seconds.");

        start =  java.lang.System.currentTimeMillis();
        System.out.println("1D problem solved with divide-and-conquer. The peak found was: " + Solution.FindPeak(testArrayList));
        end =  java.lang.System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " millseconds, or " + (end - start) / 1000.0 + " seconds.");


        // 2D Problem
        ArrayList<ArrayList<Integer>> testArrayList1 = new ArrayList<>();
        int insertValue = 0;

        for (int j = 0; j < 10000; j++)
        {
            ArrayList<Integer> tmpRow = new ArrayList<>();

            for (int i = 0; i < 10000; i++)
            {
                tmpRow.add(insertValue);
                insertValue++;
            }

            testArrayList1.add(tmpRow);
        }

        start =  java.lang.System.currentTimeMillis();
        System.out.println("2D problem solved with dive-and-conquer. The peak found was: " + Solution.FindPeak2D(testArrayList1));
        end =  java.lang.System.currentTimeMillis();
        System.out.println("Time taken: " + (end - start) + " milliseconds, or " + (end - start) / 1000.0 + " seconds.");
    }
}