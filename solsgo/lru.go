package solsgo

import "container/list"

type LRUCache struct {
	ll   *list.List
	vals map[int]int
	size int
}

func Constructor(capacity int) LRUCache {
	return LRUCache{
		ll:   list.New(),
		vals: make(map[int]int),
		size: capacity,
	}
}

func (this *LRUCache) Get(key int) int {
	val, ok := this.vals[key]
	if !ok {
		return -1
	}
	return val
}

func (this *LRUCache) Put(key int, value int) {
	
}
