package com.baijia123.guava.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;

public class ImmutableSetTest {

    public static final ImmutableSet<String> COLOR_NAMES = ImmutableSet.of(
            "red", 
            "green", 
            "yellow", 
            "blue", 
            "gray");
    public static final ImmutableSet<Color> COLORS = 
            ImmutableSet.<Color> builder()
            .add(new Color(0, 122, 200))
            .build();
    
    public static final ImmutableSortedSet<String> abclist = ImmutableSortedSet.of("a", "b", "c", "d");
    private static final Logger LOGGER = LoggerFactory.getLogger(ImmutableSet.class);

    public static void main(String[] args) {
        ImmutableSet<String> foobar = ImmutableSet.of("foo", "bar", "biz");
        ImmutableList<String> defensiveCopy = ImmutableList.copyOf(foobar);
        LOGGER.debug(defensiveCopy + "");
    }
}
