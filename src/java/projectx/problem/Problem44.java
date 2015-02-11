package projectx.problem;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihails Volkovs on 2015.02.11.
 */
public class Problem44 {

    private static final long UPPER_LIMIT = 1_000_000L;

    private static List<Long> pentagonals = Lists.newArrayList();

    public static final void main(String... args) {

        // generating pentagonals
        for (long n = 1; n < UPPER_LIMIT; n++) {
            pentagonals.add(pentagonal(n));
        }

        // enumerating pairs
        for (long pentagonal1 : pentagonals) {
            for (long pentagonal2 : pentagonals) {
                if (pairFound(pentagonal1, pentagonal2)) {
                    System.out.println("Result found: " + Math.abs(pentagonal1 - pentagonal2));
                    return;
                }
            }
        }
        System.out.println("Result NOT found within limit " + UPPER_LIMIT);
    }

    private static long pentagonal(long n) {
        return n * (3 * n - 1) / 2;
    }

    private static double reversePentagonal(long pentagonal) {
        return (1 + Math.sqrt(1 + 24 * pentagonal)) / 6;
    }

    private static boolean isPentagonal(long candidate) {
        double n = reversePentagonal(candidate);
        return n == 0.0 + Math.round(n);
    }

    private static boolean pairFound(long pentagonal1, long pentagonal2) {
        return isPentagonal(pentagonal1 + pentagonal2) && isPentagonal(Math.abs(pentagonal1 - pentagonal2));
    }

    @Test
    public void pentagonal() {
        assertEquals(1, pentagonal(1));
        assertEquals(5, pentagonal(2));
        assertEquals(12, pentagonal(3));
        assertEquals(22, pentagonal(4));
        assertEquals(35, pentagonal(5));
        assertEquals(145, pentagonal(10));
    }

    @Test
    public void reversePentagonal() {
        assertEquals(1.0, reversePentagonal(1), 0.0000000001);
        assertEquals(2.0, reversePentagonal(5), 0.0000000001);
        assertEquals(3.0, reversePentagonal(12), 0.0000000001);
        assertEquals(4.0, reversePentagonal(22), 0.0000000001);
        assertEquals(5.0, reversePentagonal(35), 0.0000000001);
        assertEquals(10.0, reversePentagonal(145), 0.0000000001);
    }

    @Test
    public void isPentagonal() {
        assertTrue(isPentagonal(1));
        assertFalse(isPentagonal(2));
        assertTrue(isPentagonal(5));
        assertTrue(isPentagonal(145));
        assertFalse(isPentagonal(146));
    }

}
