package src

// m: Rows
// n: Cols
// start from left upper corner.
// Can only to right or down
// return number of unique paths
func UniquePath(m int, n int) int {

	if m == 1 || n == 1 {
		return 1
	}
	table := make([][]int, m)
	for i, _ := range table {
		table[i] = make([]int, n)
	}
	for i := 0; i < m; i++ {
		table[i][0] = 1
	}
	for i := 0; i < n; i++ {
		table[0][i] = 1
	}
	table[0][0] = 0
	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			table[i][j] = table[i-1][j] + table[i][j-1]
		}
	}

	return table[m-1][n-1]
}
