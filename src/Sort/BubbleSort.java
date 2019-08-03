package Sort;

public class BubbleSort {
    /**
     * Keep scanning with adjacent pointer and swap
     * Until no swap occurs
     * @param nums
     * @return
     */
    public static Integer[] sort(Integer[] nums) {
        if (nums.length <= 1) return nums;

        while(true){
            boolean sorted = true;
            for(int i=0;i<nums.length - 1; i++) {
                if (nums[i+1] < nums[i]) {
                    sorted = false;
                    int tmp = nums[i+1];
                    nums[i+1] = nums[i];
                    nums[i] = tmp;
                }
            }
            if (sorted) {
                break;
            }
        }
        return nums;
    }
}
