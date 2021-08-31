package p189

func doRotate(nums []int, k int, size int) {
	k = k % size
	if k == 0 {
		return
	}

	for i := 0; i < k; i++ {
		nums[i], nums[size-k+i] = nums[size-k+i], nums[i]
	}
	doRotate(nums[k:], k, size-k)
}

func rotate(nums []int, k int) {
	s := len(nums)
	if k > s {
		k = k % s
	}
	if k > s/2 {
		doRotate(nums, k/2, s)
		doRotate(nums, k-k/2, s)
	} else {
		doRotate(nums, k, s)
	}
}
