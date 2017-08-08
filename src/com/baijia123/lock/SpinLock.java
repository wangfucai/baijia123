package com.baijia123.lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 自旋锁
 * 非公平锁，CAS， 非可重入锁
 * benfit:响应速度更快， 因为不切换线程状态
 * bad:线程数量达到一定量时， 性能下降
 * @author WangFuCai
 *
 */
public class SpinLock implements Lock {

    private AtomicReference<Thread> sign = new AtomicReference<>();

    @Override
    public void lock() {
        // TODO Auto-generated method stub
        Thread current = Thread.currentThread();
        while (!sign.compareAndSet(null, current)) {

        }
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean tryLock() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void unlock() {
        // TODO Auto-generated method stub
        Thread current=Thread.currentThread();
        sign.compareAndSet(current, null);
    }

    @Override
    public Condition newCondition() {
        // TODO Auto-generated method stub
        return null;
    }

}
