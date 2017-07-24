package com.baijia123.unsafe.util.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import com.baijia123.unsafe.util.LongToLongMap;
import com.baijia123.unsafe.util.MemoryAllocator;

public class TestLongToLongMap {
    private LongToLongMap createEmptyMap(int size) {
        return new LongToLongMap(MemoryAllocator.UNSAFE, size);
    }

    @Test
    public void emptyMap() {
        LongToLongMap map = createEmptyMap(64);
        Assert.assertFalse(map.containsKey(-1L));
        Assert.assertFalse(map.containsKey(0L));
        Assert.assertFalse(map.containsKey(10L));
    }

    @Test
    public void basicOps() {
        LongToLongMap map = createEmptyMap(128);
        map.put(10L, 1L);
        Assert.assertEquals(1L, map.get(10L));

        map.put(15L, 2L);
        Assert.assertEquals(1L, map.get(10L));
        Assert.assertEquals(2L, map.get(15L));

        Assert.assertTrue(map.containsKey(10L));
        Assert.assertTrue(map.containsKey(15L));

        Assert.assertEquals(2, map.size());
    }

    @Test
    public void putOverwrite() {
        LongToLongMap map = createEmptyMap(128);
        map.put(10L, 1L);
        map.put(10L, 2L);
        Assert.assertEquals(2L, map.get(10L));
        Assert.assertEquals(1, map.size());
    }

    @Test
    public void locationHandler() {
        LongToLongMap map = createEmptyMap(128);
        map.put(10L, 1L);
        map.put(15L, 2L);

        Assert.assertTrue(map.lookup(10L).isDefined());
        Assert.assertEquals(10L, map.lookup(10L).getKey());
        Assert.assertEquals(1L, map.lookup(10L).getValue());

        Assert.assertTrue(map.lookup(15L).isDefined());
        Assert.assertEquals(15L, map.lookup(15L).getKey());
        Assert.assertEquals(2L, map.lookup(15L).getValue());

        Assert.assertFalse(map.lookup(50L).isDefined());
        Assert.assertEquals(2, map.size());

        map.lookup(50L).setValue(5L);
        Assert.assertTrue(map.lookup(50L).isDefined());
        Assert.assertEquals(5L, map.get(50L));
        Assert.assertEquals(3, map.size());
    }

    @Test
    public void stressTest() {
        // Stuff the thing to 90% full so we can trigger probing
        LongToLongMap map = createEmptyMap(16);
        for (int i = 0; i < 128 * 0.9; i++) {
            map.put(i, i);
        }

        for (int i = 0; i < 128 * 0.9; i++) {
            Assert.assertEquals(i, map.get(i));
        }
    }

    @Test
    public void randomizedStressTest() {
        // Stuff the thing to 90% full with randomly generated numbers so we can trigger probing
        int size = 65536;
        Random rand = new Random();
        LongToLongMap map = createEmptyMap(size);

        Map<Long, Long> expected = new HashMap<Long, Long>();
        for (int i = 0; i < size * 0.9; i++) {
            long key = rand.nextLong();
            long value = rand.nextLong();
            expected.put(key, value);
            map.put(key, value);
        }

        for (Map.Entry<Long, Long> entry : expected.entrySet()) {
            long key = entry.getKey();
            long value = entry.getValue();
            Assert.assertEquals(value, map.get(key));
        }
    }
}
