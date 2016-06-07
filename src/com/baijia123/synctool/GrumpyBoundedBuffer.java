package com.baijia123.synctool;

public class GrumpyBoundedBuffer<V> extends BaseBoundedBuffer<V> {

    protected GrumpyBoundedBuffer(int capacity) {
        super(capacity);
        // TODO Auto-generated constructor stub
    }

    public synchronized void put(V v) throws Exception {
        if (isFull()) {
            throw new Exception();
        }
        doPut(v);
    }

    public synchronized V take() throws Exception {
        if (isEmpty()) {
            throw new Exception();
        }
        return doTake();
    }

}
