package Sols;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        leastDist();
    }
}
