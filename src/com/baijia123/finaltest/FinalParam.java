package com.baijia123.finaltest;

import java.util.ArrayList;
import java.util.List;

/**
 * final不外乎主要有以下3个作用：
 * 1.final放在class前，拒绝该类被继承;
 * 2.final放在类的方法名前，拒绝子类重写该方法；
 * 3.final放在域或变量前，一旦域或变量赋值或引用对象，就再也不能重新赋值或引用其他对象。
 * @author WangFuCai
 *
 */
public class FinalParam {
    
    public void testFinal(List<Integer> list) {
        for(final Integer i : list) {
            //System.out.println(i++);//(1)防止迭代时 修改
            System.out.println(i);
        }
    }
    
    public void testFinal2(final List<Integer> list) {
        //list = new ArrayList<>();(2)//防止参数重新应用
    }
}
