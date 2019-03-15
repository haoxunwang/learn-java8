package com.action.java8.chap8;

import java.util.function.Consumer;

/**
 * 模板方法：
 * 如果你需要采用某个算法的框架,同时又希望有一定的灵活度,能对它的某些部分进行改进,那么采用模板方法设计模式是比较通用的方案。
 * <p>
 * Created by Nelson on 2019/3/15.
 */
public class OnlineBankingLambda {

    public static void main(String[] args) {
        new OnlineBankingLambda().processCustomer(1337, (Customer c) -> System.out.println("Hello!"));
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        // 改造后
        Customer c = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(c);
    }

    // dummy Customer class
    static private class Customer {
    }

    // dummy Databse class
    static private class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }
}
