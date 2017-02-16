package com.baijia123.offheap;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class LocalRedisApp extends AbstractAppInvoker {

    private static final Jedis cache = new Jedis("localhost", 6379);

    private static final IObjectSerializer serializer = new Hessian2Serializer();

    @Test
    @Override
    public void invoke() {
        // write
        for (int i = 0; i < SIZE; ++i) {
            String key = String.format("key_%s", i);
            FeedDO feedDO = createFeed(i, key, System.currentTimeMillis());

            byte[] value = serializer.serialize(feedDO);
            cache.set(key.getBytes(), value);

            if (i % 10000 == 0) {
                System.out.println("write " + i);
            }
        }

        System.out.println("write down");
        // read
        for (int i = 0; i < SIZE; ++i) {
            String key = String.format("key_%s", i);
            byte[] value = cache.get(key.getBytes());
            FeedDO feedDO = serializer.deserialize(value);
            checkValid(feedDO);

            if (i % 10000 == 0) {
                System.out.println("read " + i);
            }
        }
    }

}
