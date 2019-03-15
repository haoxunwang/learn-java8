package com.action.java8.chap7;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 求累加和
 * <p>
 * Created by Nelson on 2019/3/13.
 */
public class ParallelStreams {

    public static long iterativeSum(long n) {
        long result = 0;
        for (long i = 0; i <= n; i++) {
            result += i;
        }
        return result;
    }

    /**
     * 顺序流迭代
     *
     * @param n
     * @return
     */
    public static long sequentialSum(long n) {
        // iterate 生成的是装箱的对象，必须拆箱成数字才能求和
        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(0L, Long::sum);
//        return Stream.iterate(1L, i -> i + 1).limit(n).reduce(Long::sum).get();
    }

    /**
     * 并行流
     *
     * @param n
     * @return
     */
    public static long parallelSum(long n) {
        return Stream.iterate(1L, i -> i + 1).limit(n).parallel().reduce(Long::sum).get();

    }

    public static long rangedSum(long n) {
        return LongStream.rangeClosed(1, n).reduce(Long::sum).getAsLong();
    }

    public static long parallelRangedSum(long n) {
        return LongStream.rangeClosed(1, n).parallel().reduce(Long::sum).getAsLong();
    }

    public static long sideEffectSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).forEach(accumulator::add);
        return accumulator.total;
    }

    public static long sideEffectParallelSum(long n) {
        Accumulator accumulator = new Accumulator();
        LongStream.rangeClosed(1, n).parallel().forEach(accumulator::add);
        return accumulator.total;
    }

    public static class Accumulator {
        private long total = 0;

        public void add(long value) {
            total += value;
        }
    }
}
