package com.baijia123.unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * ��������У�Ӧ���ܹ��ؽ�������final�ֶΣ�������㲻�ò����ù��캯��ʱ�����Ϳ�����һЩ�ͷ����л��޹ص����顣
 * �����ڹ��캯���д�ӡ�ı� 
 * ������Щԭ�򣬺ܶ��ʹ��Unsafe����ʵ�������ǵ��ù��캯����
 * 
 * Unsafe������һ����;���̰߳�ȫ�Ļ�ȡ�Ƕ��ڴ档
 * ByteBuffer����Ҳ��ʹ�㰲ȫ�Ļ�ȡ�Ƕ��ڴ����DirectMemory��
 * ���������ṩ�κ��̰߳�ȫ�Ĳ��������ڽ��̼乲������ʱʹ��Unsafe��������
 * 
 * @author WangFuCai
 *
 */
public class A implements Serializable {
    private final int num;

    public A(int num) {
        System.out.println("Hello Mum");
        this.num = num;
    }

    public int getNum() {
        return num;
    }

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, InstantiationException {
        //A a = new A(3);
        //System.out.println(a.getNum());
        
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe) theUnsafe.get(null);
        Class clazz = A.class;
        //����allocateInstance���������������ǲ���Ҫ���캯����ʱ��ȴ��������
        A a = (A) unsafe.allocateInstance(clazz);
        
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("fieldName:fieldOffset");
        for (Field f : fields) {
            // ��ȡ����ƫ����������ͨ�����ƫ��������������
            System.out.println(f.getName() + ":" + unsafe.objectFieldOffset(f));
        }
        long numOffset = unsafe.objectFieldOffset(clazz.getDeclaredField("num"));
        System.out.println("num = " + numOffset);
        unsafe.compareAndSwapInt(a, numOffset, 0, 5);
        System.out.println(a.getNum());
        
    }
}
