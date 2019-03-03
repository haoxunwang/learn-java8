package com.action.java8.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * {@link java.util.function.Supplier} () -> T
 * <p>
 * Created by Nelson on 2019/3/1.
 */
public class SupplierInterface {

    public static void main(String[] args) {

        // 空构造函数 Apple()
        // lambda
        Supplier<Apple> c1 = () -> new Apple();
        Apple a1 = c1.get();
        System.out.println(a1);

        // 方法引用，构造函数引用
        Supplier<Apple> c2 = Apple::new;
        Apple a2 = c2.get();
        System.out.println(a2);

        // Apple(int weight)
        //Function<Integer, Apple> c3 = (Integer weight) -> new Apple(weight);
        Function<Integer, Apple> c3 = (weight) -> new Apple(weight);
        Apple a3 = c3.apply(110);
        System.out.println(a3);

        Function<Integer, Apple> c4 = Apple::new;
        Apple a4 = c4.apply(110);
        System.out.println(a4);

        // Apple(color, weight)
        BiFunction<String, Integer, Apple> c5 = (color, weight) -> new Apple(weight, color);
        Apple a5 = c5.apply("green", 100);
        System.out.println(a5);

        BiFunction<Integer, String, Apple> c6 = Apple::new;
        Apple a6 = c6.apply(100, "red");
        System.out.println(a6);

        // create apples
        List<Integer> weights = Arrays.asList(1, 2, 3, 4);
        List<Apple> apples = map(weights, Apple::new);
        System.out.println(apples);

        List<Apple> apples2 = map(weights, new Function<Integer, Apple>() {
            @Override
            public Apple apply(Integer integer) {
                return new Apple(integer);
            }
        });
        System.out.println(apples2);
    }

    private static List<Apple> map(List<Integer> list, Function<Integer, Apple> f) {
        List<Apple> result = new ArrayList<>();
        for (Integer i : list) {
            result.add(f.apply(i));
        }
        return result;
    }

    static class Apple {
        private int weight = 0;
        private String color = "";

        public Apple() {

        }

        public Apple(int weight) {
            this.weight = weight;
        }

        public Apple(int weight, String color) {
            this.weight = weight;
            this.color = color;
        }

        public Integer getWeight() {
            return weight;
        }

        public void setWeight(Integer weight) {
            this.weight = weight;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public String toString() {
            return "Apple{" +
                    "color='" + color + '\'' +
                    ", weight=" + weight +
                    '}';
        }
    }
}
