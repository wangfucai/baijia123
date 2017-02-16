package com.baijia123.offheap;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.junit.Test;

public class ConcurrentHashMapApp extends AbstractAppInvoker {

    private static final Map<String, FeedDO> cache = new ConcurrentHashMap<>();

    @Test
    @Override
    public void invoke() {
        // TODO Auto-generated method stub
        // write
        sleep(10 * 1000, null);
        for (int i = 0; i < SIZE; ++i) {
            String key = String.format("key_%s", i);
            FeedDO feedDO = createFeed(i, key, System.currentTimeMillis());
            cache.put(key, feedDO);
        }

        System.out.println("write down");
        // read
        for (int i = 0; i < SIZE; ++i) {
            String key = String.format("key_%s", i);
            FeedDO feedDO = cache.get(key);
            checkValid(feedDO);

            if (i % 10000 == 0) {
                System.out.println("read " + i);
            }
        }
    }

}
