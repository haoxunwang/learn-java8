package com.action.java8.chap8;

import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * 输出日志：
 * 流提供的 peek 方法在分析Stream流水线时,能将中间变量的值输出到日志中,是非常有用的工具。
 * peek 的设计初衷就是在流的每个元素恢复运行之前,插入执行一个动作。但是它不像 forEach 那样恢复整个流的运行,而是在一个元素上完成操作之后,它只会将操作顺承到流水线中的下一个操作。
 * <p>
 * Created by Nelson on 2019/3/15.
 */
public class Peek {

    public static void main(String[] args) {

        Stream.of(2, 3, 4, 5)
                .peek(x -> System.out.println("taking from stream: " + x))
                .map(x -> x + 17)
                .peek(x -> System.out.println("after map: " + x))
                .filter(x -> x % 2 == 0)
                .peek(x -> System.out.println("after filter: " + x))
                .limit(3)
                .peek(x -> System.out.println("after limit: " + x))
                .collect(toList());
    }
}
