package com.baijia123.collections;

import java.util.ArrayList;
import java.util.List;

public class SubListTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        List<Integer> subList = list.subList(1, 5);
        for (int i = 0; i < subList.size(); i++) {
            System.out.println("subList " + i + " = " + subList.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("list " + i + " = " + list.get(i));
        }
        // 修改子列表会同时修改父表的值
        //subList.set(0, 20);
        System.out.println("===============after===============");
        for (int i = 0; i < subList.size(); i++) {
            System.out.println("after subList " +  i + " = " + subList.get(i));
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.println("after list " +  i + " = " + list.get(i));
        }
        /*
        list.add(11);

        for (int i = 0; i < list.size(); i++) {
            System.out.println("modify list i = " + list.get(i));
        }
        // 修改父表的长度,那么之前产生的子list将会失效，变得不可使用。java.util.ConcurrentModificationException
        for (int i = 0; i < subList.size(); i++) {
            System.out.println("modify subList i = " + subList.get(i));
        }
        */
        //System.out.println(list.subList(1, 5).subList(1, 3));
        System.out.println(list.subList(1, 9).subList(1, 5));
        //list.subList(1, 9).subList(1, 5).set(3, 10);
        list.subList(1, 9).subList(1, 5).add(0, 10);
        System.out.println(list);
//        subList.clear();
//        for (int i = 0; i < list.size(); i++) {
//            System.out.println("clear list i = " + list.get(i));
//        }
        
        
    }

}
