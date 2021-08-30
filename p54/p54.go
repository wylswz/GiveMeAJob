package p54

type state struct {
	matrix [][]int
	state  int
	bound  *[4]int
	x, y   int
}

func (s *state) getNext() int {
	currentState := s.state
	nextState := (s.state + 1) & 3
	if currentState == 0 {
		if s.y <= (*s.bound)[currentState] {
			res := s.matrix[s.x][s.y]
			s.y++
			return res
		} else {
			s.y--
			s.x++
			(*s.bound)[s.state]--
			s.state = nextState
			return s.getNext()
		}
	} else if currentState == 1 {
		if s.x <= (*s.bound)[currentState] {
			res := s.matrix[s.x][s.y]
			s.x++
			return res
		} else {
			s.x--
			s.y--
			(*s.bound)[s.state]--
			s.state = nextState
			return s.getNext()
		}
	} else if currentState == 2 {
		if s.y >= (*s.bound)[currentState] {
			res := s.matrix[s.x][s.y]
			s.y--
			return res
		} else {
			s.y++
			s.x--
			(*s.bound)[s.state]++
			s.state = nextState
			return s.getNext()
		}
	} else {
		if s.x >= (*s.bound)[currentState] {
			res := s.matrix[s.x][s.y]
			s.x--
			return res
		} else {
			s.x++
			s.y++
			(*s.bound)[s.state]++
			s.state = nextState
			return s.getNext()
		}
	}
}

func spiralOrder(matrix [][]int) []int {
	bound := [4]int{len(matrix[0]) - 1, len(matrix) - 1, 0, 1}
	if len(matrix) == 0 || len(matrix[0]) == 0 {
		return []int{}
	}
	size := len(matrix) * len(matrix[0])
	res := make([]int, 0, size)
	gameState := state{
		matrix: matrix,
		state:  0,
		bound:  &bound,
		x:      0,
		y:      0,
	}
	for i := 0; i < size; i++ {
		res = append(res, gameState.getNext())
	}
	return res
}
