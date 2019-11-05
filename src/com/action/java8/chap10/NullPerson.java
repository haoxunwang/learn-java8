package com.action.java8.chap10;

/**
 * Java 7 之前代码演示。
 * <p>
 * 一个拥有汽车及汽车保险的客户的例子。
 * <p>
 * Created by Nelson on 2019/11/5.
 */
public class NullPerson {

    public String getCarInsuranceName(Person person) {
        // 1. 高风险 NullPointerException
        //return person.getCar().getInsurance().getName();

        // 2. 防御式检查减少 NPE，是不是陷入自我「深层质疑」，不断重复着一种模式，每次都不确定是否为 null，一步步嵌套 if，你烦不烦？？？
//        if (person != null) {
//            Car car = person.getCar();
//            if (car != null) {
//                Insurance insurance = car.getInsurance();
//                if (insurance != null) {
//                    return insurance.getName();
//                }
//            }
//        }
//
//        return "Unknown";

        // 3. null-安全第二种尝试，过多的退出语句
        // 采用不同策略，没遇到 null 变量都返回，现在有了四个截然不同的退出点，使得代码的维护异常艰难
        // 发生 null 时返回的默认值，字符串 "Unknown" 在三个不同地方重复出现-出现拼写错误概率不小！
        if (person == null) {
            return "Unknown";
        }

        Car car = person.getCar();
        if (car == null) {
            return "Unknown";
        }

        Insurance insurance = car.getInsurance();
        if (insurance == null) {
            return "Unknown";
        }

        return insurance.getName();
    }

    public class Person {
        private Car car;

        public Car getCar() {
            return car;
        }
    }

    public class Car {
        private Insurance insurance;

        public Insurance getInsurance() {
            return insurance;
        }
    }

    public class Insurance {
        private String name;

        public String getName() {
            return name;
        }
    }
}
