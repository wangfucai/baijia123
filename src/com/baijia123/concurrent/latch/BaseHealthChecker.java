package com.baijia123.concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch在实时系统中的使用场景 1.实现最大的并行性：有时我们想同时启动多个线程，实现最大程度的并行性。 例如，我们想测试一个单例类。如果我们创建一个初始计数为1的CountDownLatch， 并让所有线程都在这个锁上等待，那么我们可以很轻松地完成测试。 我们只需调用
 * 一次countDown()方法就可以让所有的等待线程同时恢复执行。 2.开始执行前等待n个线程完成各自任务：例如应用程序启动类要确保在处理用户请求前，所有N个外部系统已经启动和运行了。
 * 3.死锁检测：一个非常方便的使用场景是，你可以使用n个线程访问共享资源，在每次测试阶段的线程数目是不同的，并尝试产生死锁。 这个类是一个Runnable，负责所有特定的外部服务健康的检测。它删除了重复的代码和闭锁的中心控制代码
 * 
 * @author WangFuCai
 *
 */
public abstract class BaseHealthChecker implements Runnable {

    private CountDownLatch _latch;
    private String _serverName;
    private boolean _serviceUp;

    public BaseHealthChecker(String serverName, CountDownLatch latch) {
        super();
        this._latch = latch;
        this._serverName = serverName;
        this._serviceUp = false;
        // TODO Auto-generated constructor stub
    }

    @Override
    public void run() {
        // TODO Auto-generated method stub
        try {
            verifyService();
            _serviceUp = true;
        } catch (Throwable t) {
            t.printStackTrace();
            _serviceUp = false;
        } finally {
            if (_latch != null) {
                _latch.countDown();
            }
        }
    }

    public String getServerName() {
        return this._serverName;
    }

    public boolean isServiceUp() {
        return _serviceUp;
    }

    public abstract void verifyService();

}
