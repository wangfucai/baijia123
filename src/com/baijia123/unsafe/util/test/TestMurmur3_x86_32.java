package com.baijia123.unsafe.util.test;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.baijia123.unsafe.util.Murmur3_x86_32;

public class TestMurmur3_x86_32 {
    private static Murmur3_x86_32 hasher = new Murmur3_x86_32(0);

    @Test
    public void testKnownIntegerInputs() {
        Assert.assertEquals(593689054, hasher.hashInt(0));
        Assert.assertEquals(-189366624, hasher.hashInt(-42));
        Assert.assertEquals(-1134849565, hasher.hashInt(42));
        Assert.assertEquals(-1718298732, hasher.hashInt(Integer.MIN_VALUE));
        Assert.assertEquals(-1653689534, hasher.hashInt(Integer.MAX_VALUE));
    }

    @Test
    public void testKnownLongInputs() {
        Assert.assertEquals(1669671676, hasher.hashLong(0L));
        Assert.assertEquals(-846261623, hasher.hashLong(-42L));
        Assert.assertEquals(1871679806, hasher.hashLong(42L));
        Assert.assertEquals(1366273829, hasher.hashLong(Long.MIN_VALUE));
        Assert.assertEquals(-2106506049, hasher.hashLong(Long.MAX_VALUE));
    }

    @Test
    public void randomizedStressTest() {
        int size = 65536;
        Random rand = new Random();

        // A set used to track collision rate.
        Set<Integer> hashcodes = new HashSet<Integer>();
        for (int i = 0; i < size; i++) {
            int vint = rand.nextInt();
            long lint = rand.nextLong();
            Assert.assertEquals(hasher.hashInt(vint), hasher.hashInt(vint));
            Assert.assertEquals(hasher.hashLong(lint), hasher.hashLong(lint));

            hashcodes.add(hasher.hashLong(lint));
        }

        // A very loose bound.
        Assert.assertTrue(hashcodes.size() > size * 0.95);
    }
}
