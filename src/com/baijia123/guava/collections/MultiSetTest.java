package com.baijia123.guava.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;

public class MultiSetTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MultiSetTest.class);

    public static void main(String[] args) {
        String[] words = { "abc", "cdf", "abc", "adb" };
        Map<String, Integer> counts = new HashMap<String, Integer>();
        for (String word : words) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }
        LOGGER.debug("counts = " + counts);
        Multiset<String> multiset = HashMultiset.create();
        for (String word : words) {
            multiset.add(word);
        }
        for (String element : multiset.elementSet()) {
            LOGGER.debug(element + ":" + multiset.count(element));
        }
        
    }
}
