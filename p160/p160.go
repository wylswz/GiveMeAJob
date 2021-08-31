package p160

type ListNode struct {
	Val  int
	Next *ListNode
}

func listLen(l *ListNode) int {
	res := 0
	for l != nil {
		res++
		l = l.Next
	}
	return res
}

func minMax(a, b int) (int, int) {
	if a < b {
		return a, b
	}
	return b, a
}

func getIntersectionNode(headA, headB *ListNode) *ListNode {
	lenA, lenB := listLen(headA), listLen(headB)
	if lenA == 0 || lenB == 0 {
		return nil
	}
	lenMin, lenMax := minMax(lenA, lenB)
	var short, long *ListNode
	if lenA == lenMin {
		short, long = headA, headB
	} else {
		short, long = headB, headA
	}
	for i := 0; i < lenMax-lenMin; i++ {
		long = long.Next
	}
	for short != nil && long != nil {
		if short == long {
			return short
		} else {
			short = short.Next
			long = long.Next
		}
	}
	return nil

}
