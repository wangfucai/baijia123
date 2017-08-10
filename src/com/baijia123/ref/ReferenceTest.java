package com.baijia123.ref;

import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class ReferenceTest {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        ReferenceQueue q = new ReferenceQueue();

        // ��ȡ���ݲ�����
        Object obj = new Object();
        SoftReference sr = new SoftReference(obj, q);

        // �´�ʹ��ʱ
        obj = (Object) sr.get();
        System.out.println("get obj = " + obj);
        if (obj == null) {
            // �������ñ����պ�����»�ȡ
            obj = new Object();
            System.out.println("new obj = " + obj);
        }

        // �����ջغ�ʣ�����������ö���
        SoftReference ref = null;
        while ((ref = (SoftReference)q.poll()) != null) {
            // ������
            System.out.println("������");
        }
        
        ReferenceTest.test();
    }

    
    public static void test() throws Exception{
        Object o = new Object();
        // Ĭ�ϵĹ��캯������ʹ��ReferenceQueue.NULL ��Ϊqueue
        //WeakReference<Object> wr = new WeakReference<Object>(o);
        SoftReference<Object> wr = new SoftReference<Object>(o);
        System.out.println(wr.get() == null);
        o = null;
        System.gc();
        System.out.println(wr.get() == null);
    }
}
