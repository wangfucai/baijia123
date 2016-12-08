package com.baijia123.guava.basicutil;

import com.google.common.base.Preconditions;

public class PreconditionTest {
    public static void main(String[] args){
        String sourceData = "";
        int i = 0;
        Preconditions.checkArgument(i > 0, "Argument was %s but expected nonnegative", i);
        Preconditions.checkNotNull(sourceData);
    }
}
