package com.baijia123.future;

import java.util.Date;
import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * @ClassName:FutureTaskDemo
 * @Description:FutureTask�ֲ���Future�������̳߳��ύ����Future��ȱ�ݣ�ʵ�ֹ������£� 
 * 1.Runnable�ӿڣ��ɿ����߳�ִ�С�
 * 2.Future<v>�ӿڣ��ɽ���Callable�ӿڵķ���ֵ��futureTask.get()������ȡ����� 
 * ���������裺һ�������߳�ִ������һ�������ȴ�ִ�н���������������裬�����������м䴩�������ҵ���߼���
 * @author WangFuCai
 * 
 */
public class FutureTaskContorlDemo {
    public static void main(String[] args) {
        try {
            System.out.println("=====����һ��ͳ�ƹ�˾�ܲ��ͷֲ����������Ƿ���100��==========");
            // ����
            Integer count = 0;
            // 1.����һ��futureTask������ȥԶ��http��ȡ�����ֹ�˾ҵ��.
            FutureTask<Integer> futureTask = new FutureTask<Integer>(new CallableTask());
            Thread futureTaskThread = new Thread(futureTask);
            futureTaskThread.start();
            System.out.println("futureTaskThread start��" + new Date());

            // 2.���߳�����������
            System.out.println("���̲߳�ѯ�ܲ���˾����ʼʱ�䣺" + new Date());
            Thread.sleep(5000);
            count += 10;// ���������ܲ�����
            System.out.println("���̲߳�ѯ�ܲ���˾������ʱ�䣺" + new Date());

            // �ܲ��Ѵ��100�����󣬾Ͳ��ټ���ִ�л�ȡ�ֹ�˾ҵ��������
            if (count >= 100) {
                System.out.println("�ܲ���˾�����꣬ȡ��futureTask��" + new Date());
                futureTask.cancel(true);// ����Ҫ��ȥ��ȡ�������ôֱ��ȡ������
            } else {
                System.out.println("�ܲ���˾����δ��꣬����������ѯ�ֹ�˾����" + new Date());
                // 3�ܲ�δ���.������ȡ�������ֹ�˾���
                Integer i = futureTask.get();// ����ִ��CallableTask
                System.out.println("i=" + i + "��ȡ�����!" + new Date() + new Date());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 
     * @ClassName:CallableTask
     * @Description:һ��ʮ�ֺ�ʱ������
     * @author diandian.zhang
     * @date 2017��6��16������10:39:04
     */
    static class CallableTask implements Callable<Integer> {
        @Override
        public Integer call() throws Exception {
            System.out.println("CallableTask-call����ѯ�ֹ�˾����ִ�п�ʼ��" + new Date());
            Thread.sleep(10000);
            System.out.println("CallableTask-call����ѯ�ֹ�˾����ִ����ϣ�" + new Date());
            return 10;
        }
    }
}
