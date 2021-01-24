package solsgo

import (
	"sort"
	"strings"
)

func removeNthFromEnd(head *ListNode, n int) *ListNode {
	snapshots := make([]*ListNode, 0)
	length := 0
	current := head
	for {
		if current == nil {
			break
		}
		snapshots = append(snapshots, current)
		current = current.Next
		length++
	}
	idx := length - n

	if idx == 0 {
		return head.Next
	}
	if idx == length-1 {
		snapshots[idx-1].Next = nil
		return head
	}
	snapshots[idx-1].Next = snapshots[idx+1]
	return head
}

func (s *StackParen) push(c byte) (bool, int) {
	if c == '(' {
		s.Stack++
		s.Count++
		return true, s.Count
	}

	if c == ')' {
		if s.Stack > 0 {
			s.Stack--
			s.Count++
			return true, s.Count
		} else {
			tmp := s.Count
			s.Count = 0
			return false, tmp
		}
	}

	return false, -1
}

func reverse(s string) string {
	res := make([]byte, len(s))
	for i := range s {
		origin := s[len(s)-1-i]
		if origin == '(' {
			res[i] = ')'
		} else {
			res[i] = '('
		}
	}
	return string(res)
}

func _longestValidParentheses(s string) int {
	sp := StackParen{
		Count: 0,
		Stack: 0,
	}
	accum := make([]int, 0)
	//counter := 0
	for i := range s {
		success, c := sp.push(s[i])
		if success {
			if sp.Stack == 0 {
				accum = append(accum, sp.Count)
			}
		} else {
			accum = append(accum, c)
			//counter = 0
		}
	}

	max := 0

	for _, v := range accum {
		if v > max {
			max = v
		}
	}

	return max
}

func longestValidParentheses(s string) int {
	max_l := _longestValidParentheses(s)
	max_r := _longestValidParentheses(reverse(s))
	if max_l > max_r {
		return max_l
	}
	return max_r
}

// --------------------------------------------------------

func removeElement(nums []int, val int) int {
	offset := 0
	for i, v := range nums {
		if v == val {
			offset--
			continue
		}
		nums[i+offset] = nums[i]
	}

	return len(nums) + offset
}

func longestCommonPrefix(strs []string) string {
	if len(strs) == 0 {
		return ""
	}
	count := 0
	for i := 0; i < len(strs[0]); i++ {
		unified := true
		for _, str := range strs {
			if i >= len(str) {
				unified = false
				break
			} else {
				if str[i] != strs[0][i] {
					unified = false
					break
				}
			}
		}

		if unified {
			count++
		} else {
			break
		}
	}

	return strs[0][:count]
}

func binSearch(nums []int, target int, lo int, hi int) int {
	if len(nums) == 0 {
		return 0
	}
	var idx = 0
	for hi > lo {
		idx = (hi + lo) / 2
		if nums[idx] == target {
			return idx
		}
		if nums[idx] < target {
			lo = idx + 1
		} else {
			hi = idx - 1
		}
	}
	if hi < 0 {
		return -1
	}
	if target == nums[hi] {
		return hi
	}
	// if target > nums[lo] {
	// 	return -lo - 1
	// } else {
	// 	return -lo
	// }
	return -1
}

func search(nums []int, target int) int {
	div := 0
	for i := 0; i < len(nums)-1; i++ {
		if nums[i] > nums[i+1] {
			div = i
		}
	}
	res1 := binSearch(nums, target, 0, div)
	res2 := binSearch(nums, target, div+1, len(nums)-1)

	if res1 < 0 {
		if res2 < 0 {
			return -1
		} else {
			return res2
		}
	} else {
		return res1
	}
}

func key(str string) int {
	s := strings.Split(str, "")
	sort.Strings(s)
	key := 0
	base := 26
	for i := range s {
		key += base * int(s[i][0])
		base = base * 26
	}
	return key
}

func groupAnagrams(strs []string) [][]string {
	anagramMap := make(map[int][]string)
	for _, str := range strs {
		k := key(str)
		_, ok := anagramMap[k]
		if !ok {
			anagramMap[k] = make([]string, 0)
		}
		anagramMap[k] = append(anagramMap[k], str)
	}

	res := make([][]string, 0)
	for k := range anagramMap {
		res = append(res, anagramMap[k])
	}
	return res
}

func jump(nums []int) int {
	if len(nums) == 1 {
		return 0
	}
	queue := make([][]int, 0)
	queue = append(queue, []int{0, 0})
	hist := make(map[int]int)
	for len(queue) > 0 {
		tup := queue[0]
		queue = queue[1:]
		for i := nums[tup[0]]; i >= 1; i-- {
			if tup[0]+i == len(nums)-1 {
				return tup[1] + 1
			}
			v, ok := hist[tup[0]+i]
			if ok && tup[1]+1 > v {
				continue
			} else {

				if tup[0]+i < len(nums) {
					hist[tup[0]+i] = tup[1] + 1
					queue = append(queue, []int{tup[0] + i, tup[1] + 1})
				}
			}
		}
	}
	return -1
}

func minHead(lists []*ListNode) (*ListNode, int) {
	var min *ListNode = nil
	var idx int = -1
	nilCnt := 0
	for i, l := range lists {
		if l == nil {
			nilCnt += 1
			continue
		}
		if min == nil || min.Val > l.Val {
			min = l
			idx = i
		}
	}
	if nilCnt == len(lists) {
		return nil, -1
	}
	return min, idx
}

func mergeKLists(lists []*ListNode) *ListNode {
	var res *ListNode = nil
	var current *ListNode = nil
	for {
		min, minIdx := minHead(lists)
		if min == nil {
			break
		}
		if res == nil {
			res = min
		}
		if current == nil {
			current = min
		} else {
			current.Next = min
			current = current.Next
		}
		lists[minIdx] = lists[minIdx].Next
	}
	return res
}

func strStr(haystack string, needle string) int {
	if len(needle) == 0 {
		return 0
	}
	idx := 0
	for {
		if idx+len(needle) > len(haystack) {
			break
		}
		found := true
		for j := 0; j < len(needle); j++ {
			if needle[j] != haystack[idx+j] {
				found = false
				break
			}
		}

		if found {
			return idx
		}

		idx++
	}
	return -1
}

// func toCoord(i int, j int, len int) (int, int) {
// 	var x int = -1
// 	var y int = -1
// 	if i >= len/2 {
// 		x = i - len/2 + 1
// 	} else {
// 		x = i - len/2
// 	}
// }

func rotate(matrix [][]int) {
	for i := 0; i < len(matrix); i++ {
		for j := 0; j < len(matrix); j++ {

		}
	}
}

func recurser(t *TreeNode, ts *([]*TreeNode)) {
	if t != nil {
		*ts = append(*ts, t)
		recurser(t.Left, ts)
		recurser(t.Right, ts)
	}
}

func flatten(root *TreeNode) {
	ts := make([]*TreeNode, 0)
	recurser(root, &ts)

	for i := 0; i < len(ts)-1; i++ {
		ts[i].Left = nil
		ts[i].Right = ts[i+1]
	}
}

func generateIdx(state []int, idxs [][]int) {
	n := len(state)
	for i := 0; i < n; i++ {
		idxs[i] = []int{state[i], i}
	}
}

func stateKey(state []int) string {
	n := len(state)

	res := make([]byte, n)
	for i := range state {
		res[i] = byte(state[i])
	}
	return string(res)
}

func serialize(idxs [][]int) []string {
	n := len(idxs)
	res := make([]string, n)
	row := make([]byte, n)
	for _, idx := range idxs {
		i := idx[0]
		j := idx[1]

		for k := 0; k < n; k++ {
			if k != j {
				row[k] = '.'
			} else {
				row[k] = 'Q'
			}
		}
		res[i] = string(row)
	}

	return res
}

func swap(nums []int, i int, j int) {
	temp := nums[i]
	nums[i] = nums[j]
	nums[j] = temp
}

func solveNQueens(n int) [][]string {
	res := make([][]string, 0)
	initState := make([]int, n)
	for i := range initState {
		initState[i] = i
	}
	idxs := make([][]int, n)
	closed := make(map[string]bool)
	q := make([][]int, 0)
	q = append(q, initState)

	for len(q) > 0 {
		state := q[0]
		q = q[1:]
		generateIdx(state, idxs)

		legal := true
		closed[stateKey(state)] = true

		for i := 0; i < n; i++ {
			if legal {
				for j := i + 1; j < n; j++ {
					iq1 := idxs[i][0]
					jq1 := idxs[i][1]

					iq2 := idxs[j][0]
					jq2 := idxs[j][1]

					if (iq1-iq2) == (jq1-jq2) || (iq1-iq2) == (jq2-jq1) {
						legal = false
						break
					}

				}
			}

		}
		if legal {
			res = append(res, serialize(idxs))
		}

		for i := 0; i < n; i++ {
			for j := i + 1; j < n; j++ {
				swap(state, i, j)
				k := stateKey(state)
				swap(state, i, j)
				ok := closed[k]
				if !ok {
					newState := make([]int, n)
					copy(newState, state)
					swap(newState, i, j)
					q = append(q, newState)
				}
				closed[k] = true

			}
		}
	}

	return res
}
