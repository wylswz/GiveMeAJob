package p47

var closed map[int]bool

func hash(nums []int) int {
	res := 0
	ratio := 1
	for _, v := range nums {
		res += v * ratio
		ratio *= 10
	}
	return res
}

func tryPermutate(nums []int, i int, j int) int {
	nums[i], nums[j] = nums[j], nums[i]
	res := hash(nums)
	nums[i], nums[j] = nums[j], nums[i]
	return res
}

func doPermutation(nums []int, i int, j int) []int {
	res := make([]int, len(nums))
	for idx := 0; idx < len(nums); idx++ {
		if idx == i {
			res[idx] = nums[j]
			continue
		}
		if idx == j {
			res[idx] = nums[i]
			continue
		}

		res[idx] = nums[idx]
	}
	return res
}

func walk(nums []int, sink func([]int)) {
	for i := 0; i < len(nums); i++ {
		for j := i; j < len(nums); j++ {
			afterPermu := tryPermutate(nums, i, j)
			_, ok := closed[afterPermu]
			if ok {
				continue
			}
			nextPerm := doPermutation(nums, i, j)
			closed[hash(nextPerm)] = true
			sink(nextPerm)
			walk(nextPerm, sink)
		}
	}
}

func permuteUnique(nums []int) [][]int {
	closed = make(map[int]bool)
	res := make([][]int, 0)
	if len(nums) == 1 {
		res = append(res, nums)
		return res
	}

	walk(nums, func(i []int) { res = append(res, i) })

	return res
}
