package com.baijia123.concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * 这个类继承了BaseHealthChecker，实现了verifyService()方法
 * @author WangFuCai
 *
 */
public class NetworkHealthChecker extends BaseHealthChecker {

    public NetworkHealthChecker(CountDownLatch latch) {
        super("network", latch);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void verifyService() {
        // TODO Auto-generated method stub
        System.out.println("Checking " + this.getServerName());
        try {
            Thread.sleep(7000);
        }catch(InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(this.getServerName() + "is up");
    }

}
