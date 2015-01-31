package projectx.problem;

import com.google.common.math.BigIntegerMath;

/**
 * Created by Mihails Volkovs on 2015.01.31.
 */
public class Problem48 {

    private static final int LIMIT = 1000;

    private static final int RESULT_LIMIT = 10;

    public static final void main(String... args) {
        long result = 0;
        for (int i = 1; i <= LIMIT; i++) {
            result += power(i, i);
            result = trim(result);
        }
        System.out.println(result);
    }

    private static long power(int principal, int power) {
        long result = principal;
        for (int i = 1; i < power; i++) {
            System.out.println(" - " + result);
            result *= principal;
            result = trim(result);
        }
        return result;
    }

    private static long trim(long tooLongNumber) {
        String number = "" + tooLongNumber;
        if (number.length() <= RESULT_LIMIT) {
            return tooLongNumber;
        }
        return Long.parseLong(number.substring(number.length() - RESULT_LIMIT - 1));
    }

}
