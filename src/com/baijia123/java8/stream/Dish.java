package com.baijia123.java8.stream;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;

    public enum Type {
        MEAT, FISH, OTHER
    }
}
