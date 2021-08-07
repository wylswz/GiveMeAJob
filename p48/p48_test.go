package p48

import "testing"

func TestRotate(t *testing.T) {
	img := [][]int{
		{1, 2, 3},
		{4, 5, 6},
		{7, 8, 9},
	}

	rotate(img)
	print(img)
}
