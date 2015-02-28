package projectx.problem;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertFalse;

/**
 * Created by Mihails Volkovs on 2015.02.25.
 */
public class Problem52 {

    private static final int UPPER_LIMIT = 6;

    public static final void main(String... args) {
        for (int number = 1; number < Integer.MAX_VALUE; number++) {
            if (isPermutationOf(number)) {
                System.out.println("Found minimal number: " + number);
                return;
            }
        }
    }

    private static boolean isPermutationOf(int number) {
        for (int multiplier = 2; multiplier <= UPPER_LIMIT; multiplier++) {
            if (!isPermutationOf(number, number * multiplier)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isPermutationOf(int number1, int number2) {
        List<String> digits1 = Arrays.asList((number1 + "").split(""));
        List<String> digits2 = Arrays.asList((number2 + "").split(""));
        Set<String> uniqDigits1 = Sets.newHashSet(digits1);
        Set<String> uniqDigits2 = Sets.newHashSet(digits2);
        return uniqDigits1.equals(uniqDigits2);
    }

    @Test
    public void isPermutationOf() {
//        assertTrue(isPermutationOf(125874, 251748));
//        assertTrue(isPermutationOf(251748, 125874));
//        assertTrue(isPermutationOf(2517488, 1258744));
        assertFalse(isPermutationOf(251748, 125876));
    }

}
