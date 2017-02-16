package com.baijia123.offheap;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

public class GuavaCacheApp extends AbstractAppInvoker {

    private static final LoadingCache<String, FeedDO> guavaCache;

    static {
        guavaCache = CacheBuilder.newBuilder().build(new CacheLoader<String, FeedDO>() {
            @Override
            public FeedDO load(String key) throws Exception {
                return null;
            }
        });
    }

    @Test
    @Override
    public void invoke() {
        sleep(10 * 1000, null);
        // write
        for (int i = 0; i < SIZE; ++i) {
            String key = String.format("key_%s", i);
            FeedDO feedDO = createFeed(i, key, System.currentTimeMillis());

            guavaCache.put(key, feedDO);
        }

        System.out.println("write down");
        // read
        for (int i = 0; i < SIZE; ++i) {
            String key = String.format("key_%s", i);
            FeedDO feedDO = guavaCache.getUnchecked(key);
            checkValid(feedDO);

            if (i % 10000 == 0) {
                System.out.println("read " + i);
            }
        }
    }
}
