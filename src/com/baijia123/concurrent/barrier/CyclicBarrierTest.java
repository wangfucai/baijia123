package com.baijia123.concurrent.barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch和CyclicBarrier的区别
 * CountDownLatch是一次性的，而CyclicBarrier在调用reset之后还可以继续使用
 * 
 * CountDownLatch: A synchronization aid that allows one or more threads 
 * to wait until a set of operations being performed in other threads completes.
 * 
 * CyclicBarrier : A synchronization aid that allows a set of threads to all 
 * wait for each other to reach a common barrier point.
 * 
 * CountDownLatch : 一个线程(或者多个)， 等待另外N个线程完成某个事情之后才能执行。   
 * 
 * CyclicBarrier  : N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。
 * 
 * 对于CountDownLatch来说，重点是那个“一个线程”, 是它在等待， 而另外那N的线程在把“某个事情”
 * 做完之后可以继续等待，可以终止。而对于CyclicBarrier来说，重点是那N个线程，他们之间任何
 * 一个没有完成，所有的线程都必须等待。
 * 
 * @author WangFuCai
 *
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Runner(barrier, "1号选手"));
        executor.submit(new Runner(barrier, "2号选手"));
        //executor.submit(new Thread(new Runner(barrier, "3号选手")));
        executor.submit(new Runner(barrier, "3号选手"));
        
        executor.shutdown();
    }
}
