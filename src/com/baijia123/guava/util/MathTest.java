package com.baijia123.guava.util;

import java.math.RoundingMode;

import com.google.common.math.IntMath;

public class MathTest {
    public static void main(String[] argv) {
        System.out.println(IntMath.divide(3, 4, RoundingMode.CEILING));
        System.out.println(IntMath.log2(5, RoundingMode.DOWN));
        System.out.println(IntMath.sqrt(10, RoundingMode.FLOOR));
    }
}
