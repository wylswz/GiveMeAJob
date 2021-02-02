package solsgo

type Pair struct {
	a bool
	b bool
}

func minmax(min bool, ts ...*TreeNode) *TreeNode {
	var res *TreeNode = nil
	for _, t := range ts {
		if res == nil {
			res = t
		} else {

			if t != nil && ((t.Val < res.Val) == min) {
				res = t
			}
		}
	}

	return res
}

func r_minmax(min bool, tree *TreeNode) *TreeNode {
	if tree == nil {
		return nil
	}
	return minmax(min, tree, r_minmax(min, tree.Left), r_minmax(min, tree.Right))
}

func process(tree *TreeNode) {

	if tree == nil || (tree.Left == nil && tree.Right == nil) {
		return
	}

	leftMax := r_minmax(false, tree.Left)
	rightMin := r_minmax(true, tree.Right)
	if leftMax == nil && rightMin == nil {
		return
	}

	invalidLeft := leftMax != nil && leftMax.Val > tree.Val
	invalidRight := rightMin != nil && rightMin.Val < tree.Val

	swappers := make(map[Pair]func())

	swappers[Pair{true, true}] = func() {
		leftMax.Val, rightMin.Val = rightMin.Val, leftMax.Val
	}
	swappers[Pair{true, false}] = func() {
		leftMax.Val, tree.Val = tree.Val, leftMax.Val
	}
	swappers[Pair{false, true}] = func() {
		rightMin.Val, tree.Val = tree.Val, rightMin.Val
	}
	swappers[Pair{false, false}] = func() {}

	swappers[Pair{invalidLeft, invalidRight}]()

	process(tree.Left)
	process(tree.Right)

}

func recoverTree(root *TreeNode) {
	process(root)
}
