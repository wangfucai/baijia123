package com.baijia123.unsafe.util;

public final class BytesToLongMap {
    private static final Murmur3_x86_32 HASHER = new Murmur3_x86_32(0);

    /** Bit mask for the lower 37 bits of a long. */
    private static final long MASK_LONG_LOWER_37_BITS = 0x1FFFFFFFFFL;
    // 0b11111_11111111_11111111_11111111_11111111L;

    /** Bit mask for the upper 27 bits of a long. */
    private static final long MASK_LONG_UPPER_27_BITS = ~MASK_LONG_LOWER_37_BITS;

    /** Bit mask for the upper 27 bits of an int, i.e. bit 5 - 31 (inclusive) for a long. */
    private static final int MASK_INT_UPPER_27_BITS;
    // 0b11111111_11111111_11111111_11100000;

    static {
        MASK_INT_UPPER_27_BITS = ((1 << 27) - 1) << 5;
    }

    /**
     * A single array to store the key and value.
     *
     * Position {@code 2 * i} in the array is used to track the key at index {@code i}, while position {@code 2 * i + 1} in the array is used to track
     * the value at index {@code i}.
     */
    private final LongArray longArray;

    /**
     * A {@link BitSet} used to track location of the map where the key is set. Size of the bitset should be half of the size of the long array.
     */
    private final BitSet bitset;

    /**
     * Number of keys defined in the map.
     */
    private long size;

    private long capacity;

    private long mask;

    private final Location loc;

    public BytesToLongMap(LongArray longArray, BitSet bitset) {
        assert longArray.size() == bitset.capacity() * 2 : "array (" + longArray.size() + ") should be twice the capacity of the bitset ("
                + bitset.capacity() + ")";
        this.longArray = longArray;
        this.bitset = bitset;
        this.capacity = bitset.capacity();
        this.mask = capacity - 1;
        this.loc = new Location();
    }

    /**
     * Returns the number of keys defined in the map.
     */
    public long size() {
        return size;
    }

    /**
     * Returns true if the key is defined in this map.
     */
    public boolean containsKey(long key) {
        return lookup(key).isDefined();
    }

    /**
     * Updates the value the key maps to.
     */
    public void put(long key, long value) {
        lookup(key).setValue(value);
    }

    /**
     * Returns the value to which the specified key is mapped. In the case the key is not defined, this has undefined behavior.
     */
    public long get(long key) {
        return lookup(key).getValue();
    }

    /**
     * Looks up a key, and return a {@link Location} handle that can be used to test existence and read/write values.
     *
     * This function always return the same {@link Location} instance to avoid object allocation.
     */
    public Location lookup(long key) {
        long hashcode = HASHER.hashLong(key);
        long partialKeyHashCode = (hashcode & MASK_INT_UPPER_27_BITS) >> 5;
        long pos = hashcode & mask;
        long step = 1;
        while (true) {
            if (!bitset.isSet(pos)) {
                // This is a new key.
                return loc.with(pos, key, false);
            } else {
                long stored = longArray.get(pos * 2);
                if (((stored & MASK_LONG_UPPER_27_BITS) >> 27) == partialKeyHashCode) {
                    // Partial hash code matches. There is a high likelihood this is the place.
                    long pointer = stored & MASK_LONG_LOWER_37_BITS;
                    return loc.with(pos, key, true);
                }
            }
            pos = (pos + step) & mask;
            step++;
        }
    }

    /**
     * Handle returned by {@link LongToLongMap#lookup(long)} function.
     */
    public final class Location {
        private long pos;
        private long key;
        private boolean isDefined;

        Location with(long pos, long key, boolean isDefined) {
            this.pos = pos;
            this.key = key;
            this.isDefined = isDefined;
            return this;
        }

        /**
         * Returns true if the key is defined at this position, and false otherwise.
         */
        public boolean isDefined() {
            return isDefined;
        }

        /**
         * Returns the key defined at this position. Unspecified behavior if the key is not defined.
         */
        public long getKey() {
            return longArray.get(pos * 2);
        }

        /**
         * Returns the value defined at this position. Unspecified behavior if the key is not defined.
         */
        public long getValue() {
            return longArray.get(pos * 2 + 1);
        }

        /**
         * Updates the value defined at this position. Unspecified behavior if the key is not defined.
         */
        public void setValue(long value) {
            if (!isDefined) {
                size++;
                bitset.set(pos);
                longArray.set(pos * 2, key);
            }
            longArray.set(pos * 2 + 1, value);
        }
    }
}
