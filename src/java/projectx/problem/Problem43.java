/*
 * COPYRIGHT Ericsson (c) 2014.
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 */
package projectx.problem;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.*;

public class Problem43 {

    private static final String INITIAL_PANDIGITAL = "0123456789";

    private static final int[] divs = new int[]{2, 3, 5, 7, 11, 13, 17};

    private static List<String> permutations = Lists.newArrayList();

    public static void main(String... args) {
        permutation(INITIAL_PANDIGITAL);
        long sum = 0;
        for (String permutation : permutations) {
            if (hasDivisibilityProperty(permutation)) {
                sum += Long.parseLong(permutation);
            }
        }
        System.out.println(sum);
    }

    private static void permutation(String str) {
        permutation("", str);
    }

    private static void permutation(String prefix, String str) {
        int n = str.length();
        if (n == 0) {
            permutations.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n));
            }
        }
    }

    private static boolean hasDivisibilityProperty(String permutation) {
        String[] digits = permutation.split("");
        boolean result = true;
        for (int i = 2; i <= 8; i++) {
            boolean temporaryResult = isDivisibleBy(digits, divs[i - 2], i, i + 1, i + 2);
            result = result && temporaryResult;
        }
        return result;
    }

    private static boolean isDivisibleBy(String[] digits, int number, int... indexes) {
        StringBuilder sb = new StringBuilder();
        for (int index : indexes) {
            sb.append(digits[index - 1]);
        }
        return Integer.parseInt(sb.toString()) % number == 0;
    }

    @Test
    public void permutation() {
        permutation(INITIAL_PANDIGITAL);
        assertEquals(3_628_800, permutations.size());
    }

    @Test
    public void isDivisibleBy() {
        String[] digits = "1406357289".split("");
        assertTrue(isDivisibleBy(digits, 13, 7, 8, 9));
        assertTrue(isDivisibleBy(digits, 17, 8, 9, 10));
    }

    @Test
    public void hasDivisibilityProperty() {
        assertTrue(hasDivisibilityProperty("1406357289"));
    }

}
