package com.baijia123.unsafe.util;

public interface MemoryAllocator {
    public MemoryBlock allocate(long size) throws OutOfMemoryError;

    public void free(MemoryBlock memory);

    public static final MemoryAllocator UNSAFE = new UnsafeMemoryAllocator();

    public static final MemoryAllocator HEAP = new HeapMemoryAllocator();
}
