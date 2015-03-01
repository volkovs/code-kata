package projectx.problem;

import com.google.common.base.Joiner;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mihails Volkovs on 2015.02.28.
 */
public class Problem76 {

    private static final int UPPER_LIMIT = 100;

    private static Set<String> combinations = Sets.newHashSet();

    public static final void main(String... args) {
        System.out.println("Result: " + combinations(UPPER_LIMIT));
        System.out.println("Result: " + combinations(UPPER_LIMIT).size());
    }

    private static Set<String> combinations(int number) {
        return combinations(number, number);
    }

    private static Set<String> combinations(int number, int limit) {
        Set<String> combinations = Sets.newHashSet();
        if (number == 1) {
            combinations.add("1");
            return combinations;
        }
        for (int i = 1; i <= number - 1; i++) {
            Set<String> tailCombinations = combinations(number - i, limit);
            for (String tailCombination : tailCombinations) {
                String combinationHashCode = normalize(i + tailCombination);
                combinations.add(combinationHashCode);
            }
        }
        if (number < limit) {
            combinations.add(normalize("" + number));
        }
        return combinations;
    }

    private static String normalize(String number) {
        String[] newCombination = number.split("");
        Arrays.sort(newCombination);
        return Joiner.on("").join(newCombination);
    }

    @Test
    public void count() {
        assertEquals(0, combinations(0).size());
        assertEquals(1, combinations(1).size());
        assertEquals(1, combinations(2).size());    // 1+1
        assertEquals(2, combinations(3).size());    // 1+1+1        2+1
        assertEquals(4, combinations(4).size());    // 1+1+1+1      2+1+1       2+2       3+1
        assertEquals(6, combinations(5).size());    // 1+1+1+1+1    2+1+1+1     2+2+1     2+3       3+1+1   (3+2)     4+1
    }

}
