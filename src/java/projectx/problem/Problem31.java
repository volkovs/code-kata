package projectx.problem;

import com.google.common.base.Preconditions;
import org.junit.Test;
import projectx.utils.PermutationGenerator;

import java.util.Arrays;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * Created by Mihails Volkovs on 2015.02.02.
 */
public class Problem31 {

            private static final int TOTAL_CONSTRAINT = 200;
//    private static final int TOTAL_CONSTRAINT = 10;

    private int result = 0;

    public static void main(String... args) {
        CoinsCombination combination = new CoinsCombination(TOTAL_CONSTRAINT);
        int combinations = 0;
        while (combination != null) {
            combination = combination.next();
            if (combination != null) {
                combinations++;
                System.out.println(combinations + ") " + combination);
            }
        }
        System.out.println(combinations);
    }

    private static class CoinsCombination {

                private static final int[] COINS = {200, 100, 50, 20, 10, 5, 2, 1};
//        private static final int[] COINS = {10, 5, 2, 1};

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
            if (combination[index] * COINS[index] > targetTotal) {
                combination[index] = 0;
                roll(index - 1);
            }
        }

        public int[] getCombination() {
            return combination;
        }

        private CoinsCombination next() {
            int[] newCombination = Arrays.copyOf(combination, combination.length);
            CoinsCombination candidate = new CoinsCombination(targetTotal, newCombination);
            candidate.roll();

            int[] initState = new int[combination.length];
            boolean allCombinationsEnumerated = Arrays.equals(candidate.getCombination(), initState);
            while (candidate.getTotal() != targetTotal && !allCombinationsEnumerated) {
                candidate.roll();
                allCombinationsEnumerated = Arrays.equals(candidate.getCombination(), initState);
            }

            // all combinations enumerated
            if (allCombinationsEnumerated) {
                return null;
            }
            return candidate;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < combination.length; i++) {
                for (int j = 0; j < combination[i]; j++) {
                    sb.append( COINS[i]+ ",");
                }
            }
            return getTotal() + ": " + sb.toString();
        }

        public static String toString(int[] combination) {
            return Arrays.toString(combination);
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

        combination = new CoinsCombination(10, new int[]{0, 0, 0, 10});
        assertEquals("[0, 0, 0, 10]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[0, 0, 1, 0]", Arrays.toString(combination.getCombination()));

        combination = new CoinsCombination(10, new int[]{0, 0, 10, 10});
        assertEquals("[0, 0, 10, 10]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[0, 1, 0, 0]", Arrays.toString(combination.getCombination()));

        combination = new CoinsCombination(10, new int[]{0, 10, 10, 10});
        assertEquals("[0, 10, 10, 10]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[1, 0, 0, 0]", Arrays.toString(combination.getCombination()));

        combination = new CoinsCombination(10, new int[]{10, 10, 10, 10});
        assertEquals("[10, 10, 10, 10]", Arrays.toString(combination.getCombination()));
        combination.roll();
        assertEquals("[0, 0, 0, 0]", Arrays.toString(combination.getCombination()));
    }

    @Test
    public void next() {
        CoinsCombination combination = new CoinsCombination(10);
        assertEquals("[0, 0, 0, 0]", Arrays.toString(combination.getCombination()));
        combination = combination.next();
        assertEquals("[0, 0, 0, 10]", Arrays.toString(combination.getCombination()));
        combination = combination.next();
        assertEquals("[0, 0, 1, 8]", Arrays.toString(combination.getCombination()));

        combination = new CoinsCombination(10, new int[]{10, 10, 10, 10});
        CoinsCombination next = combination.next();
        assertNull(next);
    }

    @Test
    public void testToString() {
        CoinsCombination combination = new CoinsCombination(10, new int[]{1,2,3,4});
        assertEquals("30: 10,5,5,2,2,2,1,1,1,1,", combination.toString());
    }

}
