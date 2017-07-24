package com.baijia123.unsafe.util;

public final class DoubleArray {
    private static final int WIDTH = 8;
    private static final long ARRAY_OFFSET = PlatformDependent.DOUBLE_ARRAY_OFFSET;

    private final MemoryBlock memory;
    private final Object baseObj;
    private final long baseOffset;

    private final long length;

    public DoubleArray(MemoryBlock memory) {
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

    public void set(long index, double value) {
        assert index >= 0 : "index (" + index + ") should >= 0";
        assert index < length : "index (" + index + ") should < length (" + length + ")";
        PlatformDependent.UNSAFE.putDouble(baseObj, baseOffset + index * WIDTH, value);
    }

    public double get(long index) {
        assert index >= 0 : "index (" + index + ") should >= 0";
        assert index < length : "index (" + index + ") should < length (" + length + ")";
        return PlatformDependent.UNSAFE.getDouble(baseObj, baseOffset + index * WIDTH);
    }

    public double[] toJvmArray() throws IndexOutOfBoundsException {
        if (length > Integer.MAX_VALUE) {
            throw new IndexOutOfBoundsException("array size (" + length + ") too large and cannot be converted into JVM array");
        }

        final double[] arr = new double[(int) length];
        PlatformDependent.UNSAFE.copyMemory(baseObj, baseOffset, arr, ARRAY_OFFSET, length * WIDTH);
        return arr;
    }
}
