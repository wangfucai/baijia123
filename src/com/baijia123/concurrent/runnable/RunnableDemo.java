package com.baijia123.concurrent.runnable;

/**
 * ʵ��Runnable�ӿ���ȼ̳�Thread�������ºô�:
 * 1.�����̳еľ��ޣ�һ������Լ̳ж���ӿڡ� 
 * 2.�ʺ�����Դ�Ĺ���
 * @author WangFuCai
 *
 */
public class RunnableDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyRunnable mr1 = new MyRunnable("�߳�a");
        MyRunnable mr2 = new MyRunnable("�߳�b");
        new Thread(mr1).start();
        new Thread(mr2).start();
    }

}
