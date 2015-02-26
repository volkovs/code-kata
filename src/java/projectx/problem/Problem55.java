package projectx.problem;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.math.BigInteger;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihails Volkovs on 2015.02.26.
 */
public class Problem55 {

    private static final int UPPER_LIMIT = 10_000;

    private static final int ITERATIONS_LIMIT = 50;

    public static final void main(String... args) {
        int lychrelNumberCount = 0;
        for(int number = 1; number < 10_000; number++) {
            if (isLychrelNumber(number)) {
                lychrelNumberCount++;
            }
        }
        System.out.println(lychrelNumberCount);
    }

    private static boolean isLychrelNumber(long number) {
        String numberAsString = "" + number;
        for (int iteration = 0; iteration < ITERATIONS_LIMIT; iteration++) {
            numberAsString = (new BigInteger(numberAsString)).add(new BigInteger(StringUtils.reverse(numberAsString))) + "";
            if (isPalindrome(numberAsString)) {
                System.out.println("Found palindrome: " + numberAsString);
                return false;
            }
        }
        return true;
    }

    private static boolean isPalindrome(String number ) {
        return StringUtils.reverse(number).equals(number);
    }

    @Test
    public void isLychrelNumber() {
        assertFalse(isLychrelNumber(47));
        assertFalse(isLychrelNumber(349));
        assertTrue(isLychrelNumber(10677));
        assertTrue(isLychrelNumber(4994));
    }

    @Test
    public void isPalindrome() {
        assertTrue(isPalindrome("121"));
        assertTrue(isPalindrome("123321"));
        assertFalse(isPalindrome("12312"));
    }

}
