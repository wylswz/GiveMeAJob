package p189

import "testing"

func TestFuck(t *testing.T) {
	a := []int{1, 2, 3, 4, 5}
	rotate(a, 1)
	print(a)
}
