package Sols;

import sun.reflect.generics.tree.Tree;
import Sort.QuickSort;

import java.util.HashSet;
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

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {


        Arrays.sort(input);
        ArrayList<Integer> res = new ArrayList<>();
        for (int i=0; i<k; i++) {
            res.add(input[i]);
        }

        return res;
    }

    public int FindGreatestSumOfSubArray(int[] array) {
        if (array.length == 0) {
            return 0;
        }
        int [] maxExp = new int[array.length];
        int res = array[0];
        maxExp[0] = array[0];
        for (int i = 1; i< maxExp.length; i++) {
            maxExp[i] = Math.max(array[i],maxExp[i-1] + array[i]);
            if (maxExp[i] > res) {
                res = maxExp[i];
            }
        }
        return res;

    }

    public int NumberOf1Between1AndN_Solution(int n) {
        int len = Integer.toString(n).length();
        int res = 1;
        int tmp = 1;
        int power = 1;
        int factor;
        for (int i=2; i<len ; i++) {
            power *= 10;

            factor = 9;
            tmp = tmp * factor + power;
            System.out.println(tmp);
            res += tmp;

        }
        return res;
    }
    public String PrintMinNumber(int [] numbers) {
        String res = "";
        Comparator<Integer> a = new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {
                return (o1.toString() + o2.toString()).compareTo(o2.toString() + o1.toString());
            }
        };
        Integer [] test = new Integer[numbers.length];
        for (int i=0; i<numbers.length;i++) {
            test[i] = numbers[i];
        }
        Arrays.sort(test, a);
        for (int e : test) {
            res += e;
        }
        return res;
    }

    /**
     * Find nth ugly number
     * @param index
     * @return
     */
    public int GetUglyNumber_Solution(int index) {

        if (index < 7)return index;
        int [] res = new int[index];
        res[0] = 1;
        int t2 = 0, t3 = 0, t5 = 0, i;
        for (i = 1; i < index; ++i)
        {
            res[i] = Math.min(res[t2] * 2, Math.min(res[t3] * 3, res[t5] * 5));
            if (res[i] == res[t2] * 2)t2++;
            if (res[i] == res[t3] * 3)t3++;
            if (res[i] == res[t5] * 5)t5++;
        }
        return res[index - 1];
    }
}
