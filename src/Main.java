import Search.BinSearch;
import Sols.JZOfferSols;
import Sols.JZOfferSolsPart2;
public class Main {

    public static void main(String[] args) {
        JZOfferSols part1 = new JZOfferSols();
        JZOfferSolsPart2 part2 = new JZOfferSolsPart2();

        System.out.println(part2.IsPopOrder(new int[]{1,2,3,4,5}, new int[]{4,3,5,1,2}));
        System.out.println(part2.VerifySquenceOfBST(new int[] {4,6,7,5}));
    }
}
