package euler.problem;

import java.util.*;

/**
 * Created by Mihails Volkovs on 2015.01.16.
 */
public class Problem243 {

    private static double targetRatio = 4.0 / 10.0;
//    private static double targetRatio = 15499.0 / 94744.0;

    private static double minimalRatio = Double.MAX_VALUE;

    private static Map<Integer, List<Integer>> cachedCommonDividers = new HashMap<>();

    public static void main(String[] args) {
        int denominator = 2;
        double ratio = Double.MAX_VALUE;
        System.out.println("Target ratio: " + targetRatio);
        while(ratio >= targetRatio) {
            ratio = getRatio(++denominator);
//            System.out.println("Current ratio: " + ratio);
        }

        System.out.println("Smallest denominator: " + denominator + ", final ratio: " + ratio);
    }

    private static double getRatio(int denominator) {
        int resilientCount = 1;
        List<Integer> commonDividers = new ArrayList<Integer>();
//        System.out.println(1 + "/" + denominator);
        int halfDenominator = denominator / 2;
        long now = System.nanoTime();
        for (int nominator = 2; nominator < denominator; nominator++) {
            boolean isResilient = true;
            if (nominator <= halfDenominator && denominator % nominator == 0) {
                isResilient = false;
                commonDividers.add(nominator);
            } else {
                for (int commonDivider : commonDividers) {
                    if(nominator % commonDivider == 0) {
                        isResilient = false;
                        break;
                    }
                }
            }
            if (isResilient) {
                resilientCount++;
//                System.out.println(nominator + "/" + denominator);
            }
        }

//        System.out.println("For denominator " + denominator + " resilient count: " + resilientCount);

        double result = (0.0 + resilientCount) / (denominator - 1);
        if (result < minimalRatio) {
            minimalRatio = result;
        }
        long now2 = System.nanoTime();
        if (denominator % 1000 == 0) {
            System.out.println("Current denominator: " + denominator + ", Current minimal ratio: " + minimalRatio + ", Target ratio: " + targetRatio + ", One ratio calculation takes (ms): " + (now2 - now + 0.0) / 1000000);
        }
        return result;
    }

    private static boolean isResilient(int nominator, int denominator) {
        return false;
    }

}
