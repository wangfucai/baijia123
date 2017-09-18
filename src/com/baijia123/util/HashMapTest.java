package com.baijia123.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HashMapTest {
    
    static final int MAXIMUM_CAPACITY = 1 << 30;
    
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //Map<String, Object> map = new HashMap<>(10);
        System.out.println(tableSizeFor(100));
        int n = 100 - 1;
        System.out.println(Long.toBinaryString(n));
        //System.out.println(n >>> 1);
        //System.out.println(n |= n >>> 1);
        //System.out.println(Long.toHexString(n |= n >>> 1));
        System.out.println(Long.toBinaryString(n |= n >>> 1));
        System.out.println(Long.toBinaryString(n |= n >>> 2));
        System.out.println(Long.toBinaryString(n |= n >>> 4));
        System.out.println(Long.toBinaryString(n |= n >>> 8));
        System.out.println(Long.toBinaryString(n |= n >>> 16));
        //System.out.println(n >>> 2);
        //System.out.println(n >>> 4);
        //System.out.println(n >>> 8);
        //System.out.println(n >>> 16);
        
        
        List<Integer> list = new ArrayList<>();
        
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        
        list.remove(2);
        list.remove(3);
        
        System.out.println(list);
        
    }

}
