package com.action.java8.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.function.Function;

/**
 * 读取文件「-行」、「二行」...来行为参数化
 * 「环绕执行模式」资源处理（例如处理文件或数据库）时，一个常见的模式就是打开一个资源，做一些处理，然后关闭资源。这个设置和清理阶段总是很类似的，
 * 并且会围绕着执行处理的那些重要代码。这就是所谓的环绕执行（execute around）模式。
 * <p>
 * Created by Nelson on 2019/2/28.
 */
public class ExecuteAround {

    public static void main(String[] args) throws IOException {
        String result = processFileLimited();
        System.out.println(result);

        System.out.println("---");

        // 读1行
        String oneLine = processFile((BufferedReader b) -> b.readLine());
        System.out.println(oneLine);

        // try/catch块
        Function<BufferedReader, String> f = (BufferedReader b) -> {
            try {
                return b.readLine();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        };

        // 读2行
        String twoLines = processFile((BufferedReader b) -> b.readLine() + b.readLine());
        System.out.println(oneLine);
    }

    private static String processFileLimited() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/action/java8/chap3/data.txt"))) {
            return br.readLine();
        }
    }

    private static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("src/com/action/java8/chap3/data.txt"))) {
            return p.process(br);
        }
    }

    @FunctionalInterface
    interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

}
