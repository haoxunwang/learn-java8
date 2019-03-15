package com.action.java8.chap7;

import java.util.concurrent.ForkJoinPool;
import java.util.function.Function;

/**
 * 测试流性能：用三种不同的方式（迭代式、顺序归纳和并行归纳）做全完相同操作，看谁快！
 * <p>
 * Created by Nelson on 2019/3/13.
 */
public class ParallelStreamHarness {

    public static final ForkJoinPool FORK_JOIN_POOL = new ForkJoinPool();

    public static void main(String[] args) {
        // 对前一千万个自然数求和
        // 50000005000000 3msecs
        System.out.println("Iterative Sum done in: " + measurePerf(ParallelStreams::iterativeSum, 10_000_000L) + "msecs");
        // 91msecs
        System.out.println("Sequential Sum done in: " + measurePerf(ParallelStreams::sequentialSum, 10_000_000L) + "msecs");
        // 148msecs
        System.out.println("Parallel forkJoinSum done in: " + measurePerf(ParallelStreams::parallelSum, 10_000_000L) + "msecs");
        // 14msecs
        System.out.println("Range forkJoinSum done in: " + measurePerf(ParallelStreams::rangedSum, 10_000_000L) + "msecs");
        // 5msecs
        System.out.println("Parallel range forkJoinSum done in: " + measurePerf(ParallelStreams::parallelRangedSum, 10_000_000L) + "msecs");
        // 20 msecs
        System.out.println("ForkJoin sum done in: " + measurePerf(ForkJoinSumCalculator::forkJoinSum, 10_000_000L) + " msecs");
        // 3 msecs
        System.out.println("SideEffect sum done in: " + measurePerf(ParallelStreams::sideEffectSum, 10_000_000L) + " msecs");
        // 43 msecs 数据发生了错乱
        System.out.println("SideEffect prallel sum done in: " + measurePerf(ParallelStreams::sideEffectParallelSum, 10_000_000L) + " msecs");
    }

    public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
        }
        // 返回最短执行时间
        return fastest;
    }
}
