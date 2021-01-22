fun swap(nums: IntArray, from: Int, to: Int) {
    val temp = nums[from]
    nums[from] = nums[to]
    nums[to] = temp
}

fun partition(nums: IntArray, lo: Int, hi: Int): Int {
    val pivot = nums[hi]
    var i = lo
    for (j in lo..hi) {
        if (nums[j] < pivot) {
            swap(nums, i, j)
            i++
        }
    }
    swap(nums, i, hi)
    return i
}

val nums = intArrayOf(4,6,8,3,2,5,7)
val res = partition(nums, 0, nums.size-1)
println(res)
for (n in nums) print("${n}, ")
