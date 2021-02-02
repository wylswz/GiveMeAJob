package solsgo

import (
	"fmt"
	"sort"
)

type sortIntSlice []int

func (s sortIntSlice) Len() int {
	return len(s)
}

func (s sortIntSlice) Less(i int, j int) bool {
	return s[i] < s[j]
}

func (s sortIntSlice) Swap(i int, j int) {
	s[i], s[j] = s[j], s[i]
}

func key1(k []int) string {
	return fmt.Sprintf("%v", k)
}

func subsetsWithDup(nums []int) [][]int {
	sort.Sort(sortIntSlice(nums))
	res := make([][]int, 0)
	res = append(res, []int{})
	used := make(map[string]bool)
	pos := 0
	for pos < len(nums) {
		resLen := len(res)
		for i := 0; i < resLen; i++ {
			if pos > 0 && nums[pos-1] == nums[pos] && len(res[i]) < pos {
				continue
			}
			this := make([]int, len(res[i])+1)
			copy(this, res[i])
			this[len(this)-1] = nums[pos]
			k := key1(this)
			_, ok := used[k]
			if !ok {
				res = append(res, this)
				used[k] = true
			}
		}

		pos++
	}
	return res
}
