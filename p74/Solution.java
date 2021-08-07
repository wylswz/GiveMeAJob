package p74;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;

class HeteroSearch<T, U> {
    private final List<T> src;
    private final U tgt;
    private final BiPredicate<T, U> greater;
    private final BiPredicate<T, U> eq;
    private final BiPredicate<T, U> less;
    
    public HeteroSearch(
        List<T> src, 
        U tgt,
        BiPredicate<T, U> greater,
        BiPredicate<T, U> eq,
        BiPredicate<T, U> less
        ) {
            this.src = src;
            this.tgt = tgt;
            this.less = less;
            this.eq = eq;
            this.greater = greater;
    }
    
    private int doBinSearch() {
        int lo = 0;
        int hi = src.size() - 1;
        hi = hi>=0?hi:0;
        while (lo < hi - 1) {
            int pivot = (lo + hi) / 2;
            if (eq.test(src.get(pivot), tgt)) {
                return pivot;
            } else if (greater.test(src.get(pivot), tgt)) {
                hi = pivot;
                continue;
            } else if (less.test(src.get(pivot), tgt)) {
                lo = pivot;
                continue;
            }

        }
        if (eq.test(src.get(lo), tgt)) {
            return lo;
        }
        if (eq.test(src.get(hi), tgt)) {
            return hi;
        }
        return -1;
    }

    public int search() {
        return doBinSearch();
    }

}




public class Solution {


    public boolean searchMatrix(int[][] matrix, int target) {
        List<int[]> lst = Arrays.asList(matrix);
        int idx = new HeteroSearch<int[], Integer>(
                lst, 
                target, 
                (l, e) -> l[0] > e, 
                (l, e) -> l[0] <= e && l[l.length-1]>=e, 
                (l, e) -> l[l.length-1] < e
                ).search();
        if (idx < 0) {
            return false;
        }
        return Arrays.binarySearch(matrix[idx], target) >=0 ;
    }
    

    public static void main(String[] args) {
        int[][] arr = new int[][] {
            new int[] {1,2,3,4,5},
            new int[] {6,7,8,9,10},
            new int[] {11,12,13,17,19},
        };
        System.out.println(new Solution().searchMatrix(arr, 3));
    }
}