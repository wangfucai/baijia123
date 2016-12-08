package com.baijia123.guava.util;

import java.util.Iterator;
import java.util.List;

import com.google.common.collect.AbstractIterator;
import com.google.common.collect.AbstractSequentialIterator;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.PeekingIterator;

public class IteratorTest {
    public static void main(String[] argv) {
        List<String> source = Lists.newArrayList("a", "a", "c");
        List<String> result = Lists.newArrayList();
        PeekingIterator<String> it = Iterators.peekingIterator(source.iterator());
        while (it.hasNext()) {
            String current = it.next();
            while (it.hasNext() && it.peek().equals(current)) {
                it.next();
            }
            result.add(current);
        }
        System.out.println(result);
    }

    // ����Ҫ��װһ��iterator��������ֵ
    // ע�⣺AbstractIterator�̳���UnmodifiableIterator�����Խ�ֹʵ��remove()�������������Ҫ֧��remove()�ĵ��������Ͳ�Ӧ�ü̳�AbstractIterator��
    public static Iterator<String> skipNulls(final Iterator<String> in) {
        return new AbstractIterator<String>() {

            @Override
            protected String computeNext() {
                // TODO Auto-generated method stub
                while (in.hasNext()) {
                    String s = in.next();
                    if (s != null) {
                        return s;
                    }
                }
                return null;
            }

        };
    }

    // ע���ʼֵΪ1
    private Iterator<Integer> powerOfTwo = new AbstractSequentialIterator<Integer>(1) {

        @Override
        protected Integer computeNext(Integer previous) {
            // TODO Auto-generated method stub
            return (previous == 1 << 30) ? null : previous * 2;
        }
    };

}
