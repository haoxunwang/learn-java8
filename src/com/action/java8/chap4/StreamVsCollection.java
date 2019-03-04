package com.action.java8.chap4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流只能遍历一次
 * <p>
 * Created by Nelson on 2019/3/3.
 */
public class StreamVsCollection {

    public static void main(String[] args) {
        List<String> names = Arrays.asList("Java8", "Lambdas", "In", "Action");
        Stream<String> stream = names.stream();
        stream.forEach(System.out::println);
        // java.lang.IllegalStateException: stream has already been operated upon or closed
        //stream.forEach(System.out::println);

        // 中间操作
        List<String> dishNames = Dish.menu.stream()
                .filter(d -> {
                    System.out.println("filtering " + d.getName());
                    return d.getCalories() > 300;
                })
                .map(d -> {
                    System.out.println("mapping " + d.getName());
                    return d.getName();
                })
                .limit(3)
                .collect(Collectors.toList());
        System.out.println(dishNames);

        // 终端操作
        Dish.menu.forEach(System.out::println);
    }
}
