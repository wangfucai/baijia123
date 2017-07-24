package com.baijia123.unsafe.util;

public final class LongArray {
    private static final int WIDTH = 8;
    private static final long ARRAY_OFFSET = PlatformDependent.LONG_ARRAY_OFFSET;

    private final MemoryBlock memory;
    private final Object baseObj;
    private final long baseOffset;

    private final long length;

    public LongArray(MemoryBlock memory) {
        assert memory.size() % WIDTH == 0 : "Memory not aligned (" + memory.size() + ")";
        this.memory = memory;
        this.baseObj = memory.getBaseObject();
        this.baseOffset = memory.getBaseOffset();
        this.length = memory.size() / WIDTH;
    }

    public MemoryBlock memoryBlock() {
        return memory;
    }

    public long size() {
        return length;
    }

    public void set(long index, long value) {
        assert index >= 0 : "index (" + index + ") should >= 0";
        assert index < length : "index (" + index + ") should < length (" + length + ")";
        PlatformDependent.UNSAFE.putLong(baseObj, baseOffset + index * WIDTH, value);
    }

    public long get(long index) {
        assert index >= 0 : "index (" + index + ") should >= 0";
        assert index < length : "index (" + index + ") should < length (" + length + ")";
        return PlatformDependent.UNSAFE.getLong(baseObj, baseOffset + index * WIDTH);
    }

    public long[] toJvmArray() throws IndexOutOfBoundsException {
        if (length > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("array size (" + length + ") too large and cannot be converted into JVM array");
        }

        final long[] arr = new long[(int) length];
        PlatformDependent.UNSAFE.copyMemory(baseObj, baseOffset, arr, ARRAY_OFFSET, length * WIDTH);
        return arr;
    }
}
