package com.baijia123.proxy;

/**
 * 委托类(包含业务逻辑)
 * 
 * @author
 * 
 */
public class CountImpl implements Count {

    @Override
    public void queryCount() {
        // TODO Auto-generated method stub
        System.out.println("查看账户方法...");
    }

    @Override
    public void updateCount() {
        // TODO Auto-generated method stub
        System.out.println("修改账户方法...");
    }

}
