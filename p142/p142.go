package p142

import (
	"fmt"
	"strconv"
	"unsafe"
)

type ListNode struct {
	Val  int
	Next *ListNode
}

func detectCycle(head *ListNode) *ListNode {
	base := uint64(0x00000000)
	current := head
	for {
		if current == nil {
			return nil
		}
		offset, err := strconv.ParseUint(fmt.Sprint(unsafe.Pointer(current)), 0, 64)
		if err != nil {
			panic(err)
		}
		if base|offset == base {
			return current
		} else {
			base |= offset
		}

		current = current.Next
	}

}
