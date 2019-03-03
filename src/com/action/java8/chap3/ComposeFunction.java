package com.action.java8.chap3;

import java.util.function.DoubleFunction;
import java.util.function.Function;

/**
 * 函数复合
 * Created by Nelson on 2019/3/2.
 */
public class ComposeFunction {

    public static void main(String[] args) {

        // 函数复合 andThen f(g(x))
        Function<Integer, Integer> f1 = x -> x + 1;
        Function<Integer, Integer> g1 = x -> x * 1;
        Function<Integer, Integer> h1 = f1.andThen(g1);
        Integer result1 = h1.apply(1);
        System.out.println(result1);

        // 函数复合 compose g(f(x))
        Function<Integer, Integer> f2 = x -> x + 1;
        Function<Integer, Integer> g2 = x -> x * 1;
        Function<Integer, Integer> h2 = f2.compose(g2);
        Integer result2 = h2.apply(2);
        System.out.println(result2);

        // 写信（抬头、拼写检查、落款）
        Function<String, String> addHeader = Letter::addHeader;
        Function<String, String> transformationPiple = addHeader.andThen(Letter::checkSpelling)
                .andThen(Letter::addFooter);
        String letter1 = transformationPiple.apply("labda");
        System.out.println(letter1);

        // 数学微积分「以x为自变量，结果是 x + 10 的那个函数」
        double area = integrate((x) -> x + 10, 3, 7);
        System.out.println(area);
    }


    // integrate((double x) -> x + 10, 3, 7)
    // integrate((double x) -> f(x), 3, 7)
    // integrate(C::f, 3, 7)

//    /**
//     * 错误的Java代码！（函数的写法不能像数学里那样。）
//     * @return
//     */
//    public double integrate((double -> double)f, double a, double b){
//        return (f(a) + f(b)) * (b-a) * 2.0;
//    }

    /**
     * 可惜必须写f.apply(a)，而不是像数学里写f(a)，但Java无法摆脱「一切都是对象」的思想-它不能让函数完全独立！
     */
    public static double integrate(DoubleFunction<Double> f, double a, double b) {
        return (f.apply(a) + f.apply(b)) * (b - a) / 2.0;
    }

    static class Letter {
        public static String addHeader(String text) {
            return "From Nelson, Jolly: " + text;
        }

        public static String addFooter(String text) {
            return text + " Kind regards";
        }

        public static String checkSpelling(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }
}
