package com.baijia123.offheap;

import java.nio.ByteBuffer;

import sun.nio.ch.DirectBuffer;

import com.google.common.base.Strings;

public abstract class AbstractAppInvoker {
    protected static final int SIZE = 170_0000;

    protected static final IObjectSerializer serializer = new Hessian2Serializer();

    protected static FeedDO createFeed(long id, String userId, long createTime) {

        return new FeedDO(id, userId, (int) id, userId + "_" + id, createTime);
    }

    protected static void free(ByteBuffer byteBuffer) {
        if (byteBuffer.isDirect()) {
            ((DirectBuffer) byteBuffer).cleaner().clean();
        }
    }

    protected static void checkValid(Object obj) {
        if (obj == null) {
            throw new RuntimeException("cache invalid");
        }
    }

    protected static void sleep(int time, String beforeMsg) {
        if (!Strings.isNullOrEmpty(beforeMsg)) {
            System.out.println(beforeMsg);
        }

        try {
            Thread.sleep(time);
        } catch (InterruptedException ignored) {
            // no op
        }
    }

    /**
     * 供子类继承 & 外界调用
     *
     * @param param
     */
    public abstract void invoke();
}
