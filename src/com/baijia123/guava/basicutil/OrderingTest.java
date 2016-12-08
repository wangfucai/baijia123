package com.baijia123.guava.basicutil;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

public class OrderingTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(OrderingTest.class);

    public static void main(String[] args) {
        // ������֤������Ϊ�ڲ����Ƕ�̬�ģ���static�ؼ������Σ���
        // ��main�����Ǿ�̬�ģ���ͨ���ڲ�����������ر�����һ�����ã�
        // ָ�򴴽�������Χ���������Ҫ��static�����������ʱ�Ѿ���ʼ����
        // �����ڲ���ı����ȴ����ⲿ�ࡣ
        // Foo foo1 = new OrderingTest().new Foo("test", 3);
        // Foo foo2 = new OrderingTest().new Foo("abc", 4);
        Foo foo1 = new Foo("test", 3);
        Foo foo2 = new Foo("abc", 4);

        LOGGER.debug(foo1.compareTo(foo2) + "");
        Ordering<String> byLengthOrdering = new Ordering<String>() {

            @Override
            public int compare(String left, String right) {
                // TODO Auto-generated method stub
                return Integer.compare(left.length(), right.length());
            }
        };
        LOGGER.debug(byLengthOrdering.compare("123", "1234") + "");

        List<String> list = Lists.newArrayList();
        list.add("abc");
        list.add("defg");
        list.add("hijk");
        list.add("lmn");
        list.add("bcd");
        list.add(null);

        Ordering<String> ordering = Ordering.natural();
        LOGGER.debug("natural = " + ordering.nullsFirst().sortedCopy(list));

        List<String> abc = ImmutableList.of("a", "b", "c");
        LOGGER.debug("abc = " + ordering.reverse().isStrictlyOrdered(abc));
        LOGGER.debug("max: " + ordering.max(abc) + " min: " + ordering.min(abc) + " leastOf: " + ordering.leastOf(abc, 3) + " greatestOf: "
                + ordering.greatestOf(abc, 3));
    }
}
