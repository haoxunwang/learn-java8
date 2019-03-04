package com.action.java8.chap5;


import com.action.java8.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static com.action.java8.chap4.Dish.menu;
import static java.util.stream.Collectors.toList;

/**
 * 映射:
 * 对流中每一个元素应用函数
 * <p>
 * Created by Nelson on 2019/3/3.
 */
public class Mapping {

    public static void main(String[] rgs) {
        // map
        List<String> dishNames = menu.stream()
                .map(Dish::getName)
                .collect(toList());
        dishNames.forEach(System.out::println);
        System.out.println("---");

        // map
        List<String> words = Arrays.asList("Hello", "World");
        List<Integer> wordLengths = words.stream()
                .map(String::length)
                .collect(toList());
        wordLengths.forEach(System.out::println);
        System.out.println("---");


        // flatMap error
        List<String> words2 = Arrays.asList("map", "flatMap");
        words2.stream()
                .map(word -> word.split(""))
                .distinct()
                .forEach(System.out::println);
        System.out.println("---");

        // Arrays.stream([])
        String[] arrayOfWords = {"Goodbye", "World"};
        Stream<String> streamOfWords = Arrays.stream(arrayOfWords);
        streamOfWords.forEach(System.out::println);
        System.out.println("---");

        words2.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
        System.out.println("---");

        // flatMap 各个数组并不是分别映射成一个流，而是映射成流的内容。所有使用map(Arrays::stream)时生成的单个流都被合并起来，即扁平化为一个流。
        words2.stream()
                .map(w -> w.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .forEach(System.out::println);
        System.out.println("---");

        // flatMap
        List<Integer> numbers1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> numbers2 = Arrays.asList(6, 7, 8);

        // square
        List<Integer> squares = numbers1.stream()
                .map(n -> n * n)
                .collect(toList());

        List<int[]> pairs = numbers1.stream()
                .flatMap(i -> numbers2.stream().map(j -> new int[]{i, j}))
                .collect(toList());
        pairs.forEach(System.out::println);
        System.out.println("---");

        List<int[]> pairs2 = numbers1.stream()
                .flatMap(i -> numbers2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[]{i, j}))
                .collect(toList());
        pairs2.forEach(System.out::println);
        System.out.println("---");

    }
}
