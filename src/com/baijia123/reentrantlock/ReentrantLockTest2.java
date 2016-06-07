package com.baijia123.reentrantlock;

import java.util.Collection;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.junit.Test;

public class ReentrantLockTest2 {

    private static class ReentrantLock2 extends ReentrantLock {

        // Constructor Override

        public ReentrantLock2(boolean fair) {
            super(fair);
            // TODO Auto-generated constructor stub
        }

        public ReentrantLock2() {
            super();
            // TODO Auto-generated constructor stub
        }

        private static final long serialVersionUID = 1773716895097002072L;

        public Collection<Thread> getQueuedThreads() {
            return super.getQueuedThreads();
        }
    }

    private static Lock fairLock = new ReentrantLock2(true);
    private static Lock unfairLock = new ReentrantLock2();

    @Test
    public void fair() {
        System.out.println("fair version");
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Job(fairLock)) {
                public String toString() {
                    return getName();
                }
            };
            thread.setName("" + i);
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // sleep 5000ms
    }

    @Test
    public void unfair() {
        System.out.println("unfair version");
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(new Job(unfairLock)) {
                public String toString() {
                    return getName();
                }
            };
            thread.setName("" + i);
            thread.start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        // sleep 5000ms
    }

    private static class Job implements Runnable {
        private Lock lock;

        public Job(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            for (int i = 0; i < 5; i++) {
                lock.lock();
                try {
                    System.out.println("Lock by:" + Thread.currentThread().getName() + " and " + ((ReentrantLock2) lock).getQueuedThreads()
                            + " waits.");
                 
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
