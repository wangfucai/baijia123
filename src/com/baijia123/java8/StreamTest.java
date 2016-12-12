package com.baijia123.java8;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.google.common.collect.Lists;

//参考http://ifeve.com/stream/
public class StreamTest {
    public static void main(String[] argv) {
        List<Integer> nums = Lists.newArrayList(1, null, 3, 4, null, 6);
        System.out.println(nums.stream().filter(num -> num != null).count());
        Stream<Integer> integerStream = Stream.of(1, 2, 3, 5);
        Stream<String> stringStream = Stream.of("taobao");

        // 三条语句的作用都是一样的，只是使用了lambda表达式和方法引用的语法来简化代码
        // 生成一个无限长度的Stream，其中值是随机的。这个无限长度Stream是懒加载，一般这种无限长度的Stream都会配合Stream的limit()方法来用
        Stream.generate(new Supplier<Double>() {

            @Override
            public Double get() {
                // TODO Auto-generated method stub
                return Math.random();
            }

        });
        Stream.generate(() -> Math.random());
        Stream.generate(Math::random);
        // 其元素的生成是重复对给定的种子值(seed)调用用户指定函数来生成的。其中包含的元素可以认为是：seed，f(seed),f(f(seed))无限循环
        Stream.iterate(1, item -> item + 1).limit(10).forEach(System.out::println);

        // 1. distinct: 对于Stream中包含的元素进行去重操作（去重逻辑依赖元素的equals方法），新生成的Stream中没有重复的元素
        // 2. filter: 对于Stream中包含的元素使用给定的过滤函数进行过滤操作，新生成的Stream只包含符合条件的元素
        // 3. map: 对于Stream中包含的元素使用给定的转换函数进行转换操作，新生成的Stream只包含转换生成的元素
        // 4. flatMap：和map类似，不同的是其每个元素转换得到的是Stream对象，会把子Stream中的元素压缩到父集合中
        // 5. peek: 生成一个包含原Stream的所有元素的新Stream，同时会提供一个消费函数（Consumer实例），新Stream每个元素被消费的时候都会执行给定的消费函数
        // 6. limit: 对一个Stream进行截断操作，获取其前N个元素，如果原Stream中包含的元素个数小于N，那就获取其所有的元素
        // 7. skip: 返回一个丢弃原Stream的前N个元素后剩下元素组成的新Stream，如果原Stream中包含的元素个数小于N，那么返回空Stream
        // note:转换操作都是lazy的，多个转换操作只会在汇聚操作（见下节）的时候融合起来，一次循环完成
        List<Integer> nums1 = Lists.newArrayList(1, 1, null, 2, 3, 4, null, 5, 6, 7, 8, 9, 10);
        int sum = nums1.stream().filter(num -> num != null).distinct().mapToInt(num -> num * 2).peek(System.out::println).skip(2).limit(4).sum();
        System.out.println("sum is : " + sum);

        // Reduce Stream
        // Stream接口有一些通用的汇聚操作，比如reduce()和collect()；也有一些特定用途的汇聚操作，比如sum(),max()和count()。
        // 注意：sum方法不是所有的Stream对象都有的，只有IntStream、LongStream和DoubleStream是实例才有。
        // 下面会分两部分来介绍汇聚操作：
        // 可变汇聚：把输入的元素们累积到一个可变的容器中，比如Collection或者StringBuilder；
        // 其他汇聚：除去可变汇聚剩下的，一般都不是通过反复修改某个可变对象，而是通过把前一次的汇聚结果当成下一次的入参，反复如此。比如reduce，count，allMatch；

        // 可变汇聚对应的只有一个方法：collect，正如其名字显示的，它可以把Stream中的要有元素收集到一个结果容器中（比如Collection）
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
