package com.baijia123.unsafe.util;

import java.lang.reflect.Field;

import sun.misc.Unsafe;

public final class PlatformDependent {
    public static final Unsafe UNSAFE;
    public static final int BYTE_ARRAY_OFFSET;
    public static final int INT_ARRAY_OFFSET;
    public static final int LONG_ARRAY_OFFSET;
    public static final int DOUBLE_ARRAY_OFFSET;
    private static final long UNSAFE_COPY_THRESHOLD = 1024L * 1024L;

    static {
        sun.misc.Unsafe unsafe;
        try {
            Field unsafeField = Unsafe.class.getDeclaredField("theUnsafe");
            unsafeField.setAccessible(true);
            unsafe = (sun.misc.Unsafe) unsafeField.get(null);
        } catch (Throwable cause) {
            unsafe = null;
        }
        UNSAFE = unsafe;

        if (UNSAFE != null) {
            BYTE_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(byte[].class);
            INT_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(int[].class);
            LONG_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(long[].class);
            DOUBLE_ARRAY_OFFSET = UNSAFE.arrayBaseOffset(double[].class);
        } else {
            BYTE_ARRAY_OFFSET = 0;
            INT_ARRAY_OFFSET = 0;
            LONG_ARRAY_OFFSET = 0;
            DOUBLE_ARRAY_OFFSET = 0;
        }
    }

    static void copyMemory(Object src, long srcOffset, Object dst, long dstOffset, long length) {
        while (length > 0) {
            long size = Math.min(length, UNSAFE_COPY_THRESHOLD);
            UNSAFE.copyMemory(src, srcOffset, dst, dstOffset, size);
            length -= size;
            srcOffset += size;
            dstOffset += size;
        }
    }

    public static void throwException(Throwable t) {
        UNSAFE.throwException(t);
    }

    public static void main(String[] args) {
        System.out.println(PlatformDependent.DOUBLE_ARRAY_OFFSET);
    }
}
