package com.baijia123.unsafe.util;

import javax.annotation.Nullable;

public class MemoryLocation {
    @Nullable
    final Object obj;

    final long offset;

    MemoryLocation(@Nullable Object obj, long offset) {
        this.obj = obj;
        this.offset = offset;
    }

    public final Object getBaseObject() {
        return obj;
    }

    public final long getBaseOffset() {
        return offset;
    }
}
