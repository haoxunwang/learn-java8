package com.action.java8.chap3;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Created by Nelson on 2019/3/1.
 */
public class AdvancedLambda {

    static Map<String, Function<Integer, Fruit>> map = new HashMap<>();

    static {
        map.put("apple", Apple::new);
        map.put("orange", Apple::new);
        // etc...
    }

    public static Fruit giveMeFruit(String fruit, Integer weight) {
        Function<Integer, Fruit> f = map.get(fruit.toLowerCase());
        return f.apply(weight);
    }

    public static void main(String[] args) {
        Fruit apple = giveMeFruit("apple", 200);
        System.out.println(apple);

        Fruit orange = giveMeFruit("orange", 100);
        System.out.println(orange);
    }

    static class Fruit {

        int weight;

        Fruit(int weight) {
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "Fruit{" +
                    "weight='" + weight + '\''
                    + '}';
        }
    }

    static class Apple extends Fruit {

        Apple(int weight) {
            super(weight);
        }
    }

    static class Orange extends Fruit {

        Orange(int weight) {
            super(weight);
        }
    }
}
