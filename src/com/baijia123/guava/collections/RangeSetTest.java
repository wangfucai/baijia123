package com.baijia123.guava.collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.TreeRangeSet;

//RangeSet������һ�鲻�����ġ��ǿյ����䡣����һ��������ӵ��ɱ��RangeSetʱ����������������ᱻ�ϲ���������ᱻ����
public class RangeSetTest {
    
    private static Logger LOGGER = LoggerFactory.getLogger(RangeSetTest.class);
    public static void main(String args[]) {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10));// {[1,10]}
        rangeSet.add(Range.closedOpen(11,  15));//����������:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20));//��������; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0,  0));//������; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10));//�ָ�[1, 10]; {[1,5], [10,10], [11,20)}
        LOGGER.debug(rangeSet.toString());
        LOGGER.debug(rangeSet.complement().toString());//����RangeSet�Ĳ�����ͼ
        
        RangeSet<Integer> otherRangeSet = TreeRangeSet.create();
        otherRangeSet.add(Range.closed(7,18));
        LOGGER.debug(rangeSet.subRangeSet(otherRangeSet.span()).toString());//����RangeSet�����Range�Ľ�����ͼ,span()�����ذ���RangeSet�������������С���䡣
        
        LOGGER.debug(rangeSet.rangeContaining(3).toString());//���ذ�������Ԫ�ص����䣻��û�����������䣬�򷵻�null��
    }
}
