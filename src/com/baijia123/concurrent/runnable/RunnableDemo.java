package com.baijia123.concurrent.runnable;

/**
 * 实现Runnable接口相比继承Thread类有如下好处:
 * 1.避免点继承的局限，一个类可以继承多个接口。 
 * 2.适合于资源的共享
 * @author WangFuCai
 *
 */
public class RunnableDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyRunnable mr1 = new MyRunnable("线程a");
        MyRunnable mr2 = new MyRunnable("线程b");
        new Thread(mr1).start();
        new Thread(mr2).start();
    }

}
