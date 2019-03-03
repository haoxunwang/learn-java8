package com.action.java8.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.IntPredicate;
import java.util.function.Predicate;

/**
 * {@link Predicate} T -> Boolean
 * <p>
 * {@link IntPredicate} {@link java.util.function.DoublePredicate}
 * <p>
 * Created by Nelson on 2019/2/28.
 */
public class PredicateInterface {

    public static void main(String[] args) {
        List<String> listOfStrings = Arrays.asList("", "123");
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println(nonEmpty);

        // 无装箱 true
        IntPredicate evenNumbers = (int i) -> i % 2 == 0;
        evenNumbers.test(1000);

        // 装箱 false
        Predicate<Integer> oddNumbers = (Integer i) -> i % 2 == 0;
        oddNumbers.test(1000);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (p.test(t)) {
                results.add(t);
            }
        }
        return results;
    }
}
