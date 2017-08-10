package com.baijia123.future;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName: FutureDemo
 * @Description: Future���̲߳����������鼯
 * @author WangFuCai
 * ʹ���̳߳��ύCallable�ӿ����񣬷���Future�ӿڣ���ӽ�list,������FutureList���ڲ�ʹ��while��ѯ,������ȡ���
 */
public class FutureDemo {

    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        // �������߳�
        ExecutorService exs = Executors.newFixedThreadPool(10);
        try {
            // �����
            List<Integer> list = new ArrayList<Integer>();
            List<Future<Integer>> futureList = new ArrayList<Future<Integer>>();
            // 1.�����ύ10������ÿ�����񷵻�һ��Future��list
            for (int i = 0; i < 10; i++) {
                futureList.add(exs.submit(new CallableTask(i + 1)));
            }
            Long getResultStart = System.currentTimeMillis();
            System.out.println("����鼯��ʼʱ��=" + new Date());
            // 2.����鼯������futureList,������ѯ��ģ��ʵ���˲�������ȡfuture״̬�ɹ���ɺ��ȡ������˳���ǰѭ��
            for (Future<Integer> future : futureList) {
                while (true) {// CPU������ѯ��ÿ��future��������ѭ���ж����״̬Ȼ���ȡ�������һ�У��Ǳ�ʵ�ַ����ľ������ڡ�����10��future�ڸ�����ѯ�����һ��future�Ļ�ȡ������͹ر�һ����ѯ
                    if (future.isDone() && !future.isCancelled()) {// ��ȡfuture�ɹ����״̬�������Ҫ����ÿ������ĳ�ʱʱ�䣬ȡ�����е�״̬�ж�+future.get(1000*1,
                                                                   // TimeUnit.MILLISECONDS)+catch��ʱ�쳣ʹ�ü��ɡ�
                        Integer i = future.get();// ��ȡ���
                        System.out.println("����i=" + i + "��ȡ���!" + new Date());
                        list.add(i);
                        break;// ��ǰfuture��ȡ�����ϣ�����while
                    } else {
                        Thread.sleep(1);// ÿ����ѯ��Ϣ1���루CPU���뼶��������CPU������ѭ�Ŀ�CPU---�����ֱ��������
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
            System.out.println("task�̣߳�" + Thread.currentThread().getName() + "����i=" + i + ",��ɣ�");
            return i;
        }
    }

}
