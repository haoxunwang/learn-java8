package com.action.java8.chap4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 引入流：从支持数据处理操作的源生成的元素序列。
 * <p>
 * Created by Nelson on 2019/3/3.
 */
public class StreamBasic {

    public static void main(String[] args) {

        // Java 7
        getLowCaloricDishesNamesInJava7(Dish.menu).forEach(System.out::println);
        System.out.println("---");

        // Java 8
        getLowCaloricDishesNamesInJava8(Dish.menu).forEach(System.out::println);
    }


    /**
     * 指令型编程方式：
     * 选出 400 卡路里以下菜肴 -> 按照卡路里排序 -> 提取菜肴的名称
     * 其中产生了「垃圾变量」lowCaloricDishes！！！
     *
     * @param dishes
     * @return
     */
    private static List<String> getLowCaloricDishesNamesInJava7(List<Dish> dishes) {
        List<Dish> lowCaloricDishes = new ArrayList<>();
        for (Dish dish : dishes) {
            if (dish.getCalories() < 400) {
                lowCaloricDishes.add(dish);
            }
        }

        List<String> lowCaloricDishesName = new ArrayList<>();
        Collections.sort(lowCaloricDishes, new Comparator<Dish>() {
            @Override
            public int compare(Dish d1, Dish d2) {
                return Integer.compare(d1.getCalories(), d2.getCalories());
            }
        });

        for (Dish d : lowCaloricDishes) {
            lowCaloricDishesName.add(d.getName());
        }
        return lowCaloricDishesName;
    }


    /**
     * 声明性操作集合工具
     *
     * @param dishes
     */
    private static List<String> getLowCaloricDishesNamesInJava8(List<Dish> dishes) {
        return dishes/*.stream()*/.parallelStream()
                .filter(d -> d.getCalories() < 400)
                .sorted(Comparator.comparing(Dish::getCalories))
                .map(Dish::getName)
                .collect(Collectors.toList());
    }


}
