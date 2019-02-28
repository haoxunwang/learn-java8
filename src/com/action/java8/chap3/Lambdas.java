package com.action.java8.chap3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Chapter 3: Lambda 表达式
 * <pre>
 *     1.函数式接口，类型推断
 *     2.方法引用
 *     3.Lambda表达式（函数式接口一个具体实现的实例）
 *      * 匿名
 *      * 函数-Lambda表达式有参数列表、函数主体、返回类型，还可能有抛出的异常列表。
 *      * 传递-Lambda表达式可以作为参数传递给方法或存储在变量中。
 *      * 简洁
 *     4.在哪里使用 Lambda？
 *      > 在「函数式接口」上使用 Lambda表达式，函数式接口就是只定义一个抽象方法的接口，例如Comparator和Runnable等。
 *
 *     <code>
 *         (参数) -> 主体
 *         说明：主体可以是表达式、语句。语句要使用花括号 { }
 *     </code>
 * </pre>
 * Created by Nelson on 2019/2/28.
 */
public class Lambdas {

    public static void main(String[] args) {
        // Simple example
        Runnable r = () -> System.out.println("Hello!");
        r.run();

        // Filtering with lambdas
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(120, "red"));

        // [Apple{color='green', weight=80}, Apple{color='green', weight=155}]
        List<Apple> greenApples = filter(inventory, (Apple a) -> "green".equals(a.getColor()));
        System.out.println(greenApples);

        Comparator<Apple> c = (Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight());
        inventory.sort(c);

        // [Apple{color='green', weight=80}, Apple{color='red', weight=120}, Apple{color='green', weight=155}]
        System.out.println(inventory);
    }

    public static List<Apple> filter(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
            }
        }

        return result;
    }


    static class Apple {
        private int weight = 0;
        private String color = "";

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

    interface ApplePredicate {
        boolean test(Apple a);
    }
}
