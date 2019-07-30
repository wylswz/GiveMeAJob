import Search.BinSearch;
import Sols.JZOfferSols;
import Sols.JZOfferSolsPart2;
public class Main {

    public static void main(String[] args) {
        JZOfferSols part1 = new JZOfferSols();
        JZOfferSolsPart2 part2 = new JZOfferSolsPart2();
        System.out.println(part2.isContinuous(new int[] { 1,2,3,0,6}));
    }
}
