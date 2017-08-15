package com.baijia123.concurrent.phaser;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Phaser;

public class PhaserTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // ����ʱ������Ҫָ�������parties����
        int parties = 12;
        // �����ڴ���ʱ��ָ��parties
        // ����������ʱ����ʱע���ע���µ�parties
        Phaser phaser = new Phaser();
        // ���߳���ע��һ��
        // ��Ӧ�����У����߳̿��Եȴ����е�parties������ٽ��������������CountDownLatch��
        phaser.register();
        ExecutorService executor = Executors.newFixedThreadPool(parties);
        for (int i = 0; i < parties; i++) {
            phaser.register();// ÿ����һ��task�����Ǿ�ע��һ��party
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        int i = 0;
                        while (i < 3 && !phaser.isTerminated()) {
                            System.out.println("Generation:" + phaser.getPhase());
                            Thread.sleep(3000);
                            // �ȴ�ͬһ�����ڣ�����Task����
                            // Ȼ������µ����ڣ�������ͬ������
                            phaser.arriveAndAwaitAdvance();
                            i++;// ���Ǽٶ��������������ڼ���
                        }
                    } catch (Exception e) {

                    } finally {
                        phaser.arriveAndDeregister();
                    }
                }
            });
        }
        // ���̵߳����ע���Լ�
        // �˺��̳߳��е��̼߳��ɿ�ʼ�������ڣ�ͬ��ִ�С�
        phaser.arriveAndDeregister();
    }

}
