package com.baijia123.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ParallelFileStatistics {
    public static class FileStatisticTask implements Callable<Integer> {

        private String dir;
        private boolean scanFlag;

        public FileStatisticTask(String dir, boolean scanFlag) {
            super();
            this.dir = dir;
            this.scanFlag = scanFlag;
        }

        @Override
        public Integer call() throws Exception {
            // TODO Auto-generated method stub
            int sum = listFiles(new File(dir), scanFlag);
            return sum;
        }
    }

    private static Map<String, Integer> map = new ConcurrentHashMap<>();

    public static Integer lineNumber(String file) {
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
        return lineCount;
    }

    public static Integer listFiles(File path, boolean scanFlag) {
        int sum = 0;
        File[] list = path.listFiles();
        for (File f : list) {
            if (f.isDirectory()) {
                if (scanFlag == true)
                    sum += listFiles(f, true);
            } else {
                String fileName = f.getName();
                String suffix = "";
                if (fileName.lastIndexOf(".") > 0) {
                    suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                    if (suffix != null && suffix.equals("scala")) {
                        sum += lineNumber(f.getAbsolutePath());
                    }
                }
            }
        }
        return sum;
    }

    public static Integer countFileDir(String path) {
        File file = new File(path);
        File[] list = file.listFiles();
        int count = 0;
        for (File f : list) {
            if (f.isDirectory()) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub
        // String path = "D://eclipse-workspace3//baijia123//src//com//baijia123//concurrent";
        // String path = "D://eclipse-workspace3//baijia123//src//com//baijia123";
        // String path = "D:/eclipse-workspace3//spring-framework";
        // String path = "D:/eclipse-workspace3//druid-master";//514582
         String path = "D:/eclipse-workspace3//kafka-trunk";//java251524 + scala109534
        // String path = "D:/eclipse-workspace3//tomcat-trunk";//520818
        // String path = "D:/eclipse-workspace3//zookeeper-master";//117058
        // String path = "D:/eclipse-workspace3//presto-master";//659526
        // String path = "D:/eclipse-workspace3//nubia_channel";//44050
        // String path = "D:/eclipse-workspace3//spring-framework";//951743
        // String path = "D:/eclipse-workspace3//Hystrix-master";//78170
        // String path = "D:/eclipse-workspace3//spark-master";//scala 646311 + java 153297
        // String path = "D:/eclipse-workspace3//dubbo-master";//163180
        // String path = "D:/eclipse-workspace3//spring-boot-master";//363158
        // String path = "D:/eclipse-workspace3//deeplearning4j-master";//272396
        // String path = "D:/eclipse-workspace3//hive-master";//1468368
        //String path = "D:/eclipse-workspace3//hadoop-trunk";//1990152
        Long start = System.currentTimeMillis();
        Integer nThreads = Math.min(countFileDir(path) + 1, 200);
        ExecutorService es = Executors.newWorkStealingPool(nThreads);
        CompletionService<Integer> cs = new ExecutorCompletionService<Integer>(es);
        List<Future<Integer>> futureList = new ArrayList<>();
        File file = new File(path);
        File[] list = file.listFiles();
        boolean haveFile = false;
        for (File f : list) {
            if (f.isDirectory()) {
                futureList.add(cs.submit(new FileStatisticTask(f.getAbsolutePath(), true)));
            } else {
                haveFile = true;
                
            }
        }
        if (haveFile == true) {
            futureList.add(cs.submit(new FileStatisticTask(file.getAbsolutePath(), false)));
        }

        /*
        for (Future<Integer> future : futureList) {
            while (true) {
                if (future.isDone() && !future.isCancelled()) {
                    break;
                } else {
                    Thread.sleep(10);
                }
            }
        }
        */
        int resultCount = 0;
        for (int index = 0; index < futureList.size(); index++) {
            Integer result = cs.take().get();
            System.out.println("第" + index + "个的result = " + result);
            resultCount += result;
        }
        System.out.println("resultCount = " + resultCount);
        Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
        int sum = 0;
        System.out.println("线程数是" + nThreads);
        while (iter.hasNext()) {
            Entry<String, Integer> entry = iter.next();
            // System.out.println(entry.getKey() + "文件的行数是" + entry.getValue());
            sum += entry.getValue();
        }
        System.out.println("并行执行" + (System.currentTimeMillis() - start) + "ms,总行数" + sum);
        es.shutdown();
    }
}
