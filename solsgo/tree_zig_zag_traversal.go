package solsgo

func zigZagRecurser(root *TreeNode, level int, left bool, res *[][]int) {

	if root != nil {
		if level > len(*res)-1 {
			row := make([]int, 0)
			*res = append(*res, row)
		}
		if left {
			(*res)[level] = append((*res)[level], root.Val)
		} else {
			(*res)[level] = append([]int{root.Val}, (*res)[level]...)
		}

		// right then left
		zigZagRecurser(root.Left, level+1, !left, res)
		zigZagRecurser(root.Right, level+1, !left, res)

	}

}

func zigzagLevelOrder(root *TreeNode) [][]int {
	res := make([][]int, 0)
	zigZagRecurser(root, 0, true, &res)
	return res
}
