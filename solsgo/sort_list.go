package solsgo

func mergeTwoLists(l1 *ListNode, l2 *ListNode) *ListNode {
	if l1 == nil {
		return l2
	}
	if l2 == nil {
		return l1
	}

	val current *ListNode = nil

	for l1 != nil && l2 != nil {

	}

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
