package com.baijia123.concurrent.runnable;

public class MyRunnable implements Runnable {

    private String name;

    public MyRunnable(String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++) {
            System.out.println("�߳̿�ʼ" + this.name + ",i=" + i);
        }
    }
}
