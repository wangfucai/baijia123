package com.baijia123.notify;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PCByCondition {

    private PriorityQueue<Integer> queue = new PriorityQueue<>(10);
    private ReentrantLock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PCByCondition object = new PCByCondition();
        Producer producer = object.new Producer();
        Consumer consumer = object.new Consumer();
        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("����Ϊ��");
                            notEmpty.await();

                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();

                        }
                    }
                    queue.poll();
                    notFull.signal();
                    System.out.println("ȡ��һ��Ԫ��,���У�" + queue.size());

                } finally {
                    lock.unlock();
                }
            }
        }

    }

    class Producer extends Thread {
        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 10) {

                        try {
                            System.out.println("�����Ѿ�����");
                            notFull.await();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    notEmpty.signal();
                    System.out.println("������в���һ��Ԫ�أ�����Ϊ" + queue.size());
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
