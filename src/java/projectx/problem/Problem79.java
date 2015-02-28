package projectx.problem;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import org.apache.commons.io.IOUtils;
import org.junit.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.regex.Pattern;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Created by Mihails Volkovs on 2015.02.27.
 */
public class Problem79 {

    private static List<Predicate<String>> predicates;

    public static final void main(String... args) throws IOException, URISyntaxException {
        predicates = createPredicates();
        System.out.println(predicates.size());
        for (int candidate = 111; candidate < Integer.MAX_VALUE; candidate++) {
            if (matches(candidate + "")) {
                System.out.println("Found pass code: " + candidate);
                return;
            }
        }
    }

    private static List<Predicate<String>> createPredicates() throws IOException, URISyntaxException {
        List<String> codes = IOUtils.readLines(new URL("https://projecteuler.net/project/resources/p079_keylog.txt").openConnection().getInputStream());
        List<Predicate<String>> predicates = Lists.newArrayList();
        for (String code : codes) {
            predicates.add(new PassCodePredicate(code));
        }
        return predicates;
    }

    private static boolean matches(String candidate) {
        int i = 1;
        for (Predicate<String> predicate : predicates) {
            if (!predicate.apply(candidate)) {
                if (i > 10) {
                    System.out.println("Failed predicate " + i + " " + predicate + " by candidate: " + candidate);
                }
                return false;
            }
            i++;
        }
        return true;
    }

    @Test
    public void predicate() {
        PassCodePredicate predicate = new PassCodePredicate("319");
        assertTrue(predicate.apply("319"));
        assertTrue(predicate.apply("0301090"));
        assertTrue(predicate.apply("9182319"));
        assertTrue(predicate.apply("9131927"));
        assertTrue(predicate.apply("9132179"));
        assertFalse(predicate.apply("391"));
    }

    private static class PassCodePredicate implements Predicate<String> {

        private final Pattern pattern;

        public PassCodePredicate(String key) {
            StringBuilder pattern = new StringBuilder();
            for (String digit : key.split("")) {
                pattern.append(".*").append(digit);
            }
            this.pattern = Pattern.compile(pattern.toString());
        }

        @Override
        public boolean apply(String targetPassCode) {
            return pattern.matcher(targetPassCode).find();
        }

        @Override
        public String toString() {
            return pattern.pattern();
        }

    }

}
