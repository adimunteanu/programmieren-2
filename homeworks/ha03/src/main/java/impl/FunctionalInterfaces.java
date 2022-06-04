package impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class FunctionalInterfaces {

    public static void main(String[] args) {
        //Aufgabe 3.1.1
        int modifiedNumber = applyMathToOneInt(10, i -> i + 3);
        System.out.println(modifiedNumber);

        //Aufgabe 3.1.2
        int resultOfMath = applyMathToTwoInts(10, 23, (int a, int b) -> (a % 3) + (b * 5));
        System.out.println(resultOfMath);

        //Aufgabe 3.1.3
        String concatenatedInts = concatenateInts(10, 23, (a, b) -> "First: " + a + ", Second: " + b);
        System.out.println(concatenatedInts);

        //Aufgabe 3.1.4
        List<Integer> nums = Arrays.asList(2, 3, 10, 4, 1, 6, 7, 8, 5, 9);
        List<Integer> greaterThanFive = filterIntegers(nums, i -> i > 5);
        System.out.println(greaterThanFive);
    }

    /**
     * Aufgabe 3.1.1: Wählen Sie das passende FI und wenden Sie es auf a an.
     *
     * @param i Input Integer
     * @param o Replace with correct functional interface!
     * @return Int resulting from applied functional interface
     */
    public static int applyMathToOneInt(int i, IntUnaryOperator o) {
        return o.applyAsInt(i);
    }

    /**
     * Aufgabe 3.1.2: Wählen Sie das passende FI und wenden Sie es auf a und b an.
     *
     * @param a Input Integer 1
     * @param b Input Integer 2
     * @param o Replace with correct functional interface!
     * @return Int resulting from applied functional interface
     */
    public static int applyMathToTwoInts(int a, int b, IntBinaryOperator o) {
        return o.applyAsInt(a, b);
    }

    /**
     * Aufgabe 3.1.3: Wählen Sie das passende FI und wenden Sie es auf a und b an, um sie zu einem String zu konkatenieren.
     *
     * @param a Input Integer 1
     * @param b Input Integer 2
     * @param o Replace with correct functional interface!
     * @return String resulting from applied functional interface
     */
    public static String concatenateInts(int a, int b, BiFunction<Integer, Integer, String> o) {
        return o.apply(a, b);
    }

    /**
     * Aufgabe 3.1.4: Wählen Sie das passende FI und wenden Sie es auf die Einträge von nums an, um die Liste zu filtern.
     *
     * @param nums Input List
     * @param o    Replace with correct functional interface!
     * @return List filtered by applying the functional interface to each entry
     */
    public static List<Integer> filterIntegers(List<Integer> nums, Predicate<Integer> o) {
        List<Integer> filteredIntegers = new ArrayList<>();

        for (Integer i : nums) {
            if (o.test(i)) {
                filteredIntegers.add(i);
            }
        }

        return filteredIntegers;
    }
}
