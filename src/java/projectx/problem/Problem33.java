package projectx.problem;

/**
 * Created by Mihails Volkovs on 2015.02.22.
 */
public class Problem33 {

    private static final int UPPER_LIMIT = 100;

    public static final void main(String... args) {
        long productNumerator = 1;
        long productDenominator = 1;
        long productShortNumerator = 1;
        long productShortDenominator = 1;

        for (int numerator = 11; numerator < UPPER_LIMIT; numerator++) {
            for(int denominator = numerator + 1; denominator < UPPER_LIMIT; denominator++) {
                for (int commonDivider = 1; commonDivider < 10; commonDivider++) {
                    String numeratorAsString = "" + numerator;
                    String denomenatorAsString = "" + denominator;
                    String commonDividerAsString = "" + commonDivider;
                    if (numeratorAsString.contains(commonDividerAsString) && denomenatorAsString.contains(commonDividerAsString)) {
                        Double newNumerator =  Double.parseDouble(numeratorAsString.replaceFirst(commonDividerAsString, ""));
                        Double newDenominator =  Double.parseDouble(denomenatorAsString.replaceFirst(commonDividerAsString, ""));
                        if (newNumerator / newDenominator == (0.0 + numerator) / denominator) {
                            productNumerator *= numerator;
                            productDenominator *= denominator;
                            productShortNumerator *= newNumerator;
                            productShortDenominator *= newDenominator;
                            System.out.println(String.format("Found new cancelation: %s/%s = %s/%s", numerator, denominator, newNumerator, newDenominator));
                        }
                    }
                }
            }
        }

        System.out.println(String.format("Found new cancelation: %s/%s = %s/%s", productNumerator, productDenominator, productShortNumerator, productShortDenominator));
    }

}
