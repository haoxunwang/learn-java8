package com.action.java8.chap5;

import com.action.java8.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static com.action.java8.chap4.Dish.menu;

/**
 * 数值流
 * <p>
 * Created by Nelson on 2019/3/5.
 */
public class NumericStreams {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        Arrays.stream(numbers.toArray()).forEach(System.out::println);

        int calories = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        System.out.println("Number of calories: " + calories);

        OptionalInt maxCalories = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int max;
        if (maxCalories.isPresent()) {
            max = maxCalories.getAsInt();
        } else {
            // we can choose a default value
            max = 1;
        }

        System.out.println("Max calories: " + max);

        // numeric ranges
        IntStream evenNumbers = IntStream.rangeClosed(1, 100)
                .filter(n -> n % 2 == 0);
        System.out.println(evenNumbers.count());


        // 将数值流转换为一般流 boxed -> Stream
        // 找出 1-100 所有勾股数
        Stream<int[]> pythagoreanTriples = IntStream.rangeClosed(1, 100)
                .boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .boxed()
                        .map(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));

        pythagoreanTriples.forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));
    }
}
