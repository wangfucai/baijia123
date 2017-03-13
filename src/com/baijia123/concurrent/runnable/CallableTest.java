package com.baijia123.concurrent.runnable;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  Callable 和 Runnable 的使用方法大同小异， 区别在于： 
 *  1.Callable 使用 call（） 方法， Runnable 使用 run() 方法 
 *  2.call()可以返回值， 而 run()方法不能返回。 
 *  3.call()可以抛出受检查的异常，比如ClassNotFoundException， 而run()不能抛出受检查的异常。
 *  
 * @author WangFuCai
 *
 */
public class CallableTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // TODO Auto-generated method stub
        ExecutorService executor = Executors.newCachedThreadPool();
        ArrayList<Future<String>> results = new ArrayList<Future<String>>();
        for (int i = 0; i < 10; i++) {
            results.add(executor.submit(new TaskWithResult(i)));
        }
        for (Future<String> f : results) {
            if (f.isDone()) {
                System.out.println(f.get());
            } else {
                System.out.println("Future result is not yet complete");
            }
        }
        executor.shutdown();
    }

}
