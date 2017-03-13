package com.baijia123.concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * �����̳���BaseHealthChecker��ʵ����verifyService()����
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
