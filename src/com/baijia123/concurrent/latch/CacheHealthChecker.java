package com.baijia123.concurrent.latch;

import java.util.concurrent.CountDownLatch;

public class CacheHealthChecker extends BaseHealthChecker {

    public CacheHealthChecker(CountDownLatch latch) {
        super("cache", latch);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void verifyService() {
        // TODO Auto-generated method stub
        System.out.println("Checking " + this.getServerName());
        try {
            Thread.sleep(10000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServerName() + "is up");
    }

}
