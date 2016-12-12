package com.baijia123.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

//�ο�http://ifeve.com/stream/
public class StreamTest {
    public static void main(String[] argv) {
        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
        System.out.println(nums.stream().filter(num -> num != null).count());
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
        Stream<String> stringStream = Stream.of("taobao");

        // �����������ö���һ���ģ�ֻ��ʹ����lambda���ʽ�ͷ������õ��﷨���򻯴���
        // ����һ�����޳��ȵ�Stream������ֵ������ġ�������޳���Stream�������أ�һ���������޳��ȵ�Stream�������Stream��limit()��������
        Stream.generate(new Supplier<Double>() {

            @Override
            public Double get() {
                // TODO Auto-generated method stub
                return Math.random();
            }

        });
        Stream.generate(() -> Math.random());
        Stream.generate(Math::random);
        // ��Ԫ�ص��������ظ��Ը���������ֵ(seed)�����û�ָ�����������ɵġ����а�����Ԫ�ؿ�����Ϊ�ǣ�seed��f(seed),f(f(seed))����ѭ��
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

        // 1. distinct: ����Stream�а�����Ԫ�ؽ���ȥ�ز�����ȥ���߼�����Ԫ�ص�equals�������������ɵ�Stream��û���ظ���Ԫ��
        // 2. filter: ����Stream�а�����Ԫ��ʹ�ø����Ĺ��˺������й��˲����������ɵ�Streamֻ��������������Ԫ��
        // 3. map: ����Stream�а�����Ԫ��ʹ�ø�����ת����������ת�������������ɵ�Streamֻ����ת�����ɵ�Ԫ��
        // 4. flatMap����map���ƣ���ͬ������ÿ��Ԫ��ת���õ�����Stream���󣬻����Stream�е�Ԫ��ѹ������������
        // 5. peek: ����һ������ԭStream������Ԫ�ص���Stream��ͬʱ���ṩһ�����Ѻ�����Consumerʵ��������Streamÿ��Ԫ�ر����ѵ�ʱ�򶼻�ִ�и��������Ѻ���
        // 6. limit: ��һ��Stream���нضϲ�������ȡ��ǰN��Ԫ�أ����ԭStream�а�����Ԫ�ظ���С��N���Ǿͻ�ȡ�����е�Ԫ��
        // 7. skip: ����һ������ԭStream��ǰN��Ԫ�غ�ʣ��Ԫ����ɵ���Stream�����ԭStream�а�����Ԫ�ظ���С��N����ô���ؿ�Stream
        // note:ת����������lazy�ģ����ת������ֻ���ڻ�۲��������½ڣ���ʱ���ں�������һ��ѭ�����
        List<Integer> nums1 = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        int sum = nums1.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum();
        System.out.println("sum is : " + sum);

        // Reduce Stream
        // Stream�ӿ���һЩͨ�õĻ�۲���������reduce()��collect()��Ҳ��һЩ�ض���;�Ļ�۲���������sum(),max()��count()��
        // ע�⣺sum�����������е�Stream�����еģ�ֻ��IntStream��LongStream��DoubleStream��ʵ�����С�
        // �����������������ܻ�۲�����
        // �ɱ��ۣ��������Ԫ�����ۻ���һ���ɱ�������У�����Collection����StringBuilder��
        // ������ۣ���ȥ�ɱ���ʣ�µģ�һ�㶼����ͨ�������޸�ĳ���ɱ���󣬶���ͨ����ǰһ�εĻ�۽��������һ�ε���Σ�������ˡ�����reduce��count��allMatch��

        // �ɱ��۶�Ӧ��ֻ��һ��������collect��������������ʾ�ģ������԰�Stream�е�Ҫ��Ԫ���ռ���һ����������У�����Collection��
        List<Integer> nums2 = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        List<Integer> numsWithoutNull = nums2.stream().filter(num -> num != null)
                .collect(() -> new ArrayList<Integer>(), (list, item) -> list.add(item), (list1, list2) -> list1.addAll(list2));
        
        List<Integer> numsWithoutNull1 = nums.stream().filter(num -> num != null).collect(Collectors.toList());
        List<Integer> ints = Lists.newArrayList(1,2,3,4,5,6,7,8,9,10);
        System.out.println("ints sum is:" + ints.stream().reduce((sum1, item) -> sum1 + item).get());
        System.out.println("ints1 sum is:" + ints.stream().reduce(0, (sum2, item) -> sum2 + item));
        System.out.println("ints2 sum is:" + ints.stream().count());


    }
}
