package Sols;

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
    TreeNode left;
    TreeNode right;
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
}
