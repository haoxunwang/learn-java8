package com.action.java8.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * {@link java.util.function.Function} T -> R
 * <p>
 * Created by Nelson on 2019/2/28.
 */
public class FunctionInterface {

    public static void main(String[] args) {
        List<Integer> l = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
        System.out.println(l);
    }

    public static <T, R> List<R> map(List<T> list, Function<T, R> f) {
        List<R> results = new ArrayList<>();
        for (T s : list) {
            results.add(f.apply(s));
        }
        return results;
    }
}
