package com.baijia123.java8.lambda;

@FunctionalInterface
public interface Formula {
    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(positive(a));
    }

    static int positive(int a) {
        return a > 0 ? a : 0;
    }
}
