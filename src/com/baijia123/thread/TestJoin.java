package com.baijia123.thread;

/**
 * join�����������̣߳��ȵ��������߳�ִ����ϣ�������ִ��
 * @author WangFuCai
 *
 */
public class TestJoin {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        final Thread thread1 = new Thread() {
            public void run() {
                System.out.println("first");
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("second");
            };
        };
        thread1.start();
        thread1.join(); //�����������߳�main

        Thread thread2 = new Thread() {
            public void run() {
                try {
                    System.out.println("third");
                    // thread1.join(); // �ȴ�t1�߳� ִ����ᣬ�ż�������ִ�� �����������߳�thread2

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("fourth");
            };
        };
        thread2.start();
    }

}
