package com.baijia123.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName: CompletionServiceDemo
 * @Description: CompletionService���̲߳����������鼯
 * @author WangFuCai
 * ԭ��:�ڲ�ͨ����������+FutureTask��ʵ������������ɿ����Ȼ�ȡ�����������������Ⱥ�˳������
 */
public class CompletionServiceDemo {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        // ����3���߳�
        ExecutorService exs = Executors.newFixedThreadPool(5);
        try {
            int taskCount = 10;
            // �����
            List<Integer> list = new ArrayList<Integer>();
            // 1.����CompletionService
            CompletionService<Integer> completionService = new ExecutorCompletionService<Integer>(exs);
            List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
            // 2.�������
            for (int i = 0; i < taskCount; i++) {
                futureList.add(completionService.submit(new Task(i + 1)));
            }
            // ==================����鼯===================
            // ����1��future���ύʱ���صģ�����queue���������ύ˳�򣬻�ȡ���
            // for (Future<Integer> future : futureList) {
            // System.out.println("====================");
            // Integer result = future.get();//�߳������������ȴ�������ִ�����,����
            // System.out.println("����result="+result+"��ȡ�����!"+new Date());
            // list.add(result);
            // }

            // //����2.ʹ���ڲ��������е�take()
            for (int i = 0; i < taskCount; i++) {
                Integer result = completionService.take().get();// ����completionService.take()���ڲ�ά���������У���������ɵ��Ȼ�ȡ��
                System.out.println("����i==" + result + "���!" + new Date());
                list.add(result);
            }
            System.out.println("list=" + list);
            System.out.println("�ܺ�ʱ=" + (System.currentTimeMillis() - start));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();// �ر��̳߳�
        }

    }

    static class Task implements Callable<Integer> {
        Integer i;

        public Task(Integer i) {
            super();
            this.i = i;
        }

        @Override
        public Integer call() throws Exception {
            if (i == 5) {
                Thread.sleep(5000);
            } else {
                Thread.sleep(1000);
            }
            System.out.println("�̣߳�" + Thread.currentThread().getName() + "����i=" + i + ",ִ����ɣ�");
            return i;
        }

    }
}
