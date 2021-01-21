fun searchRange(nums: IntArray, target: Int): IntArray {

    val i = nums.binarySearch(target)
    if (i < 0)
        return intArrayOf(-1, -1)

    var from = i
    var to = i
    while (nums[from] == nums[i]) {
        from --
        if (from < 0)
            break
    }
    from ++

    while (nums[to] == nums[i]) {
        to ++
        if (to >= nums.size)
            break
    }
    to --

    return intArrayOf(from, to)
}
val res = searchRange(intArrayOf(6),6)
println("${res[0]}, ${res[1]}")