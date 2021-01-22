package src

import "log"

type CNode struct {
	Candidates []int
	Taken      []int
	Target     int
}

func (c CNode) Decendents() []CNode {
	res := make([]CNode, 0)
	for _, candidate := range c.Candidates {
		if c.Target-candidate >= 0 {
			if len(c.Taken) > 0 && candidate < c.Taken[len(c.Taken)-1] {
				continue
			} else {
				taken := make([]int, len(c.Taken)+1)
				copy(taken, c.Taken)
				taken[len(c.Taken)] = candidate
				next := CNode{
					Candidates: c.Candidates,
					Taken:      taken,
					Target:     c.Target - candidate,
				}
				log.Println(c, c.Taken, candidate, next)

				res = append(res, next)
			}

		}
	}
	return res
}

func Traverse(c CNode, res *[][]int) {

	if c.Target == 0 {

		new := c.Taken
		*res = append(*res, new)
		return
	}

	for _, d := range c.Decendents() {
		Traverse(d, res)
	}
}

func isSorted(l []int) bool {
	for i := 0; i < len(l)-1; i++ {
		if l[i] > l[i+1] {
			return false
		}
	}
	return true
}

func CombinationSum(candidates []int, target int) [][]int {
	res := make([][]int, 0)
	c := CNode{
		Candidates: candidates,
		Taken:      []int{},
		Target:     target,
	}

	Traverse(c, &res)
	filted := make([][]int, len(res))
	count := 0

	for _, r := range res {
		if isSorted(r) {
			filted[count] = r
			count++
		}
	}
	return filted[:count]
}
