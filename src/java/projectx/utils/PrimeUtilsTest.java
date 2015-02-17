package projectx.utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import static projectx.utils.PrimeUtils.*;

public class PrimeUtilsTest {

    @Test
    public void testIsPrime() {
        assertTrue(isPrime(3797));
        assertFalse(isPrime(3798));
        assertFalse(isPrime(17 * 17));
    }

}
