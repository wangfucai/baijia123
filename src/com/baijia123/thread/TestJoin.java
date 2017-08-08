package com.baijia123.thread;

/**
 * join：阻塞所在线程，等调用它的线程执行完毕，再向下执行
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
        thread1.join(); //在这阻塞主线程main

        Thread thread2 = new Thread() {
            public void run() {
                try {
                    System.out.println("third");
                    // thread1.join(); // 等待t1线程 执行完结，才继续向下执行 在这阻塞子线程thread2

                } catch (Exception e) {
                    e.printStackTrace();
                }
                System.out.println("fourth");
            };
        };
        thread2.start();
    }

}
