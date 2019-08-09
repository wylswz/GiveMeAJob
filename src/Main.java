import Search.BinSearch;
import Sols.JZOfferPart4;
import Sols.JZOfferSols;
import Sols.JZOfferSolsPart2;
import Sols.JZOfferSolsPart3;
import Sort.BubbleSort;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {
    public static <T> void printArray(T[] array) {
        ArrayList tmp = new ArrayList();
        for (T a : array) {
            tmp.add(a);
        }
        System.out.println(tmp);
    }

    public static void main(String[] args) {
        JZOfferSols part1 = new JZOfferSols();
        JZOfferSolsPart2 part2 = new JZOfferSolsPart2();
        JZOfferSolsPart3 part3 = new JZOfferSolsPart3();
        JZOfferPart4 part4 = new JZOfferPart4();
        System.out.println(part4.duplicate(new int[]{}, 0, new int[]{-1}));

    }
}
