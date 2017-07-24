package com.baijia123.unsafe.util;

/**
 * 32-bit Murmur3 hasher.
 * 
 * @author WangFuCai
 *
 */
public final class Murmur3_x86_32 {
    private static final int C1 = 0xcc9e2d51;
    private static final int C2 = 0x1b873593;

    private final int seed;

    public Murmur3_x86_32(int seed) {
        this.seed = seed;
    }

    @Override
    public String toString() {
        return "Murmur3_32(seed=" + seed + ")";
    }

    public int hashInt(int input) {
        int k1 = mixK1(input);
        int h1 = mixH1(seed, k1);

        return fmix(h1, 4);
    }

    public int hashLong(long input) {
        int low = (int) input;
        int high = (int) (input >>> 32);

        int k1 = mixK1(low);
        int h1 = mixH1(seed, k1);

        k1 = mixK1(high);
        h1 = mixH1(h1, k1);

        return fmix(h1, 8);
    }

    private static int mixK1(int k1) {
        k1 *= C1;
        k1 = Integer.rotateLeft(k1, 15);
        k1 *= C2;
        return k1;
    }

    private static int mixH1(int h1, int k1) {
        h1 ^= k1;
        h1 = Integer.rotateLeft(h1, 13);
        h1 = h1 * 5 + 0xe6546b64;
        return h1;
    }

    // Finalization mix - force all bits of a hash block to avalanche
    private static int fmix(int h1, int length) {
        h1 ^= length;
        h1 ^= h1 >>> 16;
        h1 *= 0x85ebca6b;
        h1 ^= h1 >>> 13;
        h1 *= 0xc2b2ae35;
        h1 ^= h1 >>> 16;
        return h1;
    }
}
