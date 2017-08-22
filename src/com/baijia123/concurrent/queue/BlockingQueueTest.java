package com.baijia123.concurrent.queue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class BlockingQueueTest {

    public class Producer implements Runnable {
        private final BlockingQueue queue;

        Producer(BlockingQueue q) {
            queue = q;
        }

        public void run() {
            try {
                while (true) {
                    queue.put(produce());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        Object produce() {
            return new Object();
        }
    }

    public class Consumer implements Runnable {
        private final BlockingQueue queue;

        Consumer(BlockingQueue q) {
            queue = q;
        }

        public void run() {
            try {
                while (true) {
                    consume(queue.take());
                }
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        void consume(Object x) {
            System.out.println(x.toString());
        }
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BlockingQueue<Object> q = new LinkedBlockingQueue<>(3);
        Producer p = new BlockingQueueTest().new Producer(q);
        Consumer c1 = new BlockingQueueTest().new Consumer(q);
        Consumer c2 = new BlockingQueueTest().new Consumer(q);
        new Thread(p).start();
        new Thread(c1).start();
        new Thread(c2).start();
    }

}
