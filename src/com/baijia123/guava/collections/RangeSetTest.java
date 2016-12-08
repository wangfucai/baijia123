package com.baijia123.guava.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

//RangeSet描述了一组不相连的、非空的区间。当把一个区间添加到可变的RangeSet时，所有相连的区间会被合并，空区间会被忽略
public class RangeSetTest {
    
    private static Logger LOGGER = LoggerFactory.getLogger(RangeSetTest.class);
    public static void main(String args[]) {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));// {[1,10]}
        rangeSet.add(Range.closedOpen(11,  15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20));//相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0,  0));//空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10));//分割[1, 10]; {[1,5], [10,10], [11,20)}
        LOGGER.debug(rangeSet.toString());
        LOGGER.debug(rangeSet.complement().toString());//返回RangeSet的补集视图
        
        RangeSet<Integer> otherRangeSet = TreeRangeSet.create();
        otherRangeSet.add(Range.closed(7,18));
        LOGGER.debug(rangeSet.subRangeSet(otherRangeSet.span()).toString());//返回RangeSet与给定Range的交集视图,span()：返回包括RangeSet中所有区间的最小区间。
        
        LOGGER.debug(rangeSet.rangeContaining(3).toString());//返回包含给定元素的区间；若没有这样的区间，则返回null。
    }
}
