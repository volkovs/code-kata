package projectx.problem;

import com.google.common.collect.Sets;
import projectx.utils.PermUtils;
import projectx.utils.PermutationGenerator;
import projectx.utils.PrimeUtils;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Mihails Volkovs on 2015.02.21.
 */
public class Problem49 {

    private static final int UPPER_LIMIT = 4;

    public static final void main(String... args) {
        PermutationGenerator generator = new PermutationGenerator(1234567890, UPPER_LIMIT);
        generator.generate();
        Set<Integer> permutations = Sets.newHashSet(generator.getPermutationsAsInts(false));
        System.out.println("Generated permutations count: " + permutations.size());

        double pow = Math.pow(10, UPPER_LIMIT);
        System.out.println(pow);
        for (int permutation : permutations) {
            if (PrimeUtils.isPrime(permutation)) {
                Set<Integer> legalPermutations = PermUtils.getPermutations("" + permutation)
                        .stream()
                        .map(string -> Integer.valueOf(string))
                        .collect(Collectors.toSet());
                for (int addition = 1; addition < pow; addition++) {
                    int sequenceItem = permutation;
                    int currentSequence = 0;
                    String concatenation = "";
                    while (legalPermutations.contains(sequenceItem) && PrimeUtils.isPrime(sequenceItem)) {
                        concatenation += sequenceItem;
                        currentSequence++;
                        sequenceItem += addition;
                    }
                    if (currentSequence > 2) {
                        System.out.println(String.format("Found sequence of size %s: %s + %s = %s ", currentSequence, permutation, addition, concatenation));
                    }
                }
            }
        }
    }

}
