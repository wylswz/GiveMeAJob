package p38

import "fmt"

var numMap = map[byte]int{
	'1': 1, '2': 2, '3': 3, '4': 4, '5': 5, '6': 6, '7': 7, '8': 8, '9': 9, '0': 0,
}

func doCount(n int) int {
	s := fmt.Sprint(n)
	var current byte = 255
	cnt := 0
	res []byte = make(type, 0)
	for i := 0; i < len(s); i++ {
		if cnt == 0 {
			current = s[i]
			cnt = 1
			continue
		}
		if s[i] == current {
			cnt++
		} else {
			current = s[i]
			cnt = 1
		}
	}
}

func countAndSay(n int) string {
	if n == 1 {
		return "1"
	}
	return ""
}
