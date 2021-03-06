package Sols;

import sun.reflect.generics.tree.Tree;

import java.util.List;
import java.util.Stack;

import java.util.ArrayList;
import java.util.Arrays;


class ListNode {
         int val;
         ListNode next = null;

         ListNode(int val) {
             this.val = val;
         }
     }

class TreeNode {
    int val;
    TreeNode left = null;
    TreeNode right = null;
    TreeNode(int x) { val = x; }
}


public class JZOfferSols {
    public static String replaceSpace(StringBuffer str) {
        // Replace space with "%20"
        int len = str.length();
        int spaceIndex = str.lastIndexOf(" ");
        while (spaceIndex >= 0) {
            str.replace(spaceIndex, spaceIndex + 1, "%20");
            spaceIndex = str.lastIndexOf(" ");
        }

        return str.toString();
    }

    public static ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        if (listNode == null) {
            return new ArrayList<Integer>();
        }
        if (listNode.next == null) {

            ArrayList<Integer> res = new ArrayList<Integer>();
            res.add(listNode.val);
            return res;
        } else {

            ArrayList<Integer> res = JZOfferSols.printListFromTailToHead(listNode.next);
            res.add(listNode.val);
            return res;
        }
    }
    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if (pre.length <= 0) {
            return null;
        }
        TreeNode head = new TreeNode(pre[0]);
        int headIndex = 0;
        for (int i=0; i<pre.length; i ++) {
            if (in[i] == pre[0]) {
                headIndex = i;
            }
        }

        int[] leftPre = Arrays.copyOfRange(pre, 1,1 + headIndex);
        int[] rightPre = Arrays.copyOfRange(pre,1 + headIndex, in.length);
        int[] leftIn = Arrays.copyOfRange(in, 0,0 + headIndex);
        int[] rightIn = Arrays.copyOfRange(pre,1 + headIndex, in.length);
        head.left = JZOfferSols.reConstructBinaryTree(leftPre, leftIn);
        head.right = JZOfferSols.reConstructBinaryTree(rightPre, rightIn);
        return head;
    }

    // Implement queue with 2 stacks
    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    public void push(int node) {
        stack1.push(node);
    }

    public int pop() {
        while (!stack1.empty()) {
            stack2.push(stack1.pop());
        }
        int res = stack2.pop();
        while (!stack2.empty()) {
            stack1.push(stack2.pop());
        }

        return res;
    }

    public int minNumberInRotateArray(int [] array) {
        if (array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }
        if (array.length == 2) {
            return array[0] <= array[1] ? array[0] : array[1];
        }
        for (int i=1; i< array.length; i ++) {
            int thisElem = array[array.length - i];
            int nextElem = array[array.length - i - 1];
            if (thisElem < nextElem) {
                return thisElem;
            }
        }

        return array[0];
    }

    public int Fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;

        int fst = 0, sec = 1;
        // if return fst, then start from 0
        // if return sec, then start from 1
        for (int i = 1; i < n; i ++) {
            int nxt = fst + sec;
            fst = sec;
            sec = nxt;
        }

        return sec;
    }
    public int JumpFloor(int target) {
        // A frog can jump one or two steps
        // how many methods to jump to targetth floor
        if (target == 1) return 1;
        if (target == 2) return 2;
        // Recursively solve the question
        return this.JumpFloor(target - 1) + this.JumpFloor(target - 2);
    }

    public int JumpFloorII(int target) {
        // A frog can jump any steps
        // M(N) = M(n-1) + M(n-2) + ... + M(1)
        // Keep substituting, we get 2^(target - 1) as result
        return (int)Math.pow(2, target-1);
    }

    public int RectCover(int target) {
        // how many ways to cover 2 * n rect with many 2 * 1 rects
        if (target == 1) return 1;
        if (target == 2) return 2;
        return this.RectCover(target - 1) + this.RectCover(target - 2);
    }

    public int NumberOf1(int n) {
        // Number of 1s in binary number
        // complementary for negative
        int base = 0;
        int tmp = n;

        if (n < 0) {
            tmp = ((int)Math.pow(2,31) + n + 1);
            base = 1;
        }
        System.out.println(tmp);
        while (tmp != 0) {

            base += tmp % 2;
            tmp = tmp / 2;
        }

        return base;

    }

    public void swap(int [] array, int from, int to) {
        int tmp = array[from];
        array[from] = array[to];
        array[to] = tmp;
    }
    public void reOrderArray(int [] array) {
        int ptr1 = 0;
        int ptr2 = 1;
        int numOdd = 0;
        int counter = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i]%2 == 1) {
                numOdd += 1;
            }
        }
        while (counter < numOdd) {
            while (array[ptr1] % 2 == 0) {
                if (ptr2 < array.length) {
                    this.swap(array, ptr1, ptr2);
                    ptr2 += 1;
                }

            }
            ptr1 += 1;
            ptr2 = ptr1 + 1;
            counter += 1;
        }
        for (int e : array) {
            System.out.println(e);
        }
    }
    public ListNode FindKthToTail(ListNode head,int k) {
        // Kth node from tail
        int ptr = 0;
        int length = 0;
        ListNode thisNode = head;
        ArrayList<ListNode> cache = new ArrayList<ListNode>();
        while (thisNode != null) {
            cache.add(thisNode);
            length += 1;
            thisNode = thisNode.next;
        }
        if (length - k >= cache.size() || length - k <= 0) {
            return null;
        }
        return cache.get(length - k - 1);
    }

    public ListNode ReverseList(ListNode head) {
        // reverse the linked list
        if (head == null) {return null;}
        if (head.next == null) {return head;}
        ListNode pre = null;
        ListNode mid = head;
        ListNode post = head.next;
        while (post != null) {
            mid.next = pre;
            pre = mid;
            mid = post;
            post = mid.next;
        }
        mid.next = pre;
        return mid;
    }

    public ListNode Merge(ListNode list1,ListNode list2) {
        // merge two monotonically increasing lists
        if (list1 == null) {return list2;}
        if (list2 == null) {return list1;}
        if (list1.val < list2.val) {
            ListNode tmp = Merge(list1.next, list2.next);
            ListNode res = new ListNode(list1.val);
            res.next = new ListNode(list2.val);
            res.next.next = tmp;
            return res;
        } else {
            ListNode tmp = Merge(list1.next, list2.next);
            ListNode res = new ListNode(list2.val);
            res.next = new ListNode(list1.val);
            res.next.next = tmp;
            return res;
        }
    }

    public boolean subTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return true;
        }

        if (root1 == null && root2 != null) {
            return false;
        }

        if (root1 != null && root2 == null) {
            return true;
        }
        return (root1.val == root2.val) && subTree(root1.left, root2.left) && subTree(root1.right, root2.right);
    }

    public boolean HasSubtree(TreeNode root1,TreeNode root2) {
        if (root1 == null || root2 == null) {
            return false;
        } else {
            return subTree(root1, root2) || subTree(root1.left, root2) || subTree(root1.right, root2);
        }
    }

    public void Mirror(TreeNode root) {
        if (root == null) {return ;}
        TreeNode tmp = root.left;
        root.left = root.right;
        root.right = tmp;
        Mirror(root.left);
        Mirror(root.right);
    }

    public ArrayList<Integer> cwLayer(int [][] matrix, int startR, int startC) {
        int R = startR;
        int C = startC;
        ArrayList<Integer> res = new ArrayList<Integer>();
        res.add(matrix[R][C]);
        int layerShapeR = matrix.length - 2*startR;
        int layerShapeC = matrix[0].length - 2 * startC;
        // inc col
        for (int i = 0; i < layerShapeC-1; i++) {
            res.add(matrix[R][++C]);
        }
        // inc row
        for (int i = 0; i < layerShapeR-1; i++) {
            res.add(matrix[++R][C]);

        }
        // dec col
        if (layerShapeR > 1) {
            // no need to turn back for one row cases
            for (int i = 0; i < layerShapeC-1; i++) {
                res.add(matrix[R][--C]);
            }
        }

        // dec row
        if (layerShapeC > 1) {
            for (int i = 0; i < layerShapeR-2; i++) {
                res.add(matrix[--R][C]);

            }
        }
        return res;
    }
    public ArrayList<Integer> printMatrix(int [][] matrix) {
        ArrayList<Integer> res =  new ArrayList<Integer>();
        int x = Math.min(matrix.length/2, matrix[0].length/2);

        for (int i = 0; i < x; i++) {
            res.addAll(cwLayer(matrix, i, i));
        }

        if (res.size() < matrix.length * matrix[0].length) {
            res.addAll(cwLayer(matrix,x, x));
        }
        return res;
    }
}
