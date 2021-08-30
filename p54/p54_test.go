package p54

import "testing"

func TestFuck(t *testing.T) {
	matrix := [][]int{
		{1, 2, 3},
		{4, 5, 6},
		{7, 8, 9},
	}
	print(spiralOrder(matrix))
}
