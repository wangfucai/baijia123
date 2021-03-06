package com.baijia123.java8.lambda;

import java.util.HashMap;
import java.util.function.BiConsumer;

public class LambdaBiConsumerTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        BiConsumer<String, Integer> printKeyAndValue = (key, value) -> System.out.println(key + "-" + value);
        printKeyAndValue.accept("One",1);
        printKeyAndValue.accept("Two",2);
        
        System.out.println("##################");
        
        HashMap<String, Integer> dummyValues = new HashMap<>();
        dummyValues.put("One", 1);
        dummyValues.put("Two", 2);
        dummyValues.put("Three", 3);

        dummyValues.forEach((key,value) -> System.out.println(key+"-"+value));
    }

}
