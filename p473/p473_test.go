package p473

import "testing"

func TestFuck(t *testing.T) {
	res := makesquare([]int{3, 3, 3, 5, 4, 4, 4, 4, 3, 5, 5, 5})
	print(res)

}

func TestBitch(t *testing.T) {
	a := 0

	a |= 1 << 1
	print(a)
}
