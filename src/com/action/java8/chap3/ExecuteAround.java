package com.action.java8.chap3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * 读取文件「-行」、「二行」...来行为参数化
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

    interface BufferedReaderProcessor {
        String process(BufferedReader b) throws IOException;
    }

}
