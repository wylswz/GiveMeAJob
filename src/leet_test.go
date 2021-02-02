package src

import (
	"log"
	"testing"
)

func TestLeet(t *testing.T) {
	log.Println(UniquePath(3, 7))
}

func TestCombinationSum(t *testing.T) {
	r := CombinationSum([]int{2, 7, 6, 3, 5, 1}, 7)
	log.Println(r)
}

func TestCopy(t *testing.T) {
	a := make([]int, 3)
	copy(a, []int{1, 2})
	log.Println(a)
	copy(a, []int{3, 4})
	log.Println(a)
}

func TestFact(t *testing.T) {
	log.Println(2 ^ 2)
}

func TestPermute(t *testing.T) {
	log.Println(permute([]int{1, 2, 3, 4}))
}
