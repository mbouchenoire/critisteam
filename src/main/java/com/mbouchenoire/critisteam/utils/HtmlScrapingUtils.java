package com.mbouchenoire.critisteam.utils;

import com.google.common.primitives.Doubles;
import com.google.common.primitives.Ints;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author mbouchenoire
 */
public class HtmlScrapingUtils {

    public static int[] findNumbers(final String text) {
        if (text == null)
            throw new IllegalArgumentException("Text to parse cannot be null.");

        String adaptedText = text.replace(",", "");
        adaptedText = adaptedText.replaceAll("[^0-9]+", " ");

        final String[] splittedText = adaptedText.trim().split(" ");

        final List<Integer> wrappedIntegers = new ArrayList<Integer>();

        for(String textPart: splittedText) {
            final Integer wrappedInteger = Integer.parseInt(textPart);
            wrappedIntegers.add(wrappedInteger);
        }

        return Ints.toArray(wrappedIntegers);
    }

    public static double[] findDoubles(final String text) {
        if (text == null)
            throw new IllegalArgumentException("Text to parse cannot be null.");

        // This is not an elegant solution to a Localization problem
        final Scanner stPeriods = new Scanner(text.replace(",", "."));
        final Scanner stCommas = new Scanner(text.replace(".", ","));

        final double[] doublesWithPeriods = findDoubles(stPeriods);
        final double[] doublesWithCommas = findDoubles(stCommas);

        if (doublesWithCommas.length > 0) {
            return doublesWithCommas;
        } else if (doublesWithPeriods.length > 0) {
            return doublesWithPeriods;
        } else {
            return new double[0];
        }
    }

    private static double[] findDoubles(final Scanner scanner) {
        final List<Double> wrappedDoubles = new ArrayList<Double>();

        while (scanner.hasNextDouble()) {
            double value = scanner.nextDouble();
            wrappedDoubles.add(value);
        }

        return Doubles.toArray(wrappedDoubles);
    }
}
