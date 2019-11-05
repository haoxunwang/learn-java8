package com.action.java8.chap10;

import java.util.Optional;

/**
 * 汽车
 * <p>
 * Created by Nelson on 2019/4/3.
 */
public class Car {

    private Optional<Insurance> insurance;

    public Optional<Insurance> getInsurance() {
        return insurance;
    }
}

