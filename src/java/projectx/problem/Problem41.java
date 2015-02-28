package projectx.problem;

import projectx.utils.PermUtils;
import projectx.utils.PrimeUtils;

import java.util.List;

/**
 * Created by Mihails Volkovs on 2015.02.19.
 */
public class Problem41 {

    private static final int UPPER_LIMIT = 9;

    public static void main(String... args) {
        for (int digits = UPPER_LIMIT; digits > 0; digits--) {
            String salt = "";
            for (int i = digits; i > 0; i--) {
                salt += i;
            }
            System.out.println("Generator: " + salt);

            List<String> permutations = PermUtils.getPermutations(salt);
            System.out.println("Generated permutations count: " + permutations.size());
            for (String permutation : permutations) {
                long candidate = Long.parseLong(permutation);
                if (PrimeUtils.isPrime(candidate)) {
                    System.out.println("Found prime pandigital: " + candidate);
                    return;
                }
            }
        }
    }

}
