package com.baijia123.unsafe.util.test;

import org.junit.Assert;
import org.junit.Test;

import com.baijia123.unsafe.util.LongArray;
import com.baijia123.unsafe.util.MemoryBlock;

public class TestLongArray {
    private LongArray createTestData() {
        byte[] bytes = new byte[16];
        LongArray arr = new LongArray(MemoryBlock.fromByteArray(bytes));
        arr.set(0, 1L);
        arr.set(1, 2L);
        arr.set(1, 3L);
        return arr;
    }

    @Test
    public void basicTest() {
        LongArray arr = createTestData();
        Assert.assertEquals(2, arr.size());
        Assert.assertEquals(1L, arr.get(0));
        Assert.assertEquals(3L, arr.get(1));
    }

    @Test
    public void toJvmArray() {
        LongArray arr = createTestData();
        long[] expected = { 1L, 3L };
        Assert.assertArrayEquals(expected, arr.toJvmArray());
    }
}
