package p92

type ListNode struct {
	Val  int
	Next *ListNode
}

func reverseBetween(head *ListNode, left int, right int) *ListNode {
	if head == nil || head.Next == nil || left == right {
		return head
	}
	stack := make([]*ListNode, 0, 16)

	current := head
	for current != nil {
		stack = append(stack, current)
		current = current.Next
	}
	if left > 1 {
		stack[left-2].Next = stack[right-1]
	}

	if right < len(stack) {
		stack[left-1].Next = stack[right]
	} else {
		stack[left-1].Next = nil
	}
	for i := right - 1; i >= left; i-- {
		stack[i].Next = stack[i-1]
	}

	if left == 1 {
		return stack[right-1]
	}
	return stack[0]
}
