package solsgo

func buildTree(nodes []*ListNode, from int, to int) *TreeNode {
	if from == to {
		return &TreeNode{
			Val:   nodes[from].Val,
			Left:  nil,
			Right: nil,
		}
	}

	if from > to {
		return nil
	}

	root := (from + to) / 2
	res := TreeNode{
		Val:   nodes[root].Val,
		Left:  buildTree(nodes, from, root-1),
		Right: buildTree(nodes, root+1, to),
	}
	return &res
}

func sortedListToBST(head *ListNode) *TreeNode {
	listArray := make([]*ListNode, 0)
	current := head
	for current != nil {
		listArray = append(listArray, current)
		current = current.Next
	}

	return buildTree(listArray, 0, len(listArray)-1)
}
