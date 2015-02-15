package projectx.problem;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihails Volkovs on 2015.02.15.
 */
public class Problem37 {

    private static final int UPPER_LIMIT = 11;

    public static void main(String... args) {
        int count = 0;
        int number = 10;
        long total = 0;
        while (count < UPPER_LIMIT) {
            if (isTruncatablePrime(number)) {
                count++;
                total += number;
                System.out.println(String.format("Found %s truncatable prime: %s, total: %s", count, number, total));
            }
            number++;
        }
        System.out.println(total);
    }

    private static boolean isTruncatablePrime(int number) {
        if (isPrime(number)) {
            String numberAsString = "" + number;
            for (int truncation = 1; truncation < numberAsString.length(); truncation++) {
                String leftTruncated = numberAsString.substring(truncation);
                String rightTruncated = numberAsString.substring(0, truncation);
                if (!isPrime(Integer.parseInt(leftTruncated)) || !isPrime(Integer.parseInt(rightTruncated))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    private static boolean isPrime(int number) {
        if (number == 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void isPrime() {
        assertTrue(isPrime(3797));
        assertFalse(isPrime(3798));
        assertFalse(isPrime(17 * 17));
    }

    @Test
    public void isTruncatablePrime() {
        assertTrue(isTruncatablePrime(3797));
        assertFalse(isTruncatablePrime(3798));
    }

}
