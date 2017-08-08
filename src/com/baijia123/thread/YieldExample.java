package com.baijia123.thread;

public class YieldExample {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Thread producer = new YieldExample().new Producer();
        Thread consumer = new YieldExample().new Consumer();

        producer.setPriority(Thread.MIN_PRIORITY); // Min Priority
        consumer.setPriority(Thread.MAX_PRIORITY); // Max Priority

        producer.start();
        consumer.start();
    }

    public class Producer extends Thread {
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Producer : Produced Item " + i);
                Thread.yield();
            }
        }
    }

    public class Consumer extends Thread {
        public void run() {
            for (int i = 0; i < 5; i++) {
                System.out.println("I am Consumer : Consumed Item " + i);
                Thread.yield();
            }
        }
    }

}
