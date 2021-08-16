package p150

import "testing"

func TestBitch(t *testing.T) {
	token := []string{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}
	res := evalRPN(token)
	print(res)
}
