package p230

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var acc, res *int

func traverse(tree *TreeNode) {
	if tree == nil {
		return
	}
	traverse(tree.Left)
	if res != nil {
		return
	}
	if *acc == 1 {
		res = &tree.Val
		return
	}
	*acc--
	traverse(tree.Right)
}

func kthSmallest(root *TreeNode, k int) int {
	acc = &k
	res = nil
	traverse(root)
	return *res
}
