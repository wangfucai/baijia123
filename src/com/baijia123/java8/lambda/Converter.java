package com.baijia123.java8.lambda;

@FunctionalInterface
public interface Converter<F, T> {
    T converter(F from);
}
