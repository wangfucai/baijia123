package com.baijia123.thread;

/**
 * 多线程的执行流程：多个线程并发请求执行时，由cpu决定优先执行哪一个，
 * 即使通过thread.setPriority()，设置了线程的优先级，也不一定就是每次都先执行它
 * yield，表示暂停当前线程，执行其他线程(包括自身线程) 由cpu决定
 * 
 * 理解线程的优先权，尤其是了解yield()函数的工作过程。
 * 记住当线程的优先级没有指定时，所有线程都携带普通优先级。
 * 优先级可以用从1到10的范围指定。10表示最高优先级，1表示最低优先级，5是普通优先级。
 * 记住优先级最高的线程在执行时被给予优先。但是不能保证线程在启动时就进入运行状态。
 * 与在线程池中等待运行机会的线程相比，当前正在运行的线程可能总是拥有更高的优先级。
 * 由调度程序决定哪一个线程被执行。
 * t.setPriority()用来设定线程的优先级。
 * 记住在线程开始方法被调用之前，线程的优先级应该被设定。
 * 你可以使用常量，如MIN_PRIORITY,MAX_PRIORITY，NORM_PRIORITY来设定优先级
 * @author WangFuCai
 *
 */
public class TestYield implements Runnable {

    @Override
    public void run() {
        // TODO Auto-generated method stub
        System.out.println("first: " + Thread.currentThread().getName());
        // 暂停当前正在执行的线程对象，并执行其他线程，就是进入就绪状态
        Thread.currentThread().yield();
        // 可能还会执行 本线程: 以下语句不一定紧接着上面的语句被执行，可能其他线程的先执行了
        System.out.println("second: " + Thread.currentThread().getName());
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        TestYield runn = new TestYield();
        Thread t1 = new Thread(runn);
        Thread t2 = new Thread(runn);
        Thread t3 = new Thread(runn);

        t2.setPriority(t2.getPriority() + 1); // 设置t2的线程优先级
        t1.start();
        t2.start();
        t3.start();
    }

}
