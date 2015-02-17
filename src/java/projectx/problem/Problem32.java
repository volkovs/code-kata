package projectx.problem;

import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.*;

public class Problem32 {

    private static final long UPPER_LIMIT = 9876;

    private static final String[] digits = ("987654321").split("");

    private static final Set<Long> products = Sets.newHashSet();

    public static void main(String... args) {
        for (int multiplier1 = 1; multiplier1 < UPPER_LIMIT; multiplier1++) {
            for (int multiplier2 = 1; multiplier2 < UPPER_LIMIT; multiplier2++) {
                long product = multiplier1 * multiplier2;
                if (isPandigital9("" + multiplier1 + multiplier2 + product)) {
                    products.add(product);
                    System.out.println(String.format("Found %s x %s = %s", multiplier1, multiplier2, product));
                }
            }
        }
        long total = products.stream().mapToLong(product -> product.longValue()).sum();
        System.out.println(String.format("Found %s products. Total: %s", products.size(), total));
    }

    private static boolean isPandigital9(String candidate) {
        if (candidate.length() != 9) {
            return false;
        }
        if (candidate.contains("0")) {
            return false;
        }

        for (String digit : digits) {
            if (!candidate.contains(digit)) {
                return false;
            }
        }
        return true;
    }

    @Test
    public void isPandigital9() {
        assertTrue(isPandigital9("391867254"));
        assertFalse(isPandigital9("391867250"));
        assertFalse(isPandigital9("39186725"));
        assertFalse(isPandigital9("3918672541"));
        assertFalse(isPandigital9("986719867"));
    }

}
