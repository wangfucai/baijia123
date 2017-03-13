package com.baijia123.concurrent.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CountDownLatch这个类能够使一个线程等待其他线程完成各自的工作后再执行。
 * 例如，应用程序的主线程希望在负责启动框架服务的线程已经启动所有的框架服务之后再执行
 * 
 * CountDownLatch是通过一个计数器来实现的，计数器的初始值为线程的数量。
 * 每当一个线程完成了自己的任务后，计数器的值就会减1。当计数器值到达0时，
 * 它表示所有的线程已经完成了任务，然后在闭锁上等待的线程就可以恢复执行任务。
 * @author WangFuCai
 *
 */
public class ApplicationStartupUtil {

    private static List<BaseHealthChecker> _services;

    private static CountDownLatch _latch;

    private ApplicationStartupUtil() {

    }

    private final static ApplicationStartupUtil instance = new ApplicationStartupUtil();

    public static ApplicationStartupUtil getInstance() {
        return instance;
    }

    public static boolean checkExternalServices() throws InterruptedException {
        _latch = new CountDownLatch(3);

        _services = new ArrayList<BaseHealthChecker>();
        _services.add(new CacheHealthChecker(_latch));
        _services.add(new DatabaseHealthChecker(_latch));
        _services.add(new NetworkHealthChecker(_latch));

        Executor executor = Executors.newFixedThreadPool(_services.size());
        for (final BaseHealthChecker v : _services) {
            executor.execute(v);
        }

        _latch.await();
        for (final BaseHealthChecker v : _services) {
            if (!v.isServiceUp()) {
                return false;
            }
        }
        return true;
    }
}
