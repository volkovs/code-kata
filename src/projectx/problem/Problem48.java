package projectx.problem;

import com.google.common.math.BigIntegerMath;

/**
 * Created by Mihails Volkovs on 2015.01.31.
 */
public class Problem48 {

    private static final int LIMIT = 10;

    private static final long RESULT_LIMIT = 10;

    public static final void main(String... args) {
//        System.out.println(Math.pow(1000, 1000));

        long result = 0;
        for (int i = 1; i <= LIMIT; i++) {
            result += Math.pow(i, i);
            while (result > Math.pow(10, RESULT_LIMIT)) {
//                System.out.println(result + " " + result / 10);
                result /= 10;
            }
        }
        System.out.println(result);
    }

    private static class MyNumber {

    }

}
