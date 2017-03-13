package com.baijia123.concurrent.latch;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch��ʵʱϵͳ�е�ʹ�ó��� 1.ʵ�����Ĳ����ԣ���ʱ������ͬʱ��������̣߳�ʵ�����̶ȵĲ����ԡ� ���磬���������һ�������ࡣ������Ǵ���һ����ʼ����Ϊ1��CountDownLatch�� ���������̶߳���������ϵȴ�����ô���ǿ��Ժ����ɵ���ɲ��ԡ� ����ֻ�����
 * һ��countDown()�����Ϳ��������еĵȴ��߳�ͬʱ�ָ�ִ�С� 2.��ʼִ��ǰ�ȴ�n���߳���ɸ�����������Ӧ�ó���������Ҫȷ���ڴ����û�����ǰ������N���ⲿϵͳ�Ѿ������������ˡ�
 * 3.������⣺һ���ǳ������ʹ�ó����ǣ������ʹ��n���̷߳��ʹ�����Դ����ÿ�β��Խ׶ε��߳���Ŀ�ǲ�ͬ�ģ������Բ��������� �������һ��Runnable�����������ض����ⲿ���񽡿��ļ�⡣��ɾ�����ظ��Ĵ���ͱ��������Ŀ��ƴ���
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
