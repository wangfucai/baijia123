package com.baijia123.concurrent.runnable;

/**
 * 通过此代码找到Thread中的start()方法的定义，可以发现此方法中使用了private native void start0();
 * 其中native关键字表示可以调用操作系统的底层函数，那么这样的技术成为JNI技术(Java Native Interface)
 * start会启动一个线程，run是在本线程进行操作
 * @author WangFuCai
 *
 */
public class ThreadDemo {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MyThread mt1 = new MyThread("线程1");
        MyThread mt2 = new MyThread("线程2");
        mt1.start();
        mt2.start();
        //mt1.run();
        //mt2.run();
    }

}
