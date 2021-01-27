package solsgo

func longestConsecutive(nums []int) int {
	records := make(map[int]int)
	for i, num := range nums {
		records[num] = i
	}

	inBound := make([]int, len(nums))
	outBound := make([]int, len(nums))
	for i := range inBound {
		inBound[i] = -1
		outBound[i] = -1
	}

	for i, num := range nums {
		idx, ok := records[num-1]
		if ok {
			inBound[i] = idx
		}

		idx, ok = records[num+1]
		if ok {
			outBound[i] = idx
		}
	}

	max := 0
	starts := make([]int, 0)
	for i := range inBound {
		if inBound[i] < 0 {
			starts = append(starts, i)
		}
	}

	for i := range starts {
		localMax := 1
		current := starts[i]
		for outBound[current] >= 0 {
			localMax++
			current = outBound[current]
		}

		if localMax > max {
			max = localMax
		}
	}

	return max
}
