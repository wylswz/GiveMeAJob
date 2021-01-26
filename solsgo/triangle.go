package solsgo

func triangleRecurser(triangle [][]int, coord []int) int {
	if coord[0] == len(triangle)-1 {
		return triangle[coord[0]][coord[1]]
	}

	return triangle[coord[0]][coord[1]] + triangleMin(
		triangleRecurser(triangle, []int{coord[0] + 1, coord[1]}),
		triangleRecurser(triangle, []int{coord[0] + 1, coord[1] + 1}),
	)
}

func triangleMin(a int, b int) int {
	if a < b {
		return a
	}
	return b
}

func minimumTotal(triangle [][]int) int {
	for i := len(triangle) - 2; i >= 0; i-- {
		for j := range triangle[i] {
			triangle[i][j] = triangleMin(
				triangle[i+1][j],
				triangle[i+1][j+1],
			) + triangle[i][j]
		}
	}
	return triangle[0][0]
}
