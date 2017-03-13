package com.baijia123.concurrent.runnable;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *  Callable �� Runnable ��ʹ�÷�����ͬС�죬 �������ڣ� 
 *  1.Callable ʹ�� call���� ������ Runnable ʹ�� run() ���� 
 *  2.call()���Է���ֵ�� �� run()�������ܷ��ء� 
 *  3.call()�����׳��ܼ����쳣������ClassNotFoundException�� ��run()�����׳��ܼ����쳣��
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
