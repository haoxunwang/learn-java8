package com.action.java8.chap10;

import java.util.Optional;

/**
 * 例如你需要处理如下这样的嵌套对象，这是一个拥有汽车及汽车保险的客户。
 * <p>
 * Created by Nelson on 2019/4/3.
 */
public class Person {

    private Optional<Car> car;

    public Optional<Car> getCar() {
        return car;
    }
}
