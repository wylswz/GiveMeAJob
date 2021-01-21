import java.util.*

class TreeNode(var `val`: Int) {
         var left: TreeNode? = null
         var right: TreeNode? = null
}

fun countNodes(root: TreeNode?): Int {
    if (root == null)
        return 0

    val q: Queue<TreeNode> = LinkedList<TreeNode>()
    q.add(root)
    var res = 0;
    while(!q.isEmpty()) {
        val current = q.poll()
        if (current != null) {
            res += 1
            q.add(current.left)
            q.add(current.right)
        }
    }
    return res
}