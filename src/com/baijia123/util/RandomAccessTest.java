package com.baijia123.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class RandomAccessTest {

    public static void initList(List<Integer> list, int n) {
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
    }

    public static void traverseWithLoop(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int index = 0; index < 5000; index++) {
            for (int i = 0; i < list.size(); i++) {
                list.get(i);
            }
        }
        System.out.println(list.getClass().getName() + "使用循环共用了" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void traverseWithItr(List<Integer> list) {
        long start = System.currentTimeMillis();
        for (int index = 0; index < 5000; index++) {
            Iterator<Integer> itr = list.iterator();
            while (itr.hasNext()) {
                itr.next();
            }
        }

        System.out.println(list.getClass().getName() + "使用迭代共用了" + (System.currentTimeMillis() - start) + "ms");
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        ArrayList<Integer> arrayList = new ArrayList<>(); 
        LinkedList<Integer> linkedList = new LinkedList<>();
        initList(arrayList, 1000);
        initList(linkedList, 1000);
        traverseWithLoop(arrayList);
        traverseWithItr(arrayList);
        traverseWithLoop(linkedList);
        traverseWithItr(linkedList);
    }

}
