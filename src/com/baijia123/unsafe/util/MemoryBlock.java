package com.baijia123.unsafe.util;

import javax.annotation.Nullable;

public class MemoryBlock extends MemoryLocation {
    final long length;

    MemoryBlock(@Nullable Object obj, long offset, long length) {
        super(obj, offset);
        this.length = length;
    }

    public long size() {
        return length;
    }

    public static MemoryBlock fromByteArray(final byte[] array) {
        return new MemoryBlock(array, PlatformDependent.BYTE_ARRAY_OFFSET, array.length);
    }

    public static MemoryBlock fromLongArray(final long[] array) {
        return new MemoryBlock(array, PlatformDependent.LONG_ARRAY_OFFSET, array.length * 8);
    }
}
