package p236

type TreeNode struct {
	Val   int
	Left  *TreeNode
	Right *TreeNode
}

func constructLink(root *TreeNode, link map[*TreeNode]*TreeNode) {
	if root == nil {
		return
	}
	if root.Left != nil {
		link[root.Left] = root
	}
	if root.Right != nil {
		link[root.Right] = root
	}
	constructLink(root.Left, link)
	constructLink(root.Right, link)

}

func lowestCommonAncestor(root, p, q *TreeNode) *TreeNode {
	if p == q {
		return p
	}
	link := make(map[*TreeNode]*TreeNode)
	constructLink(root, link)

	nodeSet := make(map[*TreeNode]bool)
	current := p
	for {
		if current == nil {
			break
		}
		nodeSet[current] = true
		current = link[current]
	}

	current = q
	for {
		if current == nil {
			break
		}
		_, ok := nodeSet[current]
		if ok {
			return current
		}
		current = link[current]
	}
	return nil
}
