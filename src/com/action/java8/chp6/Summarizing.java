package com.action.java8.chp6;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;

/**
 * 归约和汇总
 * <p>
 * Created by Nelson on 2019/3/11.
 */
public class Summarizing {

    public static void main(String[] args) {
        System.out.println("Nr. of dishes: " + howManyDishes());
        System.out.println("The most caloric dish is: " + findMostCaloricDish());
        System.out.println("The most caloric dish is: " + findMostCaloricDishUsingComparator());
        System.out.println("Total calories in menu: " + calculateTotalCalories());
        System.out.println("Average calories in menu: " + calculateAverageCalories());
        System.out.println("Menu statistics: " + calculateMenuStatistics());
        System.out.println("Short menu: " + getShortMenu());
        System.out.println("Short menu comma separated: " + getShortMenuCommaSeparated());
    }

    private static long howManyDishes() {
        return Dish.menu.stream().collect(counting());
    }

    private static Dish findMostCaloricDish() {
        return Dish.menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
    }

    private static Dish findMostCaloricDishUsingComparator() {
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        return Dish.menu.stream().collect(reducing(moreCaloricOf)).get();
    }

    private static int calculateTotalCalories() {
        return Dish.menu.stream().collect(summingInt(Dish::getCalories));
    }

    private static Double calculateAverageCalories() {
        return Dish.menu.stream().collect(averagingInt(Dish::getCalories));
    }

    private static IntSummaryStatistics calculateMenuStatistics() {
        // {count=9, sum=4300, min=120, average=477.777778, max=800}
        return Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
    }

    private static String getShortMenu() {
        return Dish.menu.stream().map(Dish::getName).collect(joining());
    }

    private static String getShortMenuCommaSeparated() {
        return Dish.menu.stream().map(Dish::getName).collect(joining(", "));
    }
}
