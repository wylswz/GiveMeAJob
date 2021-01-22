package src

func _fact(n int, cum int) int {
	if n == 1 || n == 0 {
		return cum
	}
	return _fact(n-1, cum*n)
}

func fact(n int) int {
	return _fact(n, 1)
}

func mod(n int, m int) int {
	if n < 0 {
		return mod(n+m, m)
	} else {
		return n % m
	}
}

func permute(nums []int) [][]int {
	res := make([][]int, fact(len(nums)))
	m := len(nums)
	for i := range res {
		idxs := make([]int, m)
		res[i] = make([]int, m)
		for j := range idxs {

			if j == 0 {
				idxs[j] = mod(i, m)
			} else {
				idxs[j] = mod(i, m) + (j + i/m)

			}
			idxs[j] = mod(idxs[j], m)

			res[i][j] = nums[idxs[j]]
		}

	}
	return res
}
