package com.baijia123.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * @ClassName:FutureTaskDemo
 * @Description:FutureTaskʵ�ֶ��̲߳���ִ������ȡ����鼯
 * @author WangFuCai
 * ���̲߳���ִ�в�����鼯���������һ��FutureTask�Ƚϼ��ߣ�ֱ�ӷ���Future�����ˣ�������ʹ�á�
 */
public class FutureTaskDemo {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        // �������߳�
        ExecutorService exs = Executors.newFixedThreadPool(5);
        try {
            // �����
            List<Integer> list = new ArrayList<Integer>();
            List<FutureTask<Integer>> futureList = new ArrayList<FutureTask<Integer>>();
            // �����̳߳أ�10������̶��߳���Ϊ5
            for (int i = 0; i < 10; i++) {
                FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTask(i + 1));
                // �ύ������ӷ���
                exs.submit(futureTask);// Runnable����
                futureList.add(futureTask);// Future����
            }
            Long getResultStart = System.currentTimeMillis();
            System.out.println("����鼯��ʼʱ��=" + new Date());
            // ����鼯
            for (FutureTask<Integer> future : futureList) {
                while (true) {
                    if (future.isDone() && !future.isCancelled()) {
                        Integer i = future.get();// Future����
                        System.out.println("i=" + i + "��ȡ�����!" + new Date());
                        list.add(i);
                        break;
                    } else {
                        Thread.sleep(1);// ����CPU������ѭ��������Ϣһ�¡�
                    }
                }
            }
            System.out.println("list=" + list);
            System.out.println("�ܺ�ʱ=" + (System.currentTimeMillis() - start) + ",ȡ����鼯��ʱ=" + (System.currentTimeMillis() - getResultStart));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            exs.shutdown();
        }

    }

    static class CallableTask implements Callable<Integer> {
        Integer i;

        public CallableTask(Integer i) {
            super();
            this.i = i;
        }

        @Override
        public Integer call() throws Exception {
            if (i == 1) {
                Thread.sleep(3000);// ����1��ʱ3��
            } else if (i == 5) {
                Thread.sleep(5000);// ����5��ʱ5��
            } else {
                Thread.sleep(1000);// ���������ʱ1��
            }
            System.out.println("�̣߳�[" + Thread.currentThread().getName() + "]����i=" + i + ",��ɣ�" + new Date());
            return i;

        }

    }
}
