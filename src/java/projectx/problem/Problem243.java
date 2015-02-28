package projectx.problem;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by Mihails Volkovs on 2015.01.16.
 */
public class Problem243 {

    //    private static double targetRatio = 4.0 / 10.0;
    private static double targetRatio = 15499.0 / 94744.0;

    private static double minimalRatio = Double.MAX_VALUE;

    private static Map<Integer, List<Integer>> cachedCommonDividers = new HashMap<>();

    //    private static List<Integer> primeNumbers = new ArrayList<>();
    private static LinkedList<Integer> primeNumbers = new LinkedList<>();

    static {
        primeNumbers.add(2);
    }

    public static void main(String[] args) {
        int denominator = 2;
        double ratio = Double.MAX_VALUE;
        System.out.println("Target ratio: " + targetRatio);
        while (ratio >= targetRatio) {
            ratio = getRatio(++denominator);
//            System.out.println("Current ratio: " + ratio);
        }

        System.out.println("Smallest denominator: " + denominator + ", final ratio: " + ratio);
    }

    private static double getRatio(int denominator) {
        long now = System.nanoTime();

        extendPrimeNumbers(denominator);

        int simplifiedCount = 0;
        for (int nominator = 2; nominator < denominator; nominator++) {
            for (int primeNumber : primeNumbers) {
                if (nominator < primeNumber) {
//                    System.out.println("Nominator " + nominator + " < " + ((primeNumber - 1) * 2));
                    break;
                }
                if (denominator % primeNumber == 0 && nominator % primeNumber == 0) {
                    simplifiedCount++;
//                    System.out.println("Common divider exists for " + nominator + " and " + denominator);
                    break;
                }
            }
        }

        int resilientCount = denominator - 1 - simplifiedCount;
        double result = (0.0 + resilientCount) / (denominator - 1);
        if (result < minimalRatio) {
            minimalRatio = result;
        }
        long now2 = System.nanoTime();
//        System.out.println("For denominator: " + denominator + " resilient count is " + resilientCount);
        if (denominator % 1000 == 0) {
            System.out.println("Current denominator: " + denominator + ", Current minimal ratio: " + minimalRatio + ", Target ratio: " + targetRatio + ", One ratio calculation takes (ms): " + (now2 - now + 0.0) / 1000000);
        }
        return result;
    }

    private static void extendPrimeNumbers(int denominator) {
        Integer lastPrimeNumber = primeNumbers.getLast();
//        Integer upperLimit = denominator / 2;
        double upperLimit = Math.sqrt(denominator);
        for (int candidate = lastPrimeNumber + 1; candidate <= upperLimit; candidate++) {
            if (isPrime(candidate)) {
                primeNumbers.add(candidate);
            }
        }

//        System.out.println("Prime numbers extended: " + primeNumbers + " for denominator: " + denominator);
    }

    private static boolean isPrime(int candidate) {
        for (int divider : primeNumbers) {
            if (candidate % divider == 0) {
                return false;
            }
            if (candidate < divider * 2) {
                break;
            }
        }
        return true;
    }

    private static boolean isResilient(int nominator, int denominator) {
        return false;
    }

}
