import Search.BinSearch;
import Sols.JZOfferSols;

public class Main {

    public static void main(String[] args) {
        StringBuffer a = new StringBuffer("asd asd asd");
        System.out.println(JZOfferSols.replaceSpace(a));
        System.out.println(new JZOfferSols().minNumberInRotateArray(new int[]{3,5,1,2}));
        System.out.println(new JZOfferSols().JumpFloorII(3));
        System.out.println(new JZOfferSols().RectCover(2));
        System.out.println(new JZOfferSols().NumberOf1(-2147483648));
        new JZOfferSols().reOrderArray(new int[] {});
    }
}
