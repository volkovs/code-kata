package projectx.problem;

import com.google.common.collect.Maps;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mihails Volkovs on 2015.02.07.
 */
public class Problem39 {

    private static final int UPPER_LIMIT = 1000;

    public static void main(String... args) {
        Map<Integer, AtomicInteger> results = getSolutions();


        int result = results.entrySet().stream().max((entry1, entry2) -> entry1.getValue().get() - entry2.getValue().get()).get().getKey();
        System.out.println(result);
    }

    private static Map<Integer, AtomicInteger> getSolutions() {
        Map<Integer, AtomicInteger> results = Maps.newHashMap();
        for (int a = 1; a < UPPER_LIMIT; a++) {
            for (int b = 1; b < UPPER_LIMIT; b++) {
                int c = getC(a, b);
                int p = a + b + c;
                if (p < UPPER_LIMIT && c > 0) {
                    AtomicInteger counter = results.get(p);
                    if (counter == null) {
                        counter = new AtomicInteger();
                        results.put(p, counter);
                    }
                    counter.incrementAndGet();
                }
            }
        }
        return results;
    }

    private static int getC(int a, int b) {
        double result = Math.sqrt(a * a + b * b);
        int candidate = (int) Math.round(result);
        if (candidate * candidate == a * a + b * b) {
            return candidate;
        }
        return 0;
    }

    @Test
    public void getC() {
        assertEquals(52, getC(20, 48));
        assertEquals(51, getC(24, 45));
        assertEquals(50, getC(30, 40));

        assertEquals(0, getC(30, 41));
        assertEquals(0, getC(30, 39));
        assertEquals(0, getC(31, 40));
        assertEquals(0, getC(29, 40));
    }

}
