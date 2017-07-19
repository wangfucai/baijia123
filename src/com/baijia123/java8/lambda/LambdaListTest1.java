package com.baijia123.java8.lambda;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class LambdaListTest1 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<String> fruits = Arrays.asList("apple", "banana", "orange", "watermelon");
        //第一阶段
        Collections.sort(fruits, new Comparator<String>(){

            @Override
            public int compare(String a, String b) {
                // TODO Auto-generated method stub
                return b.compareTo(a);
            }
            
        });
        System.out.println("第1阶段：");
        fruits.forEach(System.out::println);
        //第二阶段
        Collections.sort(fruits, (a, b) -> b.compareTo(a));
        System.out.println("第2阶段：");
        fruits.forEach(System.out::println);
        //第三阶段
        fruits.sort((a, b) -> b.compareTo(a));
        System.out.println("第3阶段1：");
        fruits.forEach(System.out::println);
        fruits.sort(Collections.reverseOrder());
        System.out.println("第3阶段2：");
        fruits.forEach(System.out::println);
        
        List<String> fruits2 = Arrays.asList("apple", null, "banana", "orange", "watermelon");
        fruits2.sort(Comparator.nullsLast(String::compareTo));
        fruits2.forEach(System.out::println);
        
        System.out.println("列表为空：");
        List<String> names3 = null;
        Optional.ofNullable(names3).ifPresent(list -> list.sort(Comparator.naturalOrder()));
        System.out.println(names3);
    }

}
