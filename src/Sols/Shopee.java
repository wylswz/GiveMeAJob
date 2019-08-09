package Sols;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Shopee {


    public static int mahattan(int[] p1, int[] p2) {
        return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
    }
    public static int leastDist () {
        Scanner scanner = new Scanner(System.in);
        final int order = scanner.nextInt();
        int[][] grid = new int[order][order];
        HashSet<int[]> oneCoords = new HashSet<>();
        int max = Integer.MAX_VALUE;
        for (int i=0;i<order;i++) {
            for (int j=0; j<order; j++) {
                grid[i][j] = scanner.nextInt();
                if (grid[i][j] == 1) {
                    oneCoords.add(new int[]{i, j});
                }
            }
        }

        for (int i=0;i<order;i++) {
            for (int j=0; j<order; j++) {

                if (grid[i][j] == 0) {
                    int acc = 0;
                    for (int []p2 : oneCoords) {

                        acc += mahattan(new int[]{i,j}, p2);

                    }
                    if (acc<max) {
                        max = acc;
                    }
                }

            }
        }

        System.out.println(max);
        return max;
    }

    public static void main(String [] args) {
        wildcard("*.com", "shopeemobile.com");

    }

    public static boolean shift(String str, String tar){
        StringBuilder a = new StringBuilder(str);
        for (int i=0;i<str.length();i++) {
            a.append(a.charAt(0));
            a.deleteCharAt(0);
            if (a.toString().equals(tar)) {
                return true;
            }
        }

        return false;
    }
    public static void test() {
        Scanner sc = new Scanner(System.in);
        int counter = Integer.parseInt(sc.nextLine());
        if (counter <= 1) {System.out.println(0);return;}
        HashSet<String> acc = new HashSet<>();
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> used = new ArrayList<>();
        for (int i=0; i< counter; i++) {
            words.add(sc.nextLine());
        }
        for (String s : words) {
            for (String s2:words) {
                if (!s.equals(s2) && !used.contains(s) && shift(s,s2)) {
                    acc.add(s);
                    used.add(s2);
                }
            }

        }
        System.out.println(acc);
        System.out.println(used);
        System.out.println(acc.size());
    }

    public static boolean canBeTri(int a, int b, int c) {
        return (Math.abs(a-b) < c) && (a + b > c);
    }
    public static void triangle() {
        Scanner sc = new Scanner(System.in);
        int cnt = sc.nextInt();
        int [] sticks = new int[cnt];
        for (int i=0;i<cnt;i++) {
            sticks[i]=sc.nextInt();
        }
        int acc = 0;

        for (int i=0;i<cnt;i++) {
            for (int j=i+1;j<cnt;j++) {
                for (int k=j+1;k<cnt;k++) {
                    if (canBeTri(sticks[i], sticks[j], sticks[k])) {
                       acc += 1;
                    }
                }
            }
        }
        System.out.println(acc);
    }

    public static void flitAppend() {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        boolean end = true;
        ArrayList<Integer> res = new ArrayList<>();
        for (int i=0;i<n;i++) {
            if (end) {
                res.add(sc.nextInt());
            } else {
                res.add(0,sc.nextInt());
            }
            end = !end;
        }
        for (int i=0;i<n;i++) {
            System.out.print(res.get(i));
            if (i < (n-1)) {
                System.out.print(" ");
            }
        }
    }

    public static boolean charEq(char a, char b) {
        return a == b || (a == '*' || b == '*');
    }
    /**
     * Match *
     * Print out from index and length
     * @param pattern
     * @param string
     */
    public static String strDup(String s, int t) {
        String tmp = "";
        for (int i=0;i<t;i++) {
            tmp = tmp + s;
        }
        return tmp;
    }
    public static void strMatch(String str1, String str2) {
        int startAt = 0;
        while (startAt <= str2.length() - str1.length()) {
            boolean match = true;
            for (int i=0;i<str1.length();i++) {
                if (!charEq(str1.charAt(i),str2.charAt(startAt + i))) {
                    match = false;
                }
            }
            if (match) {
                System.out.print(startAt+" " +str1.length());
            }


            startAt ++;
        }
    }
    public static void wildcard(String pattern, String string) {
        int wildcardCount = string.length() - (pattern.length() - 1);
        int starIndex = 0;
        for (int i=0; i<pattern.length(); i++) {
            if (pattern.charAt(i) == '*') {
                starIndex = i;
                break;
            }
        }

        for (int stars=0; stars <=wildcardCount; stars ++) {
            String tmp;
            if (starIndex >= pattern.length() - 1)
            {
                tmp = pattern.substring(0, starIndex) + strDup("*", stars);
            } else {
                tmp = pattern.substring(0, starIndex) + strDup("*", stars) + pattern.substring(starIndex + 1);
            }

            System.out.println(tmp);
            strMatch(tmp, string);
        }


    }
}
