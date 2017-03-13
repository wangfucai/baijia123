package com.baijia123.concurrent.barrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Runner implements Runnable {

    private CyclicBarrier barrier;

    private String name;

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            Thread.sleep(1000 * (new Random()).nextInt(8));
            System.out.println(name + "准备好了...");
            barrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(name + "起跑！");
    }

    public Runner(CyclicBarrier barrier, String name) {
        super();
        this.barrier = barrier;
        this.name = name;
    }

}
