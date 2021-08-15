package p199

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

var res = make([]int, 0)

func inOrd(tree *TreeNode, level int) {
	if tree == nil {
		return
	}
	if len(res) < level+1 {
		res = append(res, tree.Val)
	} else {
		res[level] = tree.Val
	}
	inOrd(tree.Left, level+1)
	inOrd(tree.Right, level+1)
}

func rightSideView(root *TreeNode) []int {

	if root == nil {
		return res
	}
	inOrd(root, 0)
	return res

}
