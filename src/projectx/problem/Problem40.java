package projectx.problem;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mihails Volkovs on 2015.02.08.
 */
public class Problem40 {

    private static final long UPPER_LIMIT = 1_000_000;

    public static final void main(String... args) {
        long result = 1;
        for (int n = 1; n <= UPPER_LIMIT; n *= 10) {
            result *= d(n);
        }
        System.out.println(result);
    }

    private static int d(int n) {
        int fractionLength = 0;
        int currentNumber = 0;
        String lastDigits = "0";
        while (fractionLength < n) {
            currentNumber++;
            fractionLength += ("" + currentNumber).length();
            lastDigits = "" + currentNumber;
        }
        int from = lastDigits.length() - 1 - (fractionLength - n);
        return Integer.parseInt(lastDigits.substring(from, from + 1));
    }

    @Test
    public void d() {
        assertEquals(1, d(1));
        assertEquals(2, d(2));
        assertEquals(1, d(10));
        assertEquals(0, d(11));
        assertEquals(1, d(12));
        assertEquals(3, d(17));
        assertEquals(2, d(32));
        assertEquals(1, d(33));
    }

}
