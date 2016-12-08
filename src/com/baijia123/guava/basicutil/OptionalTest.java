package com.baijia123.guava.basicutil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Optional;

public class OptionalTest {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(OptionalTest.class);
    
    public static void main(String[] args) {
        Optional<Integer> possible = Optional.of(5);
        boolean ret = possible.isPresent();
        Integer result = possible.get();
        LOGGER.debug("result = " + result);
    }
}
