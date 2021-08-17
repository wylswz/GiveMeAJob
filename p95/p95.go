package p95

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func calSize(i, j int) int {
	if i == 0 {
		return j
	}
	if j == 0 {
		return i
	}
	return i * j
}
func comb(left, right []*TreeNode, val int) []*TreeNode {

	res := make([]*TreeNode, 0, calSize(len(left), len(right)))
	if len(left) == 0 {
		left = append(left, nil)
	}
	if len(right) == 0 {
		right = append(right, nil)
	}
	for _, vl := range left {
		for _, vr := range right {
			res = append(res, &TreeNode{Val: val, Left: vl, Right: vr})
		}
	}
	return res
}

func doGenerate(from, to int) []*TreeNode {
	if from > to {
		return []*TreeNode{}
	}
	if from == to {
		return []*TreeNode{
			{Val: from},
		}
	}

	if from+1 == to {
		return []*TreeNode{
			{Val: from, Right: &TreeNode{Val: to}},
			{Val: to, Left: &TreeNode{Val: from}},
		}
	}

	res := make([]*TreeNode, 0)
	for p := from; p <= to; p++ {
		toApp := comb(doGenerate(from, p-1), doGenerate(p+1, to), p)
		res = append(res, toApp...)
	}
	return res

}

func generateTrees(n int) []*TreeNode {
	return doGenerate(1, n)
}
