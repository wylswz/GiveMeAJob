import java.util.*

fun swap(nums: IntArray, from: Int, to: Int) {
    val temp = nums[from]
    nums[from] = nums[to]
    nums[to] = temp
}

fun sort(nums: IntArray, from: Int) {
    var sorted = false
    while (!sorted) {
        var swapped = false
        for (i in from until nums.size-1) {
            if (nums[i] > nums[i+1]) {
                swapped = true
                swap(nums, i, i+1)
            }
        }
        if (!swapped) {
            sorted = true
        }
    }
}

fun nextPermutation(nums: IntArray): Unit {
    var from = 0
    for (i in 0 until nums.size - 1) {
        val idx = nums.size - i - 1
        val idxNxt = idx - 1

        if (nums[idx] > nums[idxNxt]) {
            from = idx
            swap(nums, idx, idxNxt)
            if (idx < nums.size - 1) {
                for (j in idx+1 until nums.size) {
                    if (nums[idxNxt] > nums[j] && nums[idx]<nums[j])
                        swap(nums, j, idxNxt)
                }
            }
            break
        }
    }
    // Step 1: swap

    sort(nums, from)
    // Step 2: sort
}

var nums = intArrayOf(2,3,1)
nextPermutation(nums)

for (n in nums) print("${n}, ")