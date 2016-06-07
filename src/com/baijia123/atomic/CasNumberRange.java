package com.baijia123.atomic;

import java.util.concurrent.atomic.AtomicReference;

public class CasNumberRange {
    private static class IntPair {
        final int lower;
        final int uppper;

        public IntPair(int lower, int uppper) {
            super();
            this.lower = lower;
            this.uppper = uppper;
        }
    }

    private final AtomicReference<IntPair> values = new AtomicReference<IntPair>(new IntPair(0, 0));

    public int getLower() {
        return values.get().lower;
    }

    public int getUpper() {
        return values.get().uppper;
    }

    public void setLower(int i) {
        while (true) {
            IntPair oldv = values.get();
            if (i > oldv.uppper) {
                throw new IllegalArgumentException("Can't set lower to " + i + " > upper");
            }
            IntPair newv = new IntPair(i, oldv.uppper);
            if (values.compareAndSet(oldv, newv)) {
                return;
            }
        }
    }
}
