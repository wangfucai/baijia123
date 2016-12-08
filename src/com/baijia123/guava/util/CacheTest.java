package com.baijia123.guava.util;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

public class CacheTest {
    public static void main(String[] argv) throws Exception {
        LoadingCache<String, Object> objects = CacheBuilder.newBuilder().expireAfterAccess(10, TimeUnit.MINUTES)
                .build(new CacheLoader<String, Object>() {

                    @Override
                    public Object load(String key) throws Exception {
                        // TODO Auto-generated method stub
                        return null;
                    }

                });
        Cache<String, Object> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
        String key = "test";
        try {
            cache.get(key, new Callable<Object>() {

                @Override
                public Object call() throws Exception {
                    // TODO Auto-generated method stub
                    return null;
                }
            });
        } catch (ExecutionException e) {
            throw new Exception(e.getCause());
        }

    }
}
