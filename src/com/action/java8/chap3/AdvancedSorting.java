package com.action.java8.chap3;

import com.action.java8.chap3.Sorting.Apple;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Nelson on 2019/3/1.
 */
public class AdvancedSorting {

    public static void main(String[] args) {
        List<Apple> inventory = Arrays.asList(new Apple(80, "green"),
                new Apple(155, "green"),
                new Apple(80, "red"));

        // 匿名内部类
        inventory.sort(new Comparator<Sorting.Apple>() {
            @Override
            public int compare(Sorting.Apple a1, Sorting.Apple a2) {
                return a1.getWeight().compareTo(a2.getWeight());
            }
        });

        // Lambda表达式
        inventory.sort((Apple a1, Apple a2) -> a1.getWeight().compareTo(a2.getWeight()));
        inventory.sort((a1, a2) -> a1.getWeight().compareTo(a2.getWeight()));

        // 方法引用
        inventory.sort(Comparator.comparing((Apple a) -> a.getWeight()));
        inventory.sort(Comparator.comparing(Apple::getWeight));
        System.out.println(inventory);

        // 比较器复合
        inventory.sort(Comparator.comparing(Apple::getWeight).reversed());
        inventory.sort(Comparator.comparing(Apple::getWeight)
                .reversed()
                .thenComparing(Apple::getColor));
        System.out.println(inventory);

        // 谓词复合

    }


}
