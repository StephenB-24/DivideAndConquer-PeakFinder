import java.util.ArrayList;

public class Solution
{
    public static <T extends Comparable> T FindPeak(ArrayList<T> data)
    {
        return findPeakHelper(data, 0, data.size() - 1);
    }

    public static <T extends Comparable> T FindPeakIterative(ArrayList<T> data)
    {
        int rightIndex = data.size() - 1;

        for (int leftIndex = 0; leftIndex <= rightIndex;)
        {
            int midIndex = (leftIndex + rightIndex) / 2;
            T midElement = data.get(midIndex);

            if (leftIndex != midIndex && data.get(midIndex - 1).compareTo(midElement) >= 0)
            {
                rightIndex = midIndex - 1;
            }
            else if (rightIndex != midIndex && data.get(midIndex + 1).compareTo(midElement) >= 0)
            {
                leftIndex = midIndex + 1;
            }

            if (leftIndex == rightIndex && leftIndex == midIndex)
            {
                return midElement;
            }
        }

        return null;
    }

    public static <T extends Comparable> T FindPeak2D(ArrayList<ArrayList<T>> data)
    {
        return findPeakHelper2D(data, 0, data.size() - 1);
    }

    private static <T extends Comparable> T findPeakHelper(ArrayList<T> data, int leftIndex, int rightIndex)
    {
        // Validate data
        if (rightIndex < leftIndex)
        {
            throw new IndexOutOfBoundsException("Right index cannot be less than the left index.");
        }
        else if (rightIndex > data.size() - 1)
        {
            throw new ArrayIndexOutOfBoundsException("Right index cannot be greater than the ArrayList");
        }
        else if (leftIndex < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Left index cannot be less than 0.");
        }

        // If the array is 1 element large, return that element
        if (leftIndex == rightIndex)
        {
            return data.get(leftIndex);
        }

        int midIndex = (leftIndex + rightIndex) / 2;
        T midElement = data.get(midIndex);

        if (leftIndex != midIndex && data.get(midIndex - 1).compareTo(midElement) > 0)
        {
            return findPeakHelper(data,  leftIndex, midIndex - 1);
        }
        else if (rightIndex != midIndex && data.get(midIndex + 1).compareTo(midElement) > 0)
        {
            return findPeakHelper(data,  midIndex + 1, rightIndex);
        }
        else
        {
            return midElement;
        }
    }

    private static <T extends Comparable> T findPeakHelper2D(ArrayList<ArrayList<T>> data, int topIndex, int bottomIndex)
    {
        // Validate data
        if (topIndex > bottomIndex)
        {
            throw new IndexOutOfBoundsException("Top index cannot be greater than the bottom index.");
        }
        else if (bottomIndex > data.size() - 1)
        {
            throw new ArrayIndexOutOfBoundsException("Bottom index cannot be greater than the number of rows.");
        }
        else if (topIndex < 0) {
            throw new ArrayIndexOutOfBoundsException("Top index cannot be less than 0.");
        }

        int verticalMidIndex = (bottomIndex + topIndex) / 2;

        // Find the max for the current row.
        int horizontalMaxIndex = 0;
        for (int i = 0; i < data.get(verticalMidIndex).size(); i++)
        {
            T current = data.get(verticalMidIndex).get(i);
            T currentHorizontalMax = data.get(verticalMidIndex).get(horizontalMaxIndex);

            if (current.compareTo(currentHorizontalMax) > 0)
            {
                horizontalMaxIndex = i;
            }
        }

        T horizontalMaxElement = data.get(verticalMidIndex).get(horizontalMaxIndex);

        // If the bottom and top index have already been pinched down to one row, return this max
        if (topIndex != verticalMidIndex &&
                data.get(verticalMidIndex - 1).get(horizontalMaxIndex).compareTo(horizontalMaxElement) > 0)
        {
            return findPeakHelper2D(data, topIndex, verticalMidIndex - 1);
        }
        else if (bottomIndex != verticalMidIndex &&
                data.get(verticalMidIndex + 1).get(horizontalMaxIndex).compareTo(horizontalMaxElement) > 0)
        {
            return findPeakHelper2D(data, verticalMidIndex + 1, bottomIndex);
        }
        else
        {
            return horizontalMaxElement;
        }
    }
}
