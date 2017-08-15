package com.baijia123.concurrent.phaser;

import java.util.concurrent.Phaser;
import java.util.concurrent.TimeUnit;


/**
 * Java 并发 API 提供的其中一个最复杂且强大的功能是使用 Phaser 类来执行同步phased任务。当有些任务可以分成步骤执行
 * 时，此机制是很有用的。Phaser类提供的同步线程机制是在每个步骤的末端， 所以全部的线程都完成第一步后，才能开始执行
 * 第二步。
 * 
 * @author WangFuCai
 *
 */
public class Main1 {

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        Phaser phaser = new Phaser(3);
        for (int i = 0; i < 3; i++) {
            Task task = new Task(i + 1, phaser);
            Thread thread = new Thread(task);
            thread.start();
        }
        for (int i = 0; i < 10; i++) {
            System.out.printf("********************\n");
            System.out.printf("Main: Phaser Log\n");
            System.out.printf("Main: Phaser: Phase: %d\n", phaser.getPhase());
            System.out.printf("Main: Phaser: Registered Parties:%d\n", phaser.getRegisteredParties());
            System.out.printf("Main: Phaser: Arrived Parties:%d\n", phaser.getArrivedParties());
            System.out.printf("Main: Phaser: Unarrived Parties:%d\n", phaser.getUnarrivedParties());
            System.out.printf("********************\n");
            TimeUnit.SECONDS.sleep(1);
        }
    }

    public static class Task implements Runnable {

        private int time;
        private Phaser phaser;

        public Task(int time, Phaser phaser) {
            this.time = time;
            this.phaser = phaser;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            phaser.arrive();
            System.out.printf("%s: Entering phase 1.\n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s: Finishing phase 1.\n", Thread.currentThread().getName());
            phaser.arriveAndAwaitAdvance();
            System.out.printf("%s: Entering phase 2.\n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s: Finishing phase 2.\n", Thread.currentThread().getName());
            phaser.arriveAndAwaitAdvance();
            System.out.printf("%s: Entering phase 3.\n", Thread.currentThread().getName());
            try {
                TimeUnit.SECONDS.sleep(time);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.printf("%s: Finishing phase 3.\n", Thread.currentThread().getName());
            phaser.arriveAndDeregister();
        }
    }
}
