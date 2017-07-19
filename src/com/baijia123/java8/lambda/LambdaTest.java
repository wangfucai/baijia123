package com.baijia123.java8.lambda;

import java.io.File;
import java.io.FileFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LambdaTest {
    public static void main(String[] args) {

        // ��ͳ����
        File[] notHiddenFiles = new File(".").listFiles(new FileFilter() {

            @Override
            public boolean accept(File pathname) {
                // TODO Auto-generated method stub
                return !pathname.isHidden();
            }

        });
        List<File> fileList = Arrays.asList(notHiddenFiles);
        // fileList.forEach(f -> System.out.println(f.getName()));

        // ʹ��lambda���������Σ�����ȡֵΪ�ǻ���̫���
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
