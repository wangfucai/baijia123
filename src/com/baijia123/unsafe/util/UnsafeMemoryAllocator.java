package com.baijia123.unsafe.util;

public class UnsafeMemoryAllocator implements MemoryAllocator {

    @Override
    public MemoryBlock allocate(long size) throws OutOfMemoryError {
        // TODO Auto-generated method stub
        long address = PlatformDependent.UNSAFE.allocateMemory(size);
        PlatformDependent.UNSAFE.setMemory(address, size, (byte) 0);
        return new MemoryBlock(null, address, size);
    }

    @Override
    public void free(MemoryBlock memory) {
        // TODO Auto-generated method stub
        if (memory.obj != null) {
            PlatformDependent.UNSAFE.freeMemory(memory.offset);
        }
    }

}
