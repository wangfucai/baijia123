package com.baijia123.thread;

/**
 * ���̵߳�ִ�����̣�����̲߳�������ִ��ʱ����cpu��������ִ����һ����
 * ��ʹͨ��thread.setPriority()���������̵߳����ȼ���Ҳ��һ������ÿ�ζ���ִ����
 * yield����ʾ��ͣ��ǰ�̣߳�ִ�������߳�(���������߳�) ��cpu����
 * 
 * ����̵߳�����Ȩ���������˽�yield()�����Ĺ������̡�
 * ��ס���̵߳����ȼ�û��ָ��ʱ�������̶߳�Я����ͨ���ȼ���
 * ���ȼ������ô�1��10�ķ�Χָ����10��ʾ������ȼ���1��ʾ������ȼ���5����ͨ���ȼ���
 * ��ס���ȼ���ߵ��߳���ִ��ʱ���������ȡ����ǲ��ܱ�֤�߳�������ʱ�ͽ�������״̬��
 * �����̳߳��еȴ����л�����߳���ȣ���ǰ�������е��߳̿�������ӵ�и��ߵ����ȼ���
 * �ɵ��ȳ��������һ���̱߳�ִ�С�
 * t.setPriority()�����趨�̵߳����ȼ���
 * ��ס���߳̿�ʼ����������֮ǰ���̵߳����ȼ�Ӧ�ñ��趨��
 * �����ʹ�ó�������MIN_PRIORITY,MAX_PRIORITY��NORM_PRIORITY���趨���ȼ�
 * @author WangFuCai
 *
 */
public class TestYield implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("first: " + Thread.currentThread().getName());
        // ��ͣ��ǰ����ִ�е��̶߳��󣬲�ִ�������̣߳����ǽ������״̬
        Thread.currentThread().yield();
        // ���ܻ���ִ�� ���߳�: ������䲻һ���������������䱻ִ�У����������̵߳���ִ����
        System.out.println("second: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TestYield runn = new TestYield();
        Thread t1 = new Thread(runn);
        Thread t2 = new Thread(runn);
        Thread t3 = new Thread(runn);

        t2.setPriority(t2.getPriority() + 1); // ����t2���߳����ȼ�
        t1.start();
        t2.start();
        t3.start();
    }

}
