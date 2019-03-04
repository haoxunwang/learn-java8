package com.action.java8.chap5;

import com.action.java8.chap4.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static com.action.java8.chap4.Dish.menu;

/**
 * 查找和匹配
 * allMatch、anyMatch、noneMatch、findFirst、findAny等方法
 * <p>
 * Created by Nelson on 2019/3/3.
 */
public class Finding {

    public static void main(String[] args) {

        // anyMatch 是否至少匹配一个元素
        if (isVegetarianFriendlyMenu()) {
            System.out.println("The menu is (somewhat) vegetarian friendly!!");
        }

        // allMatch (&& 和 ||)
        System.out.println(isHealthyMenu());
        System.out.println(isHealthyMenu2());

        Optional<Dish> dish = findVegetarianDish();
        dish.ifPresent(System.out::println);

        List<Integer> someNumbers = Arrays.asList(1, 2, 3, 4, 5);
        Optional<Integer> firstSquareDivisibleByThree = someNumbers.stream()
                .map(x -> x * x)
                .filter(x -> x % 3 == 0)
                .findFirst(); // 9
        firstSquareDivisibleByThree.ifPresent(System.out::println);


    }

    private static boolean isVegetarianFriendlyMenu() {
        return menu.stream().anyMatch(Dish::isVegetarian);
    }

    /**
     * 检查谓词是否匹配所有元素
     *
     * @return
     */
    private static boolean isHealthyMenu() {
        return menu.stream().allMatch(d -> d.getCalories() < 1000);
    }

    /**
     * 确保流中没有任何元素与给定的谓词匹配
     *
     * @return
     */
    private static boolean isHealthyMenu2() {
        return menu.stream().noneMatch(d -> d.getCalories() >= 1000);
    }

    private static Optional<Dish> findVegetarianDish() {
        return menu.stream().filter(Dish::isVegetarian).findAny();
    }
}
