package Search;

import java.util.ArrayList;

public class BinSearch {

    public static int search(Comparable[] srcArray, Comparable target) {
        final int length = srcArray.length;
        int midIndex = length/2;
        int from = 0;
        int to = length - 1;
        while (from <= to) {
            if (target.equals(srcArray[midIndex])) {
                return midIndex;
            } else if (target.compareTo(srcArray[midIndex]) < 0) {
                // target less than mid elem
                to = midIndex - 1;
                midIndex = (from + to)/2;

            } else {
                from = midIndex + 1;
                midIndex = (from + to)/2;
            }
        }

        return -1;
    }
}
