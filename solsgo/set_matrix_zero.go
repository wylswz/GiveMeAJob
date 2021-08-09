package solsgo

func setZeroes(matrix [][]int) {
	rowSet := make(map[int]bool)
	colSet := make(map[int]bool)

	for i := range matrix {
		for j := range matrix[0] {
			if matrix[i][j] == 0 {
				rowSet[i] = true
				colSet[j] = true
			}
		}
	}

	for i := range matrix {
		for j := range matrix[0] {
			_, ok := rowSet[i]
			if ok {
				matrix[i][j] = 0
			}

			_, ok = colSet[j]
			if ok {
				matrix[i][j] = 0
			}

		}
	}

}
