package com.baijia123.guava.basicutil;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;

public class Test123 {

    public static void main(String[] args) {
        String a = "aaAbbc";
        String b = "abc";
        String c = "def";
        
       String a1 = a.toLowerCase();
       String b1 = b.toLowerCase();
       String c1 = c.toLowerCase();
       
       String all = a1 + b1 + c1;
       char[] allChar = all.toCharArray();
       List<String> list = new ArrayList<>();
       for(int i = 0 ; i < allChar.length ; i ++) {
           list.add(String.valueOf(allChar[i]));
       }
      
       Multiset<String> multiset = HashMultiset.create();
       for(int i = 0 ; i< list.size(); i ++){
           multiset.add(list.get(i));
       }
       for (String element : multiset.elementSet()) {
           System.out.println(element + ":" + multiset.count(element));
       }
    }
}
