package projectx.problem;

import com.google.common.base.Preconditions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mihails Volkovs on 2015.02.02.
 */
public class Problem31 {

    private int result = 0;

    public static void main(String... args) {
        CoinsCombination combination = new CoinsCombination();
        System.out.println(combination);
        int combinations = 1;
        while (combination != null) {
            combination = combination.next();
            combinations++;
            System.out.println(combination);
        }
        System.out.println(combinations);
    }

    private static class CoinsCombination {

//        private static final int TOTAL_CONSTRAINT = 200;
        private static final int TOTAL_CONSTRAINT = 10;

//        private static final int[] COINS = {1, 2, 5, 10, 20, 50, 100, 200};
        private static final int[] COINS = {1, 2, 5, 10};

        private int index = 0;

        private List<Integer> combination = new ArrayList<Integer>();

        public CoinsCombination(List<Integer> combination, int index) {
            Preconditions.checkArgument(getTotal(combination) == TOTAL_CONSTRAINT);
            this.combination = combination;
            this.index = index;
        }

        public CoinsCombination() {
            while (getTotal(combination) < TOTAL_CONSTRAINT) {
                combination.add(COINS[0]);
            }
        }

        private int getTotal(List<Integer> combination) {
            return combination.stream().mapToInt(Integer::intValue).sum();
        }

        public CoinsCombination next() {
            List<Integer> nextCombination = new ArrayList<Integer>(combination);
            int nextIndex = increment(nextCombination, index);
            return new CoinsCombination(nextCombination, nextIndex);
        }

        private int increment(List<Integer> combination, int index) {
            while (index > combination.size() - 1) {
                combination.add(COINS[0]);
            }
            Integer oldValue = combination.get(index);
            int newValue = increment(oldValue);
            combination.set(index, newValue);
            while (getTotal(combination) > TOTAL_CONSTRAINT && combination.size() > 1) {
                combination.remove(combination.size() - 1);
            }
            int newIndex = index + 1;
            if (newIndex > combination.size()) {
                newIndex = 0;
            }
            return newIndex;
        }

        private int increment(int coin) {
            for (int i = 0; i < COINS.length; i++) {
                if (COINS[i] > coin) {
                    return COINS[i];
                }
            }
            return COINS[0];
        }

        public String toString() {
            return getTotal(combination) + ": " + combination;
        }

    }

}
