package solsgo

/*

You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:

struct Node {
  int val;
  Node *left;
  Node *right;
  Node *next;
}

Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.

Initially, all next pointers are set to NULL.



Follow up:

    You may only use constant extra space.
    Recursive approach is fine, you may assume implicit stack space does not count as extra space for this problem.


*/

var current *ConnPair = nil

type ConnPair struct {
	Level int
	Node  *Node
}

func connect(root *Node) *Node {
	if root == nil {
		return root
	}
	q := make([]ConnPair, 0)
	q = append(q, ConnPair{0, root})
	for len(q) > 0 {
		next := q[0]
		q = q[1:]
		if current != nil && current.Level == next.Level {
			current.Node.Next = next.Node
		}
		current = &next

		if next.Node.Left != nil {
			q = append(q, ConnPair{next.Level + 1, next.Node.Left})
		}

		if next.Node.Right != nil {
			q = append(q, ConnPair{next.Level + 1, next.Node.Right})
		}
	}
	return root
}
