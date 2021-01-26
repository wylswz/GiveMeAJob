package solsgo

func dfs(board [][]byte, m int, n int, word string, idx int, used map[int]bool) bool {
	k := m*200 + n
	_, ok := used[k]
	if ok {
		return false
	}

	if m < 0 || m >= len(board) {
		return false
	}
	if n < 0 || n >= len(board[0]) {
		return false
	}
	if idx == len(word)-1 {
		return word[idx] == board[m][n]
	}

	used[k] = true

	if board[m][n] == word[idx] {

		res := dfs(board, m+1, n, word, idx+1, used) ||
			dfs(board, m, n+1, word, idx+1, used) ||
			dfs(board, m-1, n, word, idx+1, used) ||
			dfs(board, m, n-1, word, idx+1, used)
		delete(used, k)
		return res
	}
	delete(used, k)
	return false
}

func exist(board [][]byte, word string) bool {
	used := make(map[int]bool)
	for i := range board {
		for j := range board[0] {
			if board[i][j] == word[0] {
				if dfs(board, i, j, word[1:], 1, used) {
					return true
				}
			}
		}
	}
	return false
}
