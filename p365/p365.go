package p365

type state [2]int

var c1, c2 int

type e struct{}

func (s *state) hash() int {
	return s[0]*1000000 + s[1]
}

func min(i, j int) int {
	if i < j {
		return i
	}
	return j
}

func (s *state) finished(target int) bool {
	return s[0] == target || s[1] == target || s[0]+s[1] == target
}

func to1(s *state) state {
	if c1-s[0] > s[1] {
		return [2]int{s[0] + s[1], 0}
	}
	return [2]int{c1, s[1] - (c1 - s[0])}
}

func to2(s *state) state {
	if c2-s[1] > s[0] {
		return [2]int{0, s[0] + s[1]}
	}
	return [2]int{s[0] - (c2 - s[1]), c2}
}

func (s *state) next() []state {
	return []state{
		[2]int{c1, s[1]},
		[2]int{0, s[1]},
		[2]int{s[0], c2},
		[2]int{s[0], 0},
		to1(s),
		to2(s),
	}
}

func canMeasureWater(jug1Capacity int, jug2Capacity int, targetCapacity int) bool {
	closed := make(map[int]e)
	c1, c2 = jug1Capacity, jug2Capacity
	if c1+c2 == targetCapacity {
		return true
	}
	init := [2]int{0, 0}
	stack := make([]state, 0, 10)
	stack = append(stack, init)
	for {
		if len(stack) == 0 {
			return false
		}
		next := stack[len(stack)-1]
		stack = stack[:len(stack)-1]
		if next.finished(targetCapacity) {
			return true
		} else {
			closed[next.hash()] = e{}
		}
		for _, v := range next.next() {
			if _, ok := closed[v.hash()]; !ok {
				stack = append(stack, v)
			}
		}
	}

}
