package com.baijia123.unsafe.util.test;

import org.junit.Assert;
import org.junit.Test;

import com.baijia123.unsafe.util.BitSet;
import com.baijia123.unsafe.util.MemoryBlock;

public class TestBitSet {
    private BitSet createBitSet(int capacity) {
        assert capacity % 64 == 0;
        return new BitSet(MemoryBlock.fromLongArray(new long[capacity / 64]));
    }

    @Test
    public void basicOps() {
        BitSet bs = createBitSet(64);
        Assert.assertEquals(64, bs.capacity());

        // Make sure the bit set starts empty.
        for (int i = 0; i < bs.capacity(); i++) {
            Assert.assertFalse(bs.isSet(i));
        }

        // Set every bit and check it.
        for (int i = 0; i < bs.capacity(); i++) {
            bs.set(i);
            Assert.assertTrue(bs.isSet(i));
        }

        // Unset every bit and check it.
        for (int i = 0; i < bs.capacity(); i++) {
            Assert.assertTrue(bs.isSet(i));
            bs.unset(i);
            Assert.assertFalse(bs.isSet(i));
        }
    }

    @Test
    public void cardinality() {
        BitSet bs = createBitSet(64);
        Assert.assertEquals(0, bs.cardinality());

        // Set every bit and check it.
        for (int i = 0; i < bs.capacity(); i++) {
            bs.set(i);
            Assert.assertEquals(i + 1, bs.cardinality());
        }
    }

    @Test
    public void traversal() {
        BitSet bs = createBitSet(256);

        Assert.assertEquals(-1, bs.nextSetBit(0));
        Assert.assertEquals(-1, bs.nextSetBit(10));
        Assert.assertEquals(-1, bs.nextSetBit(64));

        bs.set(10);
        Assert.assertEquals(10, bs.nextSetBit(0));
        Assert.assertEquals(10, bs.nextSetBit(1));
        Assert.assertEquals(10, bs.nextSetBit(10));
        Assert.assertEquals(-1, bs.nextSetBit(11));

        bs.set(11);
        Assert.assertEquals(10, bs.nextSetBit(10));
        Assert.assertEquals(11, bs.nextSetBit(11));

        // Skip a whole word and find it
        bs.set(190);
        Assert.assertEquals(190, bs.nextSetBit(12));

        Assert.assertEquals(-1, bs.nextSetBit(191));
        Assert.assertEquals(-1, bs.nextSetBit(256));
    }
}
