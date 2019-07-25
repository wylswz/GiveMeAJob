package Sols;

import sun.reflect.generics.tree.Tree;

import java.util.*;

class RandomListNode {
    int label;
    RandomListNode next = null;
    RandomListNode random = null;

    RandomListNode(int label) {
        this.label = label;
    }
}

public class JZOfferSolsPart2 {

    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA.length == 0) {
            return popA.length == 0;
        }

        if (pushA.length != popA.length) {return false;}

        ListNode stack = new ListNode(pushA[0]);
        int ptrPush = 0;
        int ptrPop = 0;
        while (ptrPop < pushA.length) {
            if (popA[ptrPop] == stack.val) {
                ptrPop += 1;
                stack = stack.next;
                // Pop the stack and move the pop pointer to the right
            } else {
                ptrPush += 1;
                if (ptrPush >= pushA.length) {
                    // no more elems to push and pop does not match stack top
                    return false;
                }
                ListNode head = new ListNode(pushA[ptrPush]);
                head.next = stack;
                stack = head;
                // push elem to stack to check if it match pop

            }

        }

        return true;

    }

    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<TreeNode> q = new LinkedList<>();
        q.addFirst(root);
        while(!q.isEmpty()) {
            TreeNode thisNode = q.pollLast();
            if (thisNode != null) {
                res.add(thisNode.val);
                q.addFirst(thisNode.left);
                q.addFirst(thisNode.right);
            }
        }

        return res;
    }
    public int findEdge(int[] sequence, int num) {
        int acc = 0;
        for (int i=0; i< sequence.length; i++) {
            if (sequence[i] < num) {
                acc += 1;
            }
        }
        return acc;

    }
    public boolean VerifySquenceOfBST(int [] sequence) {
        if (sequence.length == 1) { return true; }
        if (sequence.length == 0) { return false;}
        int last = sequence[sequence.length - 1];
        int edge = findEdge(sequence, last);
        int [] left = Arrays.copyOfRange(sequence,0, edge);
        int [] right = Arrays.copyOfRange(sequence, edge , sequence.length - 1);

        for (int i = edge; i < sequence.length; i++) {
            if (sequence[i] < last) {
                return false;
            }
        }
        boolean verifiedLeft, verifiedRight;
        if (left.length >0) {
            verifiedLeft = VerifySquenceOfBST(left);
        } else {
            verifiedLeft = true;
        }

        if (right.length > 0) {
            verifiedRight = VerifySquenceOfBST(right);
        } else {
            verifiedRight = true;
        }
        return verifiedLeft && verifiedRight;

    }


    public ArrayList<ArrayList<Integer>> res = new ArrayList<>();
    public ArrayList<Integer> cache = new ArrayList<>();
    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root,int target) {
        if(root==null) return res;
        cache.add(root.val);
        target -= root.val;
        if(target ==0 && root.left == null && root.right == null) {
            res.add(new ArrayList<>(cache));
        }
        FindPath(root.left, target);
        FindPath(root.right, target);
        cache.remove(cache.size() - 1);

        return res;


    }


    public HashMap<RandomListNode, RandomListNode> listMap = new HashMap<>();
    public RandomListNode CloneNext(RandomListNode pHead) {
        if(pHead == null) {
            return null;
        }
        RandomListNode res = new RandomListNode(pHead.label);
        listMap.put(pHead, res);
        res.next = CloneNext(pHead.next);
        return res;
    }
    public RandomListNode Clone(RandomListNode pHead)
    {


        RandomListNode res = CloneNext(pHead);
        RandomListNode temp = res;
        RandomListNode oldTemp = pHead;
        while (true) {
            if (temp == null) {break;}
            temp.random = listMap.get(oldTemp.random);
            temp = temp.next;
            oldTemp = oldTemp.next;
        }
        return res;
    }

    public ArrayList<TreeNode> TNQ = new ArrayList<>();
    public void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        TNQ.add(root);
        inOrder(root.right);
    }
    public TreeNode Convert(TreeNode pRootOfTree) {
        // Convert a binary tree to a sorted doubly linked list
        if (pRootOfTree == null) {return null;}
        inOrder(pRootOfTree);
        for (int i = 0; i < TNQ.size() - 1; i ++) {
            TNQ.get(i).right = TNQ.get(i+1);
            TNQ.get(i+1).left = TNQ.get(i);
        }
        return TNQ.get(0);
    }

    public ArrayList<String> permuList = new ArrayList<>();


    public HashSet<String> usedHead= new HashSet<>();


    public int MoreThanHalfNum_Solution(int [] array) {
        HashMap<Integer, Integer> count = new HashMap<>();
        for (int i : array) {
            count.put(i, count.getOrDefault(i,0) + 1);
            if (count.get(i) > array.length/2) {return i;}
        }
        return 0;
    }

}
