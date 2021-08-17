package common

func inPlaceMerge(nums []int, i1, i2, end int) {
	for i1 <= end && i2 <= end {
		if nums[i1] < nums[i2] {
			i1++
			continue
		}
		tmp := nums[i2]
		for j := i2; j > i1; j-- {
			nums[j] = nums[j-1]
		}
		nums[i1] = tmp
		i1++
		i2++
	}
}

func doMergeSort(nums []int, start, end int) {
	if start >= end {
		return
	}
	mid := (start + end) / 2
	doMergeSort(nums, start, mid)
	doMergeSort(nums, mid+1, end)
	inPlaceMerge(nums, start, mid+1, end)
}

func MergeSort(nums []int) {
	doMergeSort(nums, 0, len(nums)-1)
}

// ---

func selection(nums []int, lo, hi int) int {
	i := lo - 1
	pivot := nums[hi]
	for j := lo; j < hi; j++ {
		if nums[j] < pivot {
			i++
			nums[i], nums[j] = nums[j], nums[i]
		}
	}
	nums[i+1], nums[hi] = nums[hi], nums[i+1]
	return i + 1
}

func doQuickSort(nums []int, lo, hi int) {
	if lo >= hi {
		return
	}
	p := selection(nums, lo, hi)
	doQuickSort(nums, lo, p-1)
	doQuickSort(nums, p+1, hi)
}

func QuickSort(nums []int) {
	doQuickSort(nums, 0, len(nums)-1)
}
