package p48

func rotate(matrix [][]int) {
	if len(matrix) == 1 {
		return
	}
	n := len(matrix)
	var iMax, jMax int

	iMax, jMax = n/2, n/2+n%2

	for i := 0; i < iMax; i++ {
		for j := 0; j < jMax; j++ {
			new_i, new_j := j, n-1-i
			matrix[i][j], matrix[new_i][new_j] = matrix[new_i][new_j], matrix[i][j]
			new_i, new_j = new_j, n-1-new_i
			matrix[i][j], matrix[new_i][new_j] = matrix[new_i][new_j], matrix[i][j]
			new_i, new_j = new_j, n-1-new_i
			matrix[i][j], matrix[new_i][new_j] = matrix[new_i][new_j], matrix[i][j]
			new_i, new_j = new_j, n-1-new_i
			matrix[i][j], matrix[new_i][new_j] = matrix[new_i][new_j], matrix[i][j]
		}
	}
}
