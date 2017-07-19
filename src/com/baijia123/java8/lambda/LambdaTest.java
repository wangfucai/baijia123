package com.baijia123.java8.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaTest {
    public static void main(String[] args) {

        // 传统做法
        File[] notHiddenFiles = new File(".").listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                // TODO Auto-generated method stub
                return !pathname.isHidden();
            }

        });
        List<File> fileList = Arrays.asList(notHiddenFiles);
        // fileList.forEach(f -> System.out.println(f.getName()));

        // 使用lambda，方法传参，函数取值为非还不太清楚
        notHiddenFiles = new File(".").listFiles(File::isHidden);

        List<Integer> list = new ArrayList<Integer>() {
            {
                add(1);
                add(2);
                add(3);
            }
        };
        list.forEach(x -> System.out.println(x + 1));
    }
}
