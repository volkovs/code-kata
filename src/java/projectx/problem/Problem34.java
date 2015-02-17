package projectx.problem;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class Problem34 {

    private static final int UPPER_LIMIT = Integer.MAX_VALUE;

    private static Map<Integer, Long> cachedFactorials = Maps.newHashMap();

    public static final void main(String... args) {

        List<Integer> result = Lists.newArrayList();

        for (int number = 3; number < UPPER_LIMIT; number++) {
            String numberAsString = "" + number;
            char[] digits = numberAsString.toCharArray();

            long factorialSum = 0;
            for (char digit : digits) {
                factorialSum += factorial(Integer.parseInt("" + digit));
            }

            if (factorialSum == number) {
                result.add(number);
                System.out.println("Found: " + number);
            }

            if (number % 1000000 == 0) {
                int progress = (int) ((0.0 + number) * 100 / Integer.MAX_VALUE);
                System.out.println(String.format("Scanning number %s (%s %%)", number, progress));
            }
        }

        int finalResult = result.stream().mapToInt(Integer::intValue).sum();
        System.out.println("Final result is " + finalResult);
    }

    private static long factorial(int number) {

        // checking in cache
        if (cachedFactorials.containsKey(number)) {
            return cachedFactorials.get(number);
        }

        long result = 1;
        for (int i = 2; i <= number; i++) {
            result *= i;
        }
        return result;
    }

    @Test
    public void factorial() {
        assertEquals(120, factorial(5));
    }

}
