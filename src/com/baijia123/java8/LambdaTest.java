package com.baijia123.java8;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaTest {
    public static void main(String[] argv) {
        List<String> names = Arrays.asList("peter", "anna", "mike", "xenial");

        Collections.sort(names, new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                // TODO Auto-generated method stub
                return o2.compareTo(o1);
            }

        });

        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });
        Collections.sort(names, (String a, String b) -> b.compareTo(a));
        Collections.sort(names, (a, b) -> b.compareTo(a));
    }
}
