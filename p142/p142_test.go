package p142

import (
	"fmt"
	"strconv"
	"testing"
	"unsafe"
)

func TestBitch(t *testing.T) {

	b := &ListNode{}
	ptr := fmt.Sprint(unsafe.Pointer(b))
	base := &bitmap{
		data: make([]uint64, 3126),
	}
	n, err := strconv.ParseUint(ptr, 0, 64)
	print(base.get(n), err)
}
