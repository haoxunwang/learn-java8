package com.action.java8.chap8;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * 工厂模式：
 * 使用工厂模式,你无需向客户暴露实例化的逻辑就能完成对象的创建。
 * <p>
 * Created by Nelson on 2019/3/15.
 */
public class FactoryMain {

    public static void main(String[] args) {

        Product p1 = ProductFactory.createProduct("loan");
        System.out.println("Result product name: " + p1.getName());

        Supplier<Product> stockSupplier = Stock::new;
        Product p2 = stockSupplier.get();
        System.out.println("Result product name: " + p2.getName());

        Product p3 = ProductFactory.createProductLambda("bond");
        System.out.println("Result product name: " + p3.getName());
    }

    static private class ProductFactory {

        // 1、改写前
        public static Product createProduct(String name) {
            switch (name) {
                case "loan":
                    return new Loan();
                case "stock":
                    return new Stock();
                case "bond":
                    return new Bond();
                default:
                    throw new RuntimeException("No such product " + name);
            }
        }

        // 2、改写后
        public static Product createProductLambda(String name) {
            Supplier<Product> p = map.get(name);
            if (p != null) return p.get();
            throw new RuntimeException("No such product " + name);
        }
    }


    private interface Product {
        public String getName();
    }

    static private class Loan implements Product {
        @Override
        public String getName() {
            return Loan.class.getName();
        }
    }

    static private class Stock implements Product {

        @Override
        public String getName() {
            return Stock.class.getName();
        }
    }

    static private class Bond implements Product {

        @Override
        public String getName() {
            return Bond.class.getName();
        }
    }

    final static private Map<String, Supplier<Product>> map = new HashMap<>();

    static {
        map.put("loan", Loan::new);
        map.put("stock", Stock::new);
        map.put("bond", Bond::new);
    }
}
