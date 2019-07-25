package Sort;

public class QuickSort {

    public static void swap(int [] array, int from, int to) {
        int temp = array[from];
        array[from] = array[to];
        array[to] = temp;
    }

    public static int partition(int [] array, int lo, int hi) {

        int pivot = array[lo + (hi-lo)/2];
        int i = lo;
        int j = hi;
        while(true){

            while(array[i] < pivot) {
                i++;
            }

            while(array[j] > pivot) {
                j--;
            }

            if (i>=j) {
                return j;
            }
            swap(array, i, j);

            i++;
            j--;
        }
    }

    public static void  printArray(int[] array) {
        for (int elem : array) {
            System.out.print(elem + ", ");
        }
        System.out.println("");
    }
    public static int[] sort(int [] array, int lo, int hi) {
        if (lo < hi) {
            int p = partition(array, lo, hi);
            sort(array, lo, p);
            sort(array, p+1, hi);
        }

        return array;
    }

    public static int kthSmallest(int [] array, int lo, int hi, int k) {
        // Find kth smallest element in an array
        int pivot = partition(array,lo, hi);
        if (pivot + 1 == k) {
            return array[pivot];
        }

        if (pivot +1 > k) {
            hi = pivot - 1;
        }

        if (pivot +1 < k) {
            k = k - pivot;
            lo = pivot + 1;
        }

        return kthSmallest(array, lo, hi, k);
    }

    public static void main(String [] args) {
        printArray(sort(new int[]{5,4,3,2,1},0,4));
        System.out.println(kthSmallest(new int[]{1,2,3,4,5},0,4,2));
    }
}
