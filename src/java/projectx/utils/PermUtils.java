package projectx.utils;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Mihails Volkovs on 2015.02.19.
 */
public class PermUtils {

    public static List<String> getPermutations(String str) {
        List<String> results = Lists.newArrayList();
        permutation("", str, results);
        return results;
    }

    private static void permutation(String prefix, String str, List<String> results) {
        int n = str.length();
        if (n == 0) {
            results.add(prefix);
        } else {
            for (int i = 0; i < n; i++) {
                permutation(prefix + str.charAt(i), str.substring(0, i) + str.substring(i + 1, n), results);
            }
        }
    }

    @Test
    public void getPermutations() {
        List<String> permutations = getPermutations("12345");
        System.out.println(permutations);
        assertEquals(120, permutations.size());
    }

}
