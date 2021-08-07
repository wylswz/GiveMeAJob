package p93

import (
	"fmt"
	"strconv"
)

func isValid(s string) bool {
	if len(s) > 1 && s[0] == '0' {
		return false
	}
	i, err := strconv.Atoi(s)
	if err != nil {
		return false
	}
	return i >= 0 && i <= 255
}

func shortCircuit(s string, order int) bool {
	if order >= len(s) {
		return true
	}

	if (order+1)*3 < len(s) {
		return true
	}

	return false
}

func doRestor(s string, order int, suffix string, sink func(string)) {
	if shortCircuit(s, order) {
		return
	}
	if order == 0 {
		if isValid(s) {
			sink(fmt.Sprintf("%s.%s", s, suffix))
		}
		return
	} else {
		for i := 0; i < 3; i++ {
			if i >= len(s) {
				continue
			}
			sub := s[len(s)-1-i:]
			if isValid(sub) {
				var newSuffix string
				if len(suffix) == 0 {
					newSuffix = sub
				} else {
					newSuffix = fmt.Sprintf("%s.%s", sub, suffix)
				}
				doRestor(s[:len(s)-1-i], order-1, newSuffix, sink)
			}
		}
	}

}

func restoreIpAddresses(s string) []string {
	res := make([]string, 0)
	doRestor(s, 3, "", func(s string) { res = append(res, s) })
	return res
}
