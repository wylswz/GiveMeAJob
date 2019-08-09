package Sols;

import java.util.*;

public class JZOfferPart4 {

    String Serialize(TreeNode root) {
        StringBuilder str = new StringBuilder();


        return str.toString();
    }
    TreeNode Deserialize(String str) {
        return null;
    }

    public boolean matchChar(char a, char b) {
        return (a==b) || (a=='.') || (b=='.');
    }

    public boolean matchExact(char[] a, char[] b) {

        if (a.length!=b.length) return false;
        for (int i=0; i<a.length; i++) {
            if (!matchChar(a[i], b[i])) return false;
        }
        return true;
    }

    public char[] generateStr(char[] pattern, ArrayList<Integer> state, int length) {
        char[] res = new char[length];
        int counter = 0;
        int patPtr = 0;
        int resPtr = 0;
        while(patPtr < pattern.length - 1) {
            if (pattern[patPtr+1] != '*' ) {
                res[resPtr] = pattern[patPtr];
                patPtr ++;
                resPtr ++;
            } else {
                for (int i=0;i<state.get(counter);i++) {
                    res[resPtr] = pattern[patPtr];
                    resPtr ++;
                }
                patPtr += 2;
                counter ++;
            }
        }
        if(pattern[pattern.length-1] != '*') {
            res[resPtr] = pattern[pattern.length-1];
        }
        System.out.println("State: "+state);
        System.out.print("newstr: ");
        for (char c : res){
            System.out.print(c);
        }
        System.out.println();
        return res;
    }

    public void generateStates(ArrayList<Integer> state) {
        //System.out.println("Ori: " + state);
        for (int i=0; i<state.size(); i++) {
            if (state.get(i) != 0) {
                for(int j=0; j<state.size(); j++) {
                    if (i!=j) {
                        state.set(i, state.get(i)-1);
                        state.set(j, state.get(j)+1);
                        if(!used.contains(state)) {
                            ArrayList<Integer> newState = new ArrayList<>();
                            for (Integer s : state) {
                                newState.add(new Integer(s));
                            }
                            System.out.println(newState);
                            q.add(newState);
                        }
                        state.set(i, state.get(i)+1);
                        state.set(j, state.get(j)-1);


                    }
                }
            }
        }
    }

    HashSet<ArrayList<Integer>> used = new HashSet<>();
    ArrayList<ArrayList<Integer>> q = new ArrayList<>();

    public boolean match(char[] str, char[] pattern)
    {
        int stateLen = 0;
        for (char c : pattern) {
            if (c=='*') stateLen += 1;
        }
        if (stateLen == 0) {
            return matchExact(str, pattern);
        }

        ArrayList<Integer> state = new ArrayList<>();
        int diff = str.length - (pattern.length - stateLen*2);
        if (diff<0) return false;
        for (int i =0;i<stateLen; i++) {
            state.add(0);
        }
        state.set(0,diff);
        q.add(state);
        while (! q.isEmpty()) {
            ArrayList<Integer> thisState = q.get(0);
            q.remove(0);
            used.add(thisState);
            char[] newStr = generateStr(pattern, thisState, str.length);
            if (matchExact(newStr, str)) {
                return true;
            } else {
                generateStates(thisState);
            }
        }
        return false;
    }


    public HashMap<Character, Integer> occuranceCounter = new HashMap<>();
    public LinkedList<Character> onceChar = new LinkedList<>();
    //Insert one char from stringstream
    public void Insert(char ch)
    {
        occuranceCounter.put(ch, occuranceCounter.getOrDefault(ch, 0)+1);
        if(occuranceCounter.get(ch) == 1) {
            onceChar.add(ch);
        } else {
            onceChar.remove(new Character(ch));
            // Otherwise the ch will be treated as index
        }
    }
    //return the first appearence once char in current stringstream
    public char FirstAppearingOnce()
    {
        if (onceChar.size() == 0) {
            return '#';
        }
        return onceChar.get(0);
    }

    public ListNode EntryNodeOfLoop(ListNode pHead)
    {
        HashSet<ListNode> used = new HashSet<>();
        while(pHead != null) {
            if(used.contains(pHead)) {
                return pHead;
            } else {
                used.add(pHead);
                pHead = pHead.next;
            }
        }
        return null;
    }

    /**
     * The linked list is sorted
     * @param pHead
     * @return
     */
    public ListNode deleteDuplication(ListNode pHead)
    {
        ListNode tmp = pHead;

        if (tmp == null) {
            return null;
        }
        ListNode res = new ListNode(pHead.val);
        ListNode resIt = res;
        while (tmp!= null) {
            if (tmp.next == null) {
                resIt.next = new ListNode(tmp.val);
                resIt = resIt.next;
                break;
            }
            if (tmp.next.val == tmp.val) {
                int dup = tmp.val;
                while(tmp.val == dup) {
                    tmp = tmp.next;

                }
            } else {
                resIt.next = new ListNode(tmp.val);
                resIt = resIt.next;
                tmp = tmp.next;
            }
        }
        return res;
    }

    /**
     * Put first duplicated in duplication array
     * @param numbers
     * @param length
     * @param duplication
     * @return
     */
    public boolean duplicate(int numbers[],int length,int [] duplication) {
        HashSet<Integer> set = new HashSet<>();
        if (length == 0) {
            return false;
        }
        for (int n : numbers) {
            if (set.contains(n)) {

                duplication[0] = n;
                return true;
            } else {
                set.add(n);
            }
        }
        return false;
    }

    /**
     * B[i] is the product of all elements in A except A[i]
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {

    }
}
