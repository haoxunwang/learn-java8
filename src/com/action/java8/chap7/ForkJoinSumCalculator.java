package com.action.java8.chap7;

import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

import static com.action.java8.chap7.ParallelStreamHarness.FORK_JOIN_POOL;

/**
 * Created by Nelson on 2019/3/13.
 */
public class ForkJoinSumCalculator extends RecursiveTask<Long> {

    private static final int THRESHOLD = 10_000;
    private final long[] numbers;
    private final int start;
    private final int end;

    public ForkJoinSumCalculator(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public ForkJoinSumCalculator(long[] numbers, int start, int end) {
        this.numbers = numbers;
        this.start = start;
        this.end = end;
    }

    /**
     * 1、把原任务分为更小的任务，直到满足不可能再进一步拆分条件
     * 2、这时会顺序计算每个任务的结果
     * 3、然后由分支过程创建的「隐含的」任务二叉树遍历回它的根，接下来会合并每个子任务的部分结果，从而得到总任务的结果。
     *
     * @return
     */
    @Override
    protected Long compute() {
        int length = end - start;
        if (length <= THRESHOLD) {
            return computeSequentially();
        }

        ForkJoinSumCalculator leftTask = new ForkJoinSumCalculator(numbers, start, start + length / 2);
        leftTask.fork();
        ForkJoinSumCalculator rightTask = new ForkJoinSumCalculator(numbers, start + length / 2, end);
        Long rightResult = rightTask.compute();
        Long leftResult = leftTask.join();
        return leftResult + rightResult;
    }

    private Long computeSequentially() {
        long sum = 0;
        for (int i = start; i < end; i++) {
            sum += numbers[i];
        }
        return sum;
    }


    public static long forkJoinSum(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();
        ForkJoinSumCalculator task = new ForkJoinSumCalculator(numbers);
        return FORK_JOIN_POOL.invoke(task);
    }

}
