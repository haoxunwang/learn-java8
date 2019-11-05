package com.action.java8.chap10;

import java.util.Optional;

/**
 * Created by Nelson on 2019/4/3.
 */
public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("unknown");
    }

//    public Set<String> getCarInsuranceNames(List<Person> persons) {
//        return persons.stream()
//                .map(Person::getCar)
//                .map(optCar -> optCar.flatMap(Car::getInsurance))
//                .map(optInsurance -> optInsurance.map(Insurance::getName))
//                .flatMap(Optional::stream)
//                .collect(toSet());
//    }
}
