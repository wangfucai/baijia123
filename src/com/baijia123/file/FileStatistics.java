package com.baijia123.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

public class FileStatistics {

    private static Map<String, Integer> map = new TreeMap<>();
    //private static Map<String, Integer> map = new HashMap<>();

    public static void lineNumber(String file) {
        FileReader fr = null;
        try {
            fr = new FileReader(file);
        } catch (FileNotFoundException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
        BufferedReader br = new BufferedReader(fr);
        int lineCount = 0;
        try {
            while (br.readLine() != null) {
                lineCount++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (fr != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        map.put(file, lineCount);
    }

    public static void listFiles(File path) {
        File[] list = path.listFiles();
        for (File f : list) {
            String fileName = f.getName();
            String suffix = "";
            if (fileName.lastIndexOf(".") > 0) {
                suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
            }
            if (suffix.equals("java")) {
                lineNumber(f.getAbsolutePath());
            } else {
                if (f.isDirectory()) {
                    listFiles(f);
                } else {
                    continue;
                }
            }

        }
    }

    public static Integer statistics(String path) {
        Long start = System.currentTimeMillis();

        listFiles(new File(path));
        Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
        int sum = 0;
        while (iter.hasNext()) {
            Entry<String, Integer> entry = iter.next();
            System.out.println(entry.getKey() + "文件的行数是" + entry.getValue());
            sum += entry.getValue();
        }
        System.out.println("执行" + (System.currentTimeMillis() - start) + "ms,总行数" + sum);
        return sum;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //String path = "D://eclipse-workspace3//baijia123//src//com//baijia123//concurrent";
        //String path = "D://eclipse-workspace3//baijia123//src//com//baijia123";
        String path = "D:/eclipse-workspace3//presto-master";
        statistics(path);
    }
}
