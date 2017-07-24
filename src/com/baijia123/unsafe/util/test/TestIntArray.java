package com.baijia123.unsafe.util.test;

import org.junit.Assert;
import org.junit.Test;

import com.baijia123.unsafe.util.IntArray;
import com.baijia123.unsafe.util.MemoryBlock;

public class TestIntArray {
    private IntArray createTestData() {
        byte[] bytes = new byte[16];
        IntArray arr = new IntArray(MemoryBlock.fromByteArray(bytes));
        arr.set(0, 1);
        arr.set(1, 2);
        arr.set(2, 3);
        arr.set(3, 4);
        arr.set(3, 5);
        return arr;
    }

    @Test
    public void basicTest() {
        IntArray arr = createTestData();
        Assert.assertEquals(4, arr.size());
        Assert.assertEquals(1, arr.get(0));
        Assert.assertEquals(2, arr.get(1));
        Assert.assertEquals(3, arr.get(2));
        Assert.assertEquals(5, arr.get(3));
    }

    @Test
    public void toJvmArray() {
        IntArray arr = createTestData();
        int[] expected = { 1, 2, 3, 5 };
        Assert.assertArrayEquals(expected, arr.toJvmArray());
    }
}
