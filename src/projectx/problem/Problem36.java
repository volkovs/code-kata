package projectx.problem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihails Volkovs on 2015.02.05.
 */
public class Problem36 {

    private static final int UPPER_LIMIT = 1000000;

    public static void main(String... args) {
        int result = 0;
        for (int number = 1; number < UPPER_LIMIT; number++) {
            if (isPalindrome("" + number) && isPalindrome(toBinary(number))) {
                result += number;
                System.out.println("Palindrome found: " + number);
            }
        }
        System.out.println("Result: " + result);
    }

    public static boolean isPalindrome(String number) {

        // checking arguments
        if (number.startsWith("0")) {
            return false;
        }

        return new StringBuilder(number).reverse().toString().equals(number);
    }

    public static String toBinary(int number) {
        return Integer.toBinaryString(number);
    }

    @Test
    public void testIsPalindrome() {
        assertTrue(isPalindrome("585"));
        assertTrue(isPalindrome("1001001001"));
        assertFalse(isPalindrome("10010010010"));
        assertFalse(isPalindrome("010010010010"));
    }

    @Test
    public void testToBinary() {
        assertEquals("1001001001", toBinary(585));
    }

}
