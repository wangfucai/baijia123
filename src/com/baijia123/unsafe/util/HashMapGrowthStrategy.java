package com.baijia123.unsafe.util;

public interface HashMapGrowthStrategy {
    public long nextCapacity(long currentCapacity);

    public static final HashMapGrowthStrategy DOUBLING = new Doubling();

    static class Doubling implements HashMapGrowthStrategy {
        @Override
        public long nextCapacity(long currentCapacity) {
            return currentCapacity * 2;
        }
    }
}
