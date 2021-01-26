package solsgo

import (
	"fmt"
	"testing"
)

func TestLongestValidParenthesis(t *testing.T) {
	par := "()"
	fmt.Println(longestValidParentheses(par))
}

func TestRemovel(t *testing.T) {
	a := []int{1, 2, 3, 4, 3, 3, 5}
	l := removeElement(a, 3)
	fmt.Println(a, l)
}

func TestLongestCommonPrefix(t *testing.T) {
	a := longestCommonPrefix([]string{"cir", "car"})
	fmt.Println(a)
}

func TestBinSearch(t *testing.T) {
	idx := search([]int{3, 5, 1}, 0)
	fmt.Println(idx)
}

func TestJump(t *testing.T) {
	fmt.Println(jump([]int{2, 3, 1, 1, 4}))
}

func TestStrStr(t *testing.T) {
	fmt.Println(strStr("asd", "sd"))
}

func TestNq(t *testing.T) {
	fmt.Println(solveNQueens(9))
}

func TestDelivery(t *testing.T) {
	d := [][]int{
		[]int{2, 4},
		[]int{2, 5},
		[]int{3, 1},
		[]int{3, 2},
		[]int{3, 7},
		[]int{3, 1},
		[]int{4, 4},
		[]int{1, 3},
		[]int{5, 2},
	}

	fmt.Println(boxDelivering(d, 5, 5, 7))
}

func TestExist(t *testing.T) {
	board := [][]byte{
		[]byte{'a', 'b'},
		[]byte{'c', 'd'},
	}

	fmt.Println(exist(board, "bacd"))
}
