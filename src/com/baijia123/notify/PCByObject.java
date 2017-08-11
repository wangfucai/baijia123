package com.baijia123.notify;

import java.util.PriorityQueue;

public class PCByObject {

    private PriorityQueue<Integer> queue = new PriorityQueue<>(10);

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PCByObject object = new PCByObject();
        Producer producer = object.new Producer();
        Consumer consumer = object.new Consumer();
        producer.start();
        consumer.start();
    }

    class Consumer extends Thread {
        @Override
        public void run() {
            try {
                consume();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        private void consume() throws InterruptedException {
            while (true) {
                synchronized (queue) {
                    Thread.sleep(200);
                    while (queue.size() == 0) {
                        try {
                            System.out.println("����Ϊ��");
                            queue.wait();

                        } catch (Exception e) {
                            // TODO: handle exception
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.poll();
                    queue.notify();
                    System.out.println("ȡ��һ��Ԫ��,���У�" + queue.size());

                }
            }
        }
    }

    class Producer extends Thread {
        @Override
        public void run() {
            System.out.println("��ʼ���г�����" + queue.size());
            produce();
        }

        private void produce() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == 10) {

                        try {
                            System.out.println("�����Ѿ�����");
                            queue.wait();
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    queue.offer(1);
                    queue.notify();
                    System.out.println("������в���һ��Ԫ�أ�����Ϊ" + queue.size());
                }
            }
        }
    }

}
