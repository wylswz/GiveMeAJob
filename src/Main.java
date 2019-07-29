import Search.BinSearch;
import Sols.JZOfferSols;
import Sols.JZOfferSolsPart2;
public class Main {

    public static void main(String[] args) {
        JZOfferSols part1 = new JZOfferSols();
        JZOfferSolsPart2 part2 = new JZOfferSolsPart2();
        System.out.println(part2.binaryFilter(new int[]{1,3,5},4, 0, 2));
        System.out.println(part2.InversePairs(new int[]{364,637,341,406,747,995,234,971,571,219,993,407,416,366,315,301,601,650,418,355,460,505,360,965,516,648,727,667,465,849,455,181,486,149,588,233,144,174,557,67,746,550,474,162,268,142,463,221,882,576,604,739,288,569,256,936,275,401,497,82,935,983,583,523,697,478,147,795,380,973,958,115,773,870,259,655,446,863,735,784,3,671,433,630,425,930,64,266,235,187,284,665,874,80,45,848,38,811,267,575}));
    }
}
