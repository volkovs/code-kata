package projectx.problem;

import com.google.common.collect.Lists;
import org.junit.Test;
import projectx.utils.PrimeUtils;

import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;

public class Problem35 {

    private static final int UPPER_LIMIT = 1_000_000;
//    private static final int UPPER_LIMIT = 100;

    public static final void main(String... args) {
        int count = 1;
        for (int number = 3; number <= UPPER_LIMIT; number++) {
            if (areRotationsPrime(number)) {
                System.out.println("Found rotation prime: " + number);
                count++;
            }
        }
        System.out.println("Total count is " + count);
    }

    private static boolean areRotationsPrime(int number) {
        String numberAsString = "" + number;
        if (numberAsString.contains("2") ||
                numberAsString.contains("4") ||
                numberAsString.contains("6") ||
                numberAsString.contains("8") ||
                numberAsString.contains("0")) {
            return false;
        }
        for (int candidate : getRotations(number)) {
            if (!PrimeUtils.isPrime(candidate)) {
                return false;
            }
        }
        return true;
    }

    private static List<Integer> getRotations(int number) {
        String numberAsString = "" + number;
        List<Integer> rotations = Lists.newArrayList();
        for (int i = 0; i < numberAsString.length(); i++) {
            rotations.add(number);
            number = rotate(number);
        }
        return rotations;
    }

    private static int rotate(int number) {
        String numberAsString = "" + number;
        String firstSymbol = numberAsString.substring(0, 1);
        String restSymbols = numberAsString.substring(1);
        String nextNumber = restSymbols + firstSymbol;
        return Integer.parseInt(nextNumber);
    }

    @Test
    public void rotate() {
        assertEquals(2341, rotate(1234));
        assertEquals(3412, rotate(rotate(1234)));
        assertEquals(4123, rotate(rotate(rotate(1234))));
        assertEquals(1234, rotate(rotate(rotate(rotate(1234)))));
    }

    @Test
    public void getRotations() {
        assertEquals(Arrays.asList(1234, 2341, 3412, 4123), getRotations(1234));
    }

}
