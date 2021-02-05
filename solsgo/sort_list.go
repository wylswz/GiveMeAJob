package solsgo

func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
	if l1 == nil {
		return l2
	}
	if l2 == nil {
		return l1
	}

	var holder *ListNode = &ListNode{}
	var current = holder

	for l1 != nil && l2 != nil {
		if l1.Val < l2.Val {
			current.Next = l1
			l1 = l1.Next
		} else {
			current.Next = l2
			l2 = l2.Next
		}
		current = current.Next
	}

	if l1 == nil {
		current.Next = l2
	}

	if l2 == nil {
		current.Next = l1
	}

	return holder.Next
}

func sortList(head *ListNode) *ListNode {
	if head == nil || head.Next == nil {
		return head
	}
	var left *ListNode = nil
	var right *ListNode = nil
	isRight := true

	for head != nil {
		newHead := head.Next
		if isRight {
			head.Next = right
			right = head
		} else {
			head.Next = left
			left = head
		}
		head = newHead
		isRight = !isRight
	}
	sr := sortList(right)
	sl := sortList(left)
	return mergeTwoLists(sr, sl)
}
