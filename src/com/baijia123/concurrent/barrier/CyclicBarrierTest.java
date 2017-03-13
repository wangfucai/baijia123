package com.baijia123.concurrent.barrier;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CountDownLatch��CyclicBarrier������
 * CountDownLatch��һ���Եģ���CyclicBarrier�ڵ���reset֮�󻹿��Լ���ʹ��
 * 
 * CountDownLatch: A synchronization aid that allows one or more threads 
 * to wait until a set of operations being performed in other threads completes.
 * 
 * CyclicBarrier : A synchronization aid that allows a set of threads to all 
 * wait for each other to reach a common barrier point.
 * 
 * CountDownLatch : һ���߳�(���߶��)�� �ȴ�����N���߳����ĳ������֮�����ִ�С�   
 * 
 * CyclicBarrier  : N���߳��໥�ȴ����κ�һ���߳����֮ǰ�����е��̶߳�����ȴ���
 * 
 * ����CountDownLatch��˵���ص����Ǹ���һ���̡߳�, �����ڵȴ��� ��������N���߳��ڰѡ�ĳ�����顱
 * ����֮����Լ����ȴ���������ֹ��������CyclicBarrier��˵���ص�����N���̣߳�����֮���κ�
 * һ��û����ɣ����е��̶߳�����ȴ���
 * 
 * @author WangFuCai
 *
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);
        
        ExecutorService executor = Executors.newFixedThreadPool(3);
        executor.submit(new Runner(barrier, "1��ѡ��"));
        executor.submit(new Runner(barrier, "2��ѡ��"));
        //executor.submit(new Thread(new Runner(barrier, "3��ѡ��")));
        executor.submit(new Runner(barrier, "3��ѡ��"));
        
        executor.shutdown();
    }
}
