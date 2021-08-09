package sols

func binSearch(nums []int, target int, from int, to int) bool {
	var middle int
	if len(nums) == 0 {
		return false
	}

	for from < to {
		middle = (from + to) / 2
		if target == nums[middle] {
			return true
		}
		if nums[middle] < target {
			from = middle + 1
			continue
		}
		if nums[middle] > target {
			to = middle - 1
			continue
		}
	}

	if from == to {
		return nums[from] == target
	}

	return false
}

func _search(nums []int, target int, from int, to int) bool {
	var middle int

	if len(nums) == 0 {
		return false
	}

	if from == to {
		return nums[from] == target
	}

	if from > to {
		return false
	}

	middle = (from + to) / 2
	if nums[middle] == target {
		return true
	}
	if nums[middle] < nums[from] {
		// second half should be correct
		return binSearch(nums, target, middle, to) || _search(nums, target, from, middle)
	}
	if nums[middle] > nums[to] {
		// first half should be correct
		return binSearch(nums, target, from, middle) || _search(nums, target, middle, to)
	}
	return false
}

func search(nums []int, target int) bool {
	return _search(nums, target, 0, len(nums)-1)
}
