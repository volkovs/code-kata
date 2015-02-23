package projectx.problem;

import org.junit.Test;
import projectx.utils.PrimeUtils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihails Volkovs on 2015.02.23.
 */
public class Problem46 {

    public static final void main(String... args) {
        int number = 1;
        while (true) {
            number++;
            if (!PrimeUtils.isPrime(number) && number % 2 != 0) {
                if (!isGoldbachNumber(number)) {
                    System.out.println("Found law break: " + number);
                    return;
                }
            }
        }
    }

    private static boolean isGoldbachNumber(int number) {
        for (int addition = 2; addition < number; addition++) {
            if (PrimeUtils.isPrime(addition)) {
                System.out.println("Considering prime: " + addition);
                for (int square = 1; square < Math.sqrt((number - addition) / 2) + 1; square++) {
                    System.out.println("Considering square: " + square);
                    if (number == addition + 2 * square * square) {
                        System.out.println(String.format("%s == %s + 2 * %s^2", number, addition, square));
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Test
    public void isGoldbachNumber() {
        assertTrue(isGoldbachNumber(9));
        assertTrue(isGoldbachNumber(15));
        assertTrue(isGoldbachNumber(33));
    }

}
