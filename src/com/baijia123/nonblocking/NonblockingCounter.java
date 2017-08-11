package com.baijia123.nonblocking;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ʹ�� CAS �ķ������㷨
 * ����CAS�Լ�AtomicReference��
 * @author WangFuCai
 *
 */
public class NonblockingCounter {
    private AtomicInteger value;

    public NonblockingCounter() {
        super();
        // TODO Auto-generated constructor stub
        value = new AtomicInteger();
    }

    public int getValue() {
        return value.get();
    }

    public int increment() {
        int v;
        do {
            v = value.get();
        } while (!value.compareAndSet(v, v + 1));
        return v + 1;
    }

    public static void main(String[] args) {
        System.out.println(new NonblockingCounter().increment());
    }
}
