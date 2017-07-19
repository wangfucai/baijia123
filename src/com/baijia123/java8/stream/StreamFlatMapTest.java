package com.baijia123.java8.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StreamFlatMapTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<Integer> number1 = Arrays.asList(1, 2, 3);
        List<Integer> number2 = Arrays.asList(3, 4);

        List<Object> pairs = number1.stream().flatMap(i -> number2.stream().map(j -> new int[] { i, j })).collect(Collectors.toList());
        pairs.forEach(o -> System.out.println(Arrays.toString((int[]) o)));
        
        System.out.println("########################");
        List<Object> pairs2 = number1.stream().flatMap(i -> number2.stream().filter(j -> (i + j) % 3 == 0).map(j -> new int[] { i, j })).collect(Collectors.toList());
        pairs2.forEach(o -> System.out.println(Arrays.toString((int[]) o)));
    }

}
