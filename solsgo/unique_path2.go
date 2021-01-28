package solsgo

func uniquePathsWithObstacles(obstacleGrid [][]int) int {
	if obstacleGrid[0][0] == 1 {
		return 0
	}
	m := len(obstacleGrid)
	n := len(obstacleGrid[0])
	table := make([][]int, m)
	for i := 0; i < m; i++ {
		table[i] = make([]int, n)
	}

	table[0][0] = 1

	for i := 1; i < m; i++ {
		if obstacleGrid[i][0] == 1 {
			table[i][0] = 0
		} else {
			table[i][0] = table[i-1][0]
		}
	}
	for j := 1; j < n; j++ {
		if obstacleGrid[0][j] == 1 {
			table[0][j] = 0
		} else {
			table[0][j] = table[0][j-1]
		}
	}

	for i := 1; i < m; i++ {
		for j := 1; j < n; j++ {
			if obstacleGrid[i][j] != 1 {
				table[i][j] = table[i-1][j] + table[i][j-1]
			} else {
				table[i][j] = 0
			}
		}
	}
	return table[m-1][n-1]

}
