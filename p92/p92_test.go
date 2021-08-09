package p92

import "testing"

func TestShit(t *testing.T) {
	lst := &ListNode{
		Val: 1,
		Next: &ListNode{
			Val: 2,
			Next: &ListNode{
				Val: 3,
			},
		},
	}

	res := reverseBetween(lst, 1, 2)
	print(res)
}
