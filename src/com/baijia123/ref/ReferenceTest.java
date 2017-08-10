package com.baijia123.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        ReferenceQueue q = new ReferenceQueue();

        // 获取数据并缓存
        Object obj = new Object();
        SoftReference sr = new SoftReference(obj, q);

        // 下次使用时
        obj = (Object) sr.get();
        System.out.println("get obj = " + obj);
        if (obj == null) {
            // 当软引用被回收后才重新获取
            obj = new Object();
            System.out.println("new obj = " + obj);
        }

        // 清理被收回后剩下来的软引用对象
        SoftReference ref = null;
        while ((ref = (SoftReference)q.poll()) != null) {
            // 清理工作
            System.out.println("清理工作");
        }
        
        ReferenceTest.test();
    }

    
    public static void test() throws Exception{
        Object o = new Object();
        // 默认的构造函数，会使用ReferenceQueue.NULL 作为queue
        //WeakReference<Object> wr = new WeakReference<Object>(o);
        SoftReference<Object> wr = new SoftReference<Object>(o);
        System.out.println(wr.get() == null);
        o = null;
        System.gc();
        System.out.println(wr.get() == null);
    }
}
