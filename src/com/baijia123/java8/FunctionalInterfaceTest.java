package com.baijia123.java8;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class FunctionalInterfaceTest {
    public static void main(String[] argv) {
        // Converter<String, Integer> converter = (from) -> Integer.valueOf(from);
        // 通过静态方法引用来表示
        // Java 8 允许你使用 :: 关键字来传递方法或者构造函数引用,也可以引用一个对象的方法
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);

        Predicate<String> predicate = (s) -> s.length() > 0;
        System.out.println(predicate.test("foo"));
        predicate.negate().test("foo"); // false

        // Predicate接口
        // Predicate 接口只有一个参数，返回boolean类型。该接口包含多种默认方法来将Predicate组合成其他复杂的逻辑（比如：与，或，非）
        Predicate<Boolean> nonNull = Objects::nonNull;
        Predicate<Boolean> isNull = Objects::isNull;
        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        // Function接口
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);
        backToString.apply("123"); // "123"

        // Supplier 接口 Supplier 接口返回一个任意范型的值，和Function接口不同的是该接口没有任何参数
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get(); // new Person

        // Consumer 接口表示执行在单个参数上的操作。
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
        greeter.accept(new Person("Luke", "Skywalker"));

        // Comparator 接口
        Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        comparator.compare(p1, p2); // > 0
        comparator.reversed().compare(p1, p2); // < 0

        // Optional 接口
        Optional<String> optional = Optional.of("bam");
        optional.isPresent(); // true
        optional.get(); // "bam"
        optional.orElse("fallback"); // "bam"
        optional.ifPresent((s) -> System.out.println(s.charAt(0))); // "b"

        // Stream 接口
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream().filter((s) -> s.startsWith("a")).forEach(System.out::println);

        stringCollection.stream().sorted().filter((s) -> s.startsWith("a")).forEach(System.out::println);
        stringCollection.stream().map(String::toUpperCase).sorted((a, b) -> b.compareTo(a)).forEach(System.out::println);

        boolean anyStartsWithA = stringCollection.stream().anyMatch((s) -> s.startsWith("a"));
        System.out.println("1 = " + anyStartsWithA);

        boolean allStartsWithA = stringCollection.stream().allMatch((s) -> s.startsWith("a"));
        System.out.println("2 = " + allStartsWithA);

        boolean noneStartsWithZ = stringCollection.stream().noneMatch((s) -> s.startsWith("z"));
        System.out.println(noneStartsWithZ); // true

        long startsWithB = stringCollection.stream().filter((s) -> s.startsWith("b")).count();
        System.out.println(startsWithB); // 3

        // Reduce 规约
        Optional<String> reduced = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        reduced.ifPresent(System.out::println);
        
        int max = 1000000;
        List<String> values = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }
        
        //串行排序
        /*
        long t0 = System.nanoTime();
        long count = values.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));
        */
        
        //并行排序
        /*long t0 = System.nanoTime();
        long count = values.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parallel sort took: %d ms", millis));*/
        
        Map<Integer, String> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }
        
        map.computeIfPresent(3, (num, val) -> val + num);
        map.forEach((id, val) -> System.out.println(val));
        map.computeIfPresent(9, (num, val) -> null);
        map.containsKey(9);     // false
        map.computeIfAbsent(23, num -> "val" + num);
        map.containsKey(23);    // true
    }
}
