package p284

type Iterator struct {
}

func (this *Iterator) hasNext() bool {
	return true
}

func (this *Iterator) next() int {
	return 0
}

// implementations

type PeekingIterator struct {
	current *int
	iter    *Iterator
}

func Constructor(iter *Iterator) *PeekingIterator {
	return &PeekingIterator{
		iter: iter,
	}
}

func (this *PeekingIterator) hasNext() bool {
	return this.iter.hasNext() || this.current != nil
}

func (this *PeekingIterator) next() int {
	if this.current != nil {
		res := *this.current
		this.current = nil
		return res
	}
	return this.iter.next()
}

func (this *PeekingIterator) peek() int {
	if this.current != nil {
	} else {
		nxt := this.iter.next()
		this.current = &nxt
	}
	return *this.current
}
