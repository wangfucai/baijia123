package com.baijia123.nonblocking;

/**
 * ʹ��ͬ�����̰߳�ȫ�ļ�����
 * @author WangFuCai
 *
 */
public final class Counter {
    private long value = 0;
    public synchronized long getValue() {
        return value;
    }
    public synchronized long increment() {
        return ++value;
    }
    
    public static void main(String[] args) {
        System.out.println(new Counter().increment());
    }
}
