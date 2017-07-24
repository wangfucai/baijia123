package com.baijia123.unsafe.util;

public class HeapMemoryAllocator implements MemoryAllocator {

    @Override
    public MemoryBlock allocate(long size) throws OutOfMemoryError {
        // TODO Auto-generated method stub
        long[] array = new long[(int) (size / 8)];
        return MemoryBlock.fromLongArray(array);
    }

    @Override
    public void free(MemoryBlock memory) {
        // TODO Auto-generated method stub

    }

}
