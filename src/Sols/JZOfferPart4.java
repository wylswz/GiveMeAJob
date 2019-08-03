package Sols;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

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
}
