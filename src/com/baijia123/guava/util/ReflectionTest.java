package com.baijia123.guava.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.reflect.TypeToken;

public class ReflectionTest {
    public static void main(String[] argv) throws NoSuchMethodException, SecurityException {
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        System.out.println(stringList.getClass().getCanonicalName());
        System.out.println(stringList.getClass().isAssignableFrom(intList.getClass()));
        
        TypeToken<String> stringTok = TypeToken.of(String.class);
        TypeToken<Integer> intTok = TypeToken.of(Integer.class);
        
        TypeToken<List<String>> stringListTok = new TypeToken<List<String>>() {};
        System.out.println(stringListTok.getType().getTypeName());
        
        TypeToken<Function<Integer, String>> funToken = new TypeToken<Function<Integer, String>>() {};
        TypeToken<?> funResultToken = funToken.resolveType(Function.class.getTypeParameters()[0]);
        System.out.println(funResultToken.getRawType().getTypeName());
        
        TypeToken<Map<String, Integer>> mapToken = new TypeToken<Map<String, Integer>>() {};
        TypeToken<?> entrySetToken = mapToken.resolveType(Map.class.getMethod("entrySet").getGenericReturnType());
        System.out.println(entrySetToken.getType().getTypeName());
    }
}
