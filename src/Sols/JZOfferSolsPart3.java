package Sols;

import com.sun.scenario.effect.impl.state.AccessHelper;
import sun.reflect.generics.tree.Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;


class TreeLinkNode {
    int val;
    TreeLinkNode left = null;
    TreeLinkNode right = null;
    TreeLinkNode next = null;

    TreeLinkNode(int val) {
        this.val = val;
    }
}


public class JZOfferSolsPart3 {
    public int LastRemaining_Solution(int n, int m) {
        LinkedList<Integer> children = new LinkedList<>();
        int counter = 0;

        for (int i=0; i<n; i++) {
            children.add(i);
        }
        Iterator it = children.iterator();
        while (children.size() > 1) {
            if (it.hasNext()){
                it.next();
            } else {
                it = children.iterator();
                it.next();
            }

            if (counter == m - 1) {
                it.remove();
                counter = 0;
            } else {
                counter += 1;

            }
        }
        return children.get(0);
    }

    public int Sum_Solution(int n) {
        try {
            int i = 3 / n;
        } catch (Exception e) {
            return 0;
        }
        return n + Sum_Solution(n-1);

    }

    public int Add(int num1,int num2) {
        if (num2 == 0) return num1;
        return Add(num1 ^ num2, (num1 & num2) << 1);
    }

    public int findMax(int[] num, int from, int to) {
        int res = 0;
        for (int i=from; i <= to; i++) {
            res = Math.max(res, num[i]);
        }
        return res;
    }
    public ArrayList<Integer> maxInWindows(int [] num, int size)
    {
        ArrayList<Integer> res = new ArrayList<>();
        if (num.length < size || size <= 0) return res;
        int from = 0;
        int to = from + size - 1;
        while(to < num.length) {
            res.add(findMax(num, from, to));
            to ++;
            from ++;
        }
        return res;
    }

    public boolean hasPathR(char[] matrix, int rows, int cols, char[] str, boolean[] visited, int row, int col) {
        if (str.length == 0) return true;
        if (cols * row + col >= matrix.length) return false;
        if ( row >= rows || col >= cols || row <0 || col < 0 || visited[cols * row + col]) return false;
        if (matrix[cols * row + col] != str[0]) return false;
        visited[cols * row + col] = true;

        return hasPathR(matrix, rows, cols, Arrays.copyOfRange(str, 1, str.length),visited, row+1,col)
                || hasPathR(matrix, rows, cols, Arrays.copyOfRange(str, 1, str.length),visited, row,col+1)
                || hasPathR(matrix, rows, cols, Arrays.copyOfRange(str, 1, str.length),visited, row-1,col)
                || hasPathR(matrix, rows, cols, Arrays.copyOfRange(str, 1, str.length),visited, row,col-1);


    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str)
    {
        boolean [] visited = new boolean[rows * cols];
        for (int i =0; i <visited.length; i++) {
            visited[i] = false;
        }
        for (int i=0; i< rows; i++) {
            for (int j=0; j< cols; j++) {
                if (matrix[i*cols + j] == str[0]) {
                    if (hasPathR(Arrays.copyOf(matrix,matrix.length),rows,cols,Arrays.copyOf(str, str.length),Arrays.copyOf(visited,visited.length),i,j)) {
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public int bitSum2(int num1, int num2) {
        int sum = 0;
        while (num1 > 0) {
            sum += num1 % 10;
            num1 /= 10;
        }

        while (num2 > 0) {
            sum += num2 % 10;
            num2 /= 10;
        }
        return sum;
    }

    public void movingCountR(int threshold, int rows, int cols, int row, int col, int[][] visited) {
        if (row>=rows || col>= cols || col<0||row<0 || visited[row][col]==1 || bitSum2(row,col)>threshold) {
            return;
        } else {
            visited[row][col] = 1;
            movingCountR(threshold,rows,cols,row+1,col,visited);
            movingCountR(threshold,rows,cols,row-1,col,visited);
            movingCountR(threshold,rows,cols,row,col+1,visited);
            movingCountR(threshold,rows,cols,row,col-1,visited);

        }
    }
    public int movingCount(int threshold, int rows, int cols)

    {
        int[][] visited = new int[rows][cols];
        int count = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                visited[i][j] = 0;
            }
        }

        movingCountR(threshold, rows, cols,0,0,visited);
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                count += visited[i][j];
            }
        }
        return count;

    }


    public TreeLinkNode GetNext(TreeLinkNode pNode)
    {
        if (pNode == null) return null;
        TreeLinkNode probe = pNode;
        if (pNode.right != null) {
            probe = pNode.right;
            while (probe.left != null) {
                probe = probe.left;
            }
            return probe;
        } else {
            if (pNode.next == null) {
                return null;
            }
            if (pNode.next.left == pNode) {
                return pNode.next;
            } else {
                pNode.next.right = null;
                return GetNext(pNode.next);
            }

        }
    }

    boolean isMirror(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1==null || t2 == null) {
            return false;
        }
        if (t1.val != t2.val) {
            return false;
        }
        return isMirror(t1.left, t2.right )&& isMirror(t1.right, t2.left);
    }

    boolean isSymmetrical(TreeNode pRoot)
    {
        if (pRoot == null) return false;
        return isMirror(pRoot.left, pRoot.right);
    }

}
