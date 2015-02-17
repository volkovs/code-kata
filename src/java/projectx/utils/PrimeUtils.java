package projectx.utils;

/**
 * Created by Mihails Volkovs on 2015.01.12.
 */
public class PrimeUtils {

    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

}
