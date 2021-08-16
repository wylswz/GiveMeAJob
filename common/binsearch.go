package common

// Return idx or -1
func binSearch(nums []int, target int) int {
	lo, hi := 0, len(nums)-1
	for lo <= hi {
		mid := (lo + hi) / 2
		if nums[mid] == target {
			return mid
		}
		if nums[mid] > target {
			hi = mid - 1
		} else {
			lo = mid + 1
		}
	}

	return -1
}
