package Sols;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Shopee {

    public int[] wildcard(String wc, String target) {
        return new int[]{};
    }

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
        flitAppend();

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
}
