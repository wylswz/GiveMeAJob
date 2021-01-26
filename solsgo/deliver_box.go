package solsgo

import "math"

func boxDelivering(boxes [][]int, portsCount int, maxBoxes int, maxWeight int) int {
	nBox := len(boxes)
	table := make([]int, nBox)
	mins := make([]int, nBox)
	for i := range mins {
		mins[i] = math.MaxInt32
	}
	currentDelivered := -1
	for {
		if currentDelivered >= nBox {
			break
		}
		if currentDelivered >= 0 {
			table[currentDelivered] = mins[currentDelivered]
		}
		b := 0
		w := 0
		for j := currentDelivered + 1; j < nBox; j++ {

			if boxes[j][1] <= w && b > 0 {
				if boxes[j][0] == boxes[j-1][0] {
					table[j] = table[j-1]

				} else {
					table[j] = table[j-1] + 1
				}

			} else {

				if j == 0 {
					table[j] = 2
				} else {
					table[j] = table[j-1] + 2
				}

				b = maxBoxes
				w = maxWeight
			}
			b--
			w -= boxes[j][1]

			if table[j] < mins[j] {
				mins[j] = table[j]
			}
		}
		currentDelivered++
	}
	res := mins[nBox-1]
	return res
}
