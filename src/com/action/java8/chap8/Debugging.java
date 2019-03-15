package com.action.java8.chap8;

import java.util.Arrays;
import java.util.List;

/**
 * 调试：
 * 查看栈跟踪
 * 由于Lambda表达式没有名字, 编译器只能为它们指定一个名字，比如lambda$main$0
 * <p>
 * Created by Nelson on 2019/3/15.
 */
public class Debugging {

    public static void main(String[] args) {
        List<Point> points = Arrays.asList(new Point(12, 2), null);
        points.stream()
                .map(p -> p.getX())
                .forEach(System.out::println);
    }

    private static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }
    }
}
