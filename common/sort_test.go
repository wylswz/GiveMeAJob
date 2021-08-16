package common

import "testing"

func TestInPlaceMerge(t *testing.T) {
	nums := []int{3, 7, 4, 7, 5, 9, 1, 1, 4, 5, 6, 7, 6, 5}
	QuickSort(nums)
	print(nums)
}
