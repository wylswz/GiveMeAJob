package p473

import "sort"

type arr []int

var edgeSize int

func (a arr) Len() int {
	return len(a)
}

func (a arr) Less(i, j int) bool {
	return a[i] > a[j]
}

func (a arr) Swap(i, j int) {
	a[i], a[j] = a[j], a[i]
}

type state struct {
	sticks *[]int
	bitMap uint16
	a      int
	b      int
	c      int
	d      int
}

func (s *state) get(i int) bool {
	return (1 << i & s.bitMap) != 0
}

func (s *state) set(i int) {
	s.bitMap = s.bitMap | (1 << i)
}

func (s *state) hash() uint32 {
	comp := 0
	if s.a == edgeSize {
		comp++
	}
	if s.b == edgeSize {
		comp++
	}
	if s.c == edgeSize {
		comp++
	}
	if s.d == edgeSize {
		comp++
	}
	return uint32(uint32(s.bitMap)<<16 + uint32(comp))
}

func (s *state) len(i int) int {
	return (*s.sticks)[i]
}

func (s *state) isNextStateValid(i int) bool {
	if s.get(i) {
		return false
	}
	t := edgeSize - s.len(i)
	return s.a <= t || s.b <= t || s.c <= t || s.d <= t
}

func (s state) nextState(i int) state {
	newState := state{
		sticks: s.sticks,
		bitMap: s.bitMap,
		a:      s.a,
		b:      s.b,
		c:      s.c,
		d:      s.d,
	}
	if newState.a+s.len(i) <= edgeSize {
		newState.a += s.len(i)
		newState.set(i)
	} else if newState.b+s.len(i) <= edgeSize {
		newState.b += s.len(i)
		newState.set(i)
	} else if newState.c+s.len(i) <= edgeSize {
		newState.c += s.len(i)
		newState.set(i)
	} else if newState.d+s.len(i) <= edgeSize {
		newState.d += s.len(i)
		newState.set(i)
	}

	return newState
}

func (s state) nextStates() []state {
	res := make([]state, 0, 16)
	for i := 0; i < len(*s.sticks); i++ {
		if s.isNextStateValid(i) {
			res = append(res, s.nextState(i))
		}
	}
	return res
}

func (s state) isCompleted() bool {
	return s.a == edgeSize && s.b == edgeSize && s.c == edgeSize && s.d == edgeSize
}

// Constrained knapsack
func makesquare(matchsticks []int) bool {
	sort.Sort(arr(matchsticks))
	totalLen := 0
	for _, s := range matchsticks {
		totalLen += s
	}

	if totalLen%4 != 0 {
		return false
	}

	edgeSize = totalLen / 4

	initState := state{
		sticks: &matchsticks,
		bitMap: 0,
	}

	queue := make([]state, 0, len(matchsticks)*len(matchsticks))
	queue = append(queue, initState)
	completed := false
	closed := make(map[uint32]bool)
	for {
		if len(queue) == 0 {
			break
		}
		next := queue[len(queue)-1]
		queue = queue[:len(queue)-1]
		if next.isCompleted() {
			return true
		}
		for _, s := range next.nextStates() {
			_, ok := closed[s.hash()]
			if !ok {
				queue = append(queue, s)
			}
		}
		closed[next.hash()] = true
	}
	return completed
}
