import java.util.ArrayList;

public class Solution
{
    public static <T extends Comparable> T FindPeak2D(ArrayList<ArrayList<T>> data)
    {
        return findPeakHelper2D(data, 0, data.get(0).size() - 1, data.size() - 1,
                0);
    }

    public static <T extends Comparable> T FindPeak3D(ArrayList<ArrayList<ArrayList<T>>> data)
    {
        return findPeakHelper3D(data, 0, data.size() - 1, 0,
                data.get(0).size() - 1, 0, data.get(0).get(0).size() - 1);
    }

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

        if (leftIndex != midIndex && data.get(midIndex - 1).compareTo(midElement) >= 0)
        {
            return findPeakHelper(data,  leftIndex, midIndex - 1);
        }
        else if (rightIndex != midIndex && data.get(midIndex + 1).compareTo(midElement) >= 0)
        {
            return findPeakHelper(data,  midIndex + 1, rightIndex);
        }
        else
        {
            return midElement;
        }
    }

    private static <T extends Comparable> T findPeakHelper2D(ArrayList<ArrayList<T>> data,
                                                             int leftIndex, int rightIndex,
                                                             int bottomIndex, int topIndex)
    {
        // Validate data
        if (rightIndex < leftIndex)
        {
            throw new IndexOutOfBoundsException("Right index cannot be less than the left index.");
        }
        else if (topIndex > bottomIndex)
        {
            throw new IndexOutOfBoundsException("Top index cannot be less than the bottom index.");
        }
        else if (rightIndex > data.get(0).size() - 1)
        {
            throw new ArrayIndexOutOfBoundsException("Right index cannot be greater than the ArrayList");
        }
        else if (leftIndex < 0)
        {
            throw new ArrayIndexOutOfBoundsException("Left index cannot be less than 0.");
        }

        // If the array is 1 element large, return that element.
        if (leftIndex == rightIndex && topIndex == bottomIndex)
        {
            return data.get(bottomIndex).get(leftIndex);
        }

        // Can be thought of as a horizontal bar finding the vertical midpoint.
        int verticalMidIndex = (topIndex + bottomIndex) / 2;
        // Can be thought of as a vertical bar finding the horizontal midpoint.
        int horizontalMidIndex = (leftIndex + rightIndex) / 2;

        T midElement = data.get(verticalMidIndex).get(horizontalMidIndex);

        if (leftIndex != horizontalMidIndex &&
                data.get(verticalMidIndex).get(horizontalMidIndex - 1).compareTo(midElement) >= 0)
        {
            // Left element is bigger, go left.
            return findPeakHelper2D(data,  leftIndex, horizontalMidIndex - 1, bottomIndex, topIndex);
        }
        else if (rightIndex != horizontalMidIndex &&
                data.get(verticalMidIndex).get(horizontalMidIndex + 1).compareTo(midElement) >= 0)
        {
            // Right element is bigger, go right.
            return findPeakHelper2D(data,  horizontalMidIndex + 1, rightIndex, bottomIndex, topIndex);
        }
        else if (topIndex != verticalMidIndex &&
                data.get(verticalMidIndex - 1).get(horizontalMidIndex).compareTo(midElement) >= 0)
        {
            // Top element is bigger, go top.
            return findPeakHelper2D(data,  leftIndex, rightIndex, verticalMidIndex - 1, topIndex);
        }
        else if (bottomIndex != verticalMidIndex &&
                data.get(verticalMidIndex + 1).get(horizontalMidIndex).compareTo(midElement) >= 0)
        {
            // Bottom element is bigger, go bottom.
            return findPeakHelper2D(data, leftIndex, rightIndex, bottomIndex, verticalMidIndex + 1);
        }
        else
        {
            return midElement;
        }
    }

    private static <T extends Comparable> T findPeakHelper3D(ArrayList<ArrayList<ArrayList<T>>> data,
                                                             int xLowIndex, int xHighIndex,
                                                             int yLowIndex, int yHighIndex,
                                                             int zLowIndex, int zHighIndex)
    {
        // If the array is 1 element large, return that element.
        if (xLowIndex == xHighIndex && yLowIndex == yHighIndex && zLowIndex == zHighIndex)
        {
            return data.get(xHighIndex).get(yLowIndex).get(zLowIndex);
        }

        int xMidIndex = (xLowIndex + xHighIndex) / 2;
        int yMidIndex = (yLowIndex + yHighIndex) / 2;
        int zMidIndex = (zLowIndex + zHighIndex) / 2;

        T midElement = data.get(xMidIndex).get(yMidIndex).get(zMidIndex);

        if (xLowIndex != xMidIndex &&
                data.get(xMidIndex - 1).get(yMidIndex).get(zMidIndex).compareTo(midElement) >= 0)
        {
            return findPeakHelper3D(data, xLowIndex, xMidIndex - 1, yLowIndex, yHighIndex,
                    zLowIndex, zHighIndex);
        }
        else if (xHighIndex != xMidIndex &&
                data.get(xMidIndex + 1).get(yMidIndex).get(zMidIndex).compareTo(midElement) >= 0)
        {
            return findPeakHelper3D(data, xMidIndex + 1, xHighIndex, yLowIndex, yHighIndex,
                    zLowIndex, zHighIndex);
        }
        else if (yLowIndex != yMidIndex &&
                data.get(xMidIndex).get(yMidIndex - 1).get(zMidIndex).compareTo(midElement) >= 0)
        {
            return findPeakHelper3D(data, xLowIndex, xHighIndex, yLowIndex, yMidIndex - 1,
                    zLowIndex, zHighIndex);
        }
        else if (yHighIndex != yMidIndex &&
                data.get(xMidIndex).get(yMidIndex + 1).get(zMidIndex).compareTo(midElement) >= 0)
        {
            return findPeakHelper3D(data, xLowIndex, xHighIndex, yMidIndex + 1, yHighIndex,
                    zLowIndex, zHighIndex);
        } else if (zLowIndex != zMidIndex &&
                data.get(xMidIndex).get(yMidIndex).get(zMidIndex - 1).compareTo(midElement) >= 0)
        {
            return findPeakHelper3D(data, xLowIndex, xHighIndex, yLowIndex, yHighIndex,
                    zLowIndex, zMidIndex - 1);
        }
        else if (zHighIndex != zMidIndex &&
                data.get(xMidIndex).get(yMidIndex).get(zMidIndex + 1).compareTo(midElement) >= 0)
        {
            return findPeakHelper3D(data, xLowIndex, xHighIndex, yLowIndex, yHighIndex,
                    zMidIndex + 1, zHighIndex);
        }
        else
        {
            return midElement;
        }
    }
}
