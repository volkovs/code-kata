package projectx.problem;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Created by Mihails Volkovs on 2015.02.14.
 */
public class Problem38 {

    private static final int UPPER_LIMIT = 100_000;

    private static int counter = 0;

    public static final void main(String... args) {
        int result = 0;

        int generator = 2;
        while (generator < UPPER_LIMIT) {

            // performance optimization
            if (isGte(generator, result)) {

                Integer pandigital = getPandigital(generator);
                if (pandigital != null && pandigital > result) {
                    result = pandigital;
                }
            }
            generator++;
        }
        System.out.println("Maximum pandigital is " + result);
    }

    private static boolean isGte(int generator, int result) {
        String generatorString = "" + generator;
        String resultString = "" + result;
        int substringSize = Math.min(generatorString.length(), resultString.length());
        int value1 = Integer.parseInt(generatorString.substring(0, substringSize));
        int value2 = Integer.parseInt(resultString.substring(0, substringSize));
        return value1 >= value2;
    }

    private static Integer getPandigital(long generator) {
        int count = 2;
        StringBuilder sb = new StringBuilder("" + generator);
        while (sb.length() < 9) {
            sb.append(generator * count++);
        }
        String candidate = sb.toString();
        if (count > 2 && isPandigital9(candidate)) {
            counter++;
            int pandigital = Integer.parseInt(sb.toString());
            System.out.println(String.format("Found pandigital %s \t%s = %s x (1, 2,..., %s) ", counter, pandigital, generator, count - 1));
            return pandigital;
        }
        return null;
    }

    private static boolean isPandigital9(String candidate) {
        return candidate.length() == 9 && !candidate.contains("0") && new HashSet(Arrays.asList(candidate.split(""))).size() == 9;
    }

    @Test
    public void isPandigital9() {
        assertTrue(isPandigital9("192384576"));
        assertTrue(isPandigital9("918273645"));
        assertFalse(isPandigital9("18273645"));
        assertFalse(isPandigital9("918273646"));
        assertFalse(isPandigital9("012345678"));
    }

    @Test
    public void getPandigital() {
        assertEquals(192384576, getPandigital(192).intValue());
        assertEquals(918273645, getPandigital(9).intValue());
        assertNull(getPandigital(8));
        assertNull(getPandigital(987654321));
    }

    @Test
    public void isGreaterThan() {
        assertFalse(isGte(4, 56));
        assertTrue(isGte(5, 56));
        assertTrue(isGte(6, 56));

        assertFalse(isGte(45, 56));
        assertTrue(isGte(56, 56));
        assertTrue(isGte(67, 56));

        assertFalse(isGte(456, 56));
        assertTrue(isGte(567, 56));
        assertTrue(isGte(678, 56));
    }

}
