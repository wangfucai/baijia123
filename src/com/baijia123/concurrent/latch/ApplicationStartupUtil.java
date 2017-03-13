package com.baijia123.concurrent.latch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CountDownLatch������ܹ�ʹһ���̵߳ȴ������߳���ɸ��ԵĹ�������ִ�С�
 * ���磬Ӧ�ó�������߳�ϣ���ڸ���������ܷ�����߳��Ѿ��������еĿ�ܷ���֮����ִ��
 * 
 * CountDownLatch��ͨ��һ����������ʵ�ֵģ��������ĳ�ʼֵΪ�̵߳�������
 * ÿ��һ���߳�������Լ�������󣬼�������ֵ�ͻ��1����������ֵ����0ʱ��
 * ����ʾ���е��߳��Ѿ����������Ȼ���ڱ����ϵȴ����߳̾Ϳ��Իָ�ִ������
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
