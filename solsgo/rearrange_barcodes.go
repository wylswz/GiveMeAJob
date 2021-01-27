package solsgo

import "sort"

type byLen [][]int

func (l byLen) Len() int {
	return len(l)
}

func (l byLen) Less(i, j int) bool {
	return len(l[i]) > len(l[j])
}

func (l byLen) Swap(i, j int) {
	l[i], l[j] = l[j], l[i]
}

func preSort(barcodes []int) []int {
	aggregated := make(map[int][]int)
	for _, c := range barcodes {
		_, ok := aggregated[c]
		if !ok {
			aggregated[c] = make([]int, 0)
		}
		aggregated[c] = append(aggregated[c], c)
	}
	ll := make([][]int, 0)
	for k := range aggregated {
		ll = append(ll, aggregated[k])
	}
	sort.Sort(byLen(ll))
	sorted := make([]int, 0)
	for _, l := range ll {
		sorted = append(sorted, l...)
	}
	return sorted
}

func rearrangeBarcodes(barcodes []int) []int {
	sorted := preSort(barcodes)
	res := make([]int, len(sorted))
	idxCon := 0
	n := len(barcodes)
	for i := range sorted {

		for {
			if idxCon == 0 {
				if res[idxCon] > 0 {
					idxCon = (idxCon + 1) % n
				} else {
					res[idxCon] = sorted[i]
					break
				}
			}

			if sorted[i] == res[idxCon-1] || res[idxCon] > 0 {
				idxCon = (idxCon + 1) % n

			} else {
				res[idxCon] = sorted[i]
				break
			}

		}
	}
	return res

}
