package com.baijia123.concurrent.latch;

public class Main {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        boolean result = false;
        try {
            result = ApplicationStartupUtil.checkExternalServices();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("External services validation completed !! Result was :: "+ result);
    }
}
