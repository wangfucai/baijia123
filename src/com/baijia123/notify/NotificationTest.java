package com.baijia123.notify;

public class NotificationTest {

    private volatile boolean go = false;

    public static void main(String[] args) throws InterruptedException {
        // TODO Auto-generated method stub
        final NotificationTest test = new NotificationTest();

        Runnable waitTask = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                try {
                    test.shouldGo();
                } catch(InterruptedException ex) {
                    ex.printStackTrace();
                }
            }

        };

        Runnable notifyTask = new Runnable() {

            @Override
            public void run() {
                // TODO Auto-generated method stub
                test.go();
                System.out.println(Thread.currentThread() + " finished Execution");
            }

        };

        Thread t1 = new Thread(waitTask, "WT1");
        Thread t2 = new Thread(waitTask, "WT2");
        Thread t3 = new Thread(waitTask, "WT3");
        Thread t4 = new Thread(notifyTask, "WT4");
        
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(200);

        t4.start();
    }

    private synchronized void shouldGo() throws InterruptedException {
        while(go != true) {
            System.out.println(Thread.currentThread() + " is going to wait on the object");
            wait();
            System.out.println(Thread.currentThread() + " is woken up");
        }
        go = false;
    }
    
    private synchronized void go() {
        while (go == false) {
            System.out.println(Thread.currentThread() + " is going to notify all or one thread waiting on the object");
            go = true;
            //notify();
            notifyAll();
        }
    }

}
