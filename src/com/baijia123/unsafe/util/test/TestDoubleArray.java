package com.baijia123.unsafe.util.test;

import org.junit.Assert;
import org.junit.Test;

import com.baijia123.unsafe.util.DoubleArray;
import com.baijia123.unsafe.util.MemoryBlock;

public class TestDoubleArray {
    private DoubleArray createTestData() {
        byte[] bytes = new byte[16];
        DoubleArray arr = new DoubleArray(MemoryBlock.fromByteArray(bytes));
        arr.set(0, 1.0);
        arr.set(1, 2.0);
        arr.set(1, 3.0);
        return arr;
    }

    @Test
    public void basicTest() {
        DoubleArray arr = createTestData();
        Assert.assertEquals(2, arr.size());
        Assert.assertEquals(1.0, arr.get(0), 0.00000000001);
        Assert.assertEquals(3.0, arr.get(1), 0.00000000001);
    }

    @Test
    public void toJvmArray() {
        DoubleArray arr = createTestData();
        double[] expected = { 1.0, 3.0 };
        Assert.assertArrayEquals(expected, arr.toJvmArray(), 0.00000000001);
    }
}
