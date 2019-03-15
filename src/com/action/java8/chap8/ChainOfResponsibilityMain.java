package com.action.java8.chap8;

import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * 责任链模式：
 * 责任链模式是一种创建处理对象序列(比如操作序列)的通用方案。
 * <p>
 * Created by Nelson on 2019/3/15.
 */
public class ChainOfResponsibilityMain {

    public static void main(String[] args) {

        // 改造前
        ProcessingObject<String> p1 = new HeaderTextProcessing();
        ProcessingObject<String> p2 = new SpellCheckerProcessing();
        p1.setSuccessor(p2);
        String result1 = p1.handle("Aren't labdas rellay sexy?!!");
        System.out.println(result1);

        // 改造后
        UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
        UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
        Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
        String result2 = pipeline.apply("Aren't labdas rellay sexy?!!");
        System.out.println(result2);
    }


    static private class HeaderTextProcessing extends ProcessingObject<String> {

        @Override
        protected String handleWork(String text) {
            return "From Raoul, Mario and Alan: " + text;
        }
    }

    static private class SpellCheckerProcessing extends ProcessingObject<String> {

        @Override
        protected String handleWork(String text) {
            return text.replaceAll("labda", "lambda");
        }
    }

    static private abstract class ProcessingObject<T> {
        private ProcessingObject<T> successor;

        public void setSuccessor(ProcessingObject<T> successor) {
            this.successor = successor;
        }

        public T handle(T input) {
            T r = handleWork(input);
            if (successor != null) {
                return successor.handle(r);
            }
            return r;
        }

        abstract protected T handleWork(T input);
    }
}
