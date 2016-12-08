package com.baijia123.guava.collections;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;
import com.google.common.collect.RangeMap;
import com.google.common.collect.TreeRangeMap;

public class RangeMapTest {
    
    private static Logger LOGGER = LoggerFactory.getLogger(RangeMapTest.class);
    
    public static void main(String args[]) {
        RangeMap<Integer, String> rangeMap = TreeRangeMap.create();
        rangeMap.put(Range.closed(1, 10),  "foo");//{[1,10] => "foo"}
        rangeMap.put(Range.open(3, 6), "bar");//{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo"}
        rangeMap.put(Range.open(10, 20), "foo"); //{[1,3] => "foo", (3,6) => "bar", [6,10] => "foo", (10,20) => "foo"}
        rangeMap.remove(Range.closed(5, 11)); //{[1,3] => "foo", (3,5) => "bar", (11,20) => "foo"}
        LOGGER.debug(rangeMap.toString());
        
        Map<Range<Integer>, String> map = rangeMap.asMapOfRanges();
    }
}
