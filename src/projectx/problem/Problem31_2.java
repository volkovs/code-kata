package projectx.problem;

import com.google.common.base.Preconditions;
import org.junit.Test;
import projectx.utils.PermutationGenerator;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;

/**
 * Created by Mihails Volkovs on 2015.02.02.
 */
public class Problem31_2 {

    //        private static final int TOTAL_CONSTRAINT = 200;
    private static final int TOTAL_CONSTRAINT = 10;



    private int result = 0;

    public static void main(String... args) {
        PermutationGenerator generator = new PermutationGenerator(new String[]{"1", "2", "3", "4"}, 4);
        generator.generate();
        System.out.println(generator.getPermutations());

//        CoinsCombination combination = new CoinsCombination();
//        System.out.println(combination);
//        int combinations = 1;
//        while (combination != null) {
////            combination = combination.next();
//            combinations++;
//            System.out.println(combination);
//        }
//        System.out.println(combinations);
    }

    private static class CoinsCombination {

        //        private static final int[] COINS = {200, 100, 50, 20, 10, 5, 2, 1};
        private static final int[] COINS = {10, 5, 2, 1};

        private int targetTotal;

        private int[] combination;


        public CoinsCombination(int targetTotal){
            this(targetTotal, new int[COINS.length]);
        }

        public CoinsCombination(int targetTotal, int[] combination){
            this.targetTotal = targetTotal;
            Preconditions.checkArgument(combination.length == COINS.length);
            this.combination = combination;
        }

        public int getTotal() {
            int result = 0;
            for (int i = 0; i < combination.length; i++) {
                result += COINS[i] * combination[i];
            }
            return result;
        }

        public void roll() {
            roll(combination.length - 1);
        }

        private void roll(int index) {
            if (index < 0) {
                return;
            }
            combination[index] = combination[index] + 1;
            if (combination[index] > targetTotal) {
                combination[index] = 0;
                roll(index - 1);
            }
        }

        public int[] getCombination() {
            return combination;
        }

        private CoinsCombination next() {
            return null;
        }

        private int increment(int coin) {
            for (int i = 0; i < COINS.length; i++) {
                if (COINS[i] > coin) {
                    return COINS[i];
                }
            }
            return COINS[0];
        }

    }

    @Test
    public void getTotal() {
        CoinsCombination combination = new CoinsCombination(10);
        assertEquals(0, combination.getTotal());

        combination = new CoinsCombination(10, new int[]{4, 3, 2, 1});
        assertEquals(60, combination.getTotal());
    }

    @Test
    public void roll() {
        CoinsCombination combination = new CoinsCombination(10);
        assertEquals("[0, 0, 0, 0]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[0, 0, 0, 1]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[0, 0, 0, 2]", Arrays.toString(combination.getCombination()));

        combination = new CoinsCombination(10, new int[]{0, 0, 0, 100});
        assertEquals("[0, 0, 0, 100]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[0, 0, 1, 0]", Arrays.toString(combination.getCombination()));

        combination = new CoinsCombination(10, new int[]{0, 0, 100, 100});
        assertEquals("[0, 0, 100, 100]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[0, 1, 0, 0]", Arrays.toString(combination.getCombination()));

        combination = new CoinsCombination(10, new int[]{0, 100, 100, 100});
        assertEquals("[0, 100, 100, 100]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[1, 0, 0, 0]", Arrays.toString(combination.getCombination()));

        combination = new CoinsCombination(10, new int[]{100, 100, 100, 100});
        assertEquals("[100, 100, 100, 100]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[0, 0, 0, 0]", Arrays.toString(combination.getCombination()));
    }

}
