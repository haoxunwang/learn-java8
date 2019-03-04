package com.action.java8.chap5;

import com.action.java8.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.action.java8.chap4.Dish.menu;

/**
 * 归约：
 * 将流归约成一个值，用函数式编程语言的术语来说，这称为折叠（fold）
 * <p>
 * Created by Nelson on 2019/3/3.
 */
public class Reducing {

    public static void main(String[] args) {

        List<Integer> numbers = Arrays.asList(3, 4, 5, 1, 2);
        int sum = numbers.stream().reduce(0, (a, b) -> a + b);
        System.out.println(sum);

        int sum2 = numbers.stream().reduce(0, Integer::sum);
        System.out.println(sum2);

        int max = numbers.stream().reduce(0, (a, b) -> Integer.max(a, b));
        System.out.println(max);

        // 在没有任何元素的情况下，reduce操作无法返回其和，因为它没有初始值，这就是为什么结果被包裹在一个Optional对象里，以表明和可能不存在。
        Optional<Integer> min = numbers.stream().reduce(Integer::min);
        min.ifPresent(System.out::println);

        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);
        System.out.println("Number of calories: " + calories);
    }
}
