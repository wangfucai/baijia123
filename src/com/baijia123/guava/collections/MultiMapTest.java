package com.baijia123.guava.collections;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;

public class MultiMapTest {
    
    private static Logger LOGGER = LoggerFactory.getLogger(MultiMapTest.class);
    public static void main(String[] args) {
        Multimap<String, String> myMultimap = ArrayListMultimap.create();
        myMultimap.put("fruits", "banana");
        myMultimap.put("fruits", "apple");
        myMultimap.put("fruits", "pear");
        myMultimap.put("fruits", "pear");
        myMultimap.put("vegetables", "carrot");

        LOGGER.debug("size = " + myMultimap.size());
        Collection<String> fruits = myMultimap.get("fruits");
        LOGGER.debug("fruits = " + fruits);
        LOGGER.debug("copy fruits = " + ImmutableSet.copyOf(fruits));

        Collection<String> vegetables = myMultimap.get("vegetables");
        LOGGER.debug("vegetables = " + vegetables);

        for (String value : myMultimap.values()) {
            LOGGER.debug("value = " + value);
        }
        myMultimap.remove("fruits", "pear");
        LOGGER.debug("fruits = " + myMultimap.get("fruits"));
        myMultimap.removeAll("fruits");
        LOGGER.debug("fruits = " + myMultimap.get("fruits"));
    }
}
