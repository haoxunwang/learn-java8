package com.action.java8.chap3;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * {@link Consumer} T -> Void
 * <p>
 * Created by Nelson on 2019/2/28.
 */
public class ConsumerInterface {

    public static void main(String[] args) {

        forEach(Arrays.asList(1, 2, 3, 4, 5), ((Integer i) -> System.out.println(i)));
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }
}
