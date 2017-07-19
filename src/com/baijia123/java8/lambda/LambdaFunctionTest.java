package com.baijia123.java8.lambda;

import java.util.Comparator;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class LambdaFunctionTest {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub

        // Predicates
        Predicate<String> predicate = (s) -> s.length() > 0;
        predicate.test("foo");
        predicate.negate().test("foo");

        Predicate<Object> notNull = Objects::nonNull;
        Predicate<Object> isNull = Objects::isNull;

        Predicate<String> isEmpty = String::isEmpty;
        Predicate<String> isNotEmpty = isEmpty.negate();

        // Functions
        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger.andThen(String::valueOf);

        backToString.apply("123");

        // Supplier
        Supplier<Person> personSupplier = Person::new;
        personSupplier.get();

        // Consumer
        Consumer<Person> greeter = (p) -> System.out.println("Hello, " + p.getFirstName());
        greeter.accept(new Person("wang", "tale"));

        // Comparator
        // Comparator<Person> comparator = (p1, p2) -> p1.getFirstName().compareTo(p2.getFirstName());
        // Comparator<Person> comparator = Comparator.comparing( p -> p.getFirstName());
        Comparator<Person> comparator = Comparator.comparing(Person::getFirstName);
        Person p1 = new Person("first1", "last1");
        Person p2 = new Person("first2", "last2");
        comparator.compare(p1, p2);
        comparator.reversed().compare(p1, p2);

        System.out.println(comparator.compare(p1, p2));
        
        // Runnable
        Runnable runnable = () -> System.out.println(UUID.randomUUID());
        runnable.run();
        
        // Callable
        Callable<UUID> callable = UUID::randomUUID;
        callable.call();
    }

}
