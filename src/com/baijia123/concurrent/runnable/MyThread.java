package com.baijia123.concurrent.runnable;

public class MyThread extends Thread {
    private String name;

    public MyThread(String name) {
        super();
        this.name = name;
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        for (int i = 0; i < 10; i++) {
            System.out.println("线程开始:" + this.name + ",i=" + i);
        }
    }

}
