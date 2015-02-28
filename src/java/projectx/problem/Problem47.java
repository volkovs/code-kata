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
import com.google.common.collect.Sets;
import org.junit.Test;
import projectx.utils.PrimeUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class Problem47 {

    private static final int UPPER_LIMIT = 4;

    public static final void main(String... args) {
        findConsecutiveNumbers(UPPER_LIMIT);
    }

    private static List<Integer> findConsecutiveNumbers(int limit) {
        List<Integer> numbers = Lists.newArrayList();
        boolean consecutive = false;
        for (int number = 2; number < Integer.MAX_VALUE; number++) {
            Set<Integer> primeFactors = findPrimeFactors(number);
            if (primeFactors.size() == limit) {
                if (!consecutive) {
                    numbers.clear();
                    consecutive = true;
                }
                if (!numbers.isEmpty()) {
                    System.out.println(numbers + " " + primeFactors);
                }
                numbers.add(number);
                if (numbers.size() >= limit) {
                    System.out.println("Found consecutive numbers: " + numbers);
                    return numbers;
                }
            } else {
                consecutive = false;
            }
        }
        return numbers;
    }

    private static Set<Integer> findPrimeFactors(int number) {
        Set<Integer> result = Sets.newHashSet();
        for (int factor = 2; factor < Math.sqrt(number); factor++) {
            if (number % factor == 0) {
                number = number / factor;
                result.add(factor);
                result.addAll(findPrimeFactors(number));
                break;
            }
        }
        if (PrimeUtils.isPrime(number)) {
            result.add(number);
        }
        return result;
    }

    @Test
    public void findConsecutiveNumbers() {
        assertEquals(Arrays.asList(14, 15), findConsecutiveNumbers(2));
        assertEquals(Arrays.asList(644, 645, 646), findConsecutiveNumbers(3));
    }

    @Test
    public void findPrimeFactors() {
        assertSet(findPrimeFactors(14), 2, 7);
        assertSet(findPrimeFactors(15), 3, 5);
        assertSet(findPrimeFactors(644), 2, 7, 23);
        assertSet(findPrimeFactors(645), 3, 5, 43);
        assertSet(findPrimeFactors(646), 2, 17, 19);
    }

    private void assertSet(Set<Integer> primeFactors, int... factors) {
        assertEquals(factors.length, primeFactors.size());
        for (int factor : factors) {
            assertTrue(primeFactors.contains(factor));
        }
    }

}
