package com.baijia123.unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

import sun.misc.Unsafe;

/**
 * ��ȡ�ֽڶ����зǾ�̬������ƫ���� public native long objectFieldOffset(java.lang.reflect.Field field); ��ȡ�����е�һ��Ԫ�ص�ƫ���� public native int
 * arrayBaseOffset(java.lang.Class aClass); ��ȡ������һ��Ԫ�صĴ�С public native int arrayIndexScale(java.lang.Class aClass); ��ȡJVM�еĵ�ֵַ public native int
 * addressSize();
 * 
 * @author WangFuCai
 *
 */
public class UnsafeTest {

    private static int byteArrayBaseOffset;

    public static void main(String[] args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
        // TODO Auto-generated method stub
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe UNSAFE = (Unsafe) theUnsafe.get(null);
        System.out.println(UNSAFE);

        byte[] data = new byte[22];
        System.out.println(Arrays.toString(data));
        byteArrayBaseOffset = UNSAFE.arrayBaseOffset(byte[].class);
        System.out.println(byteArrayBaseOffset);
        UNSAFE.putByte(data, byteArrayBaseOffset, (byte) 1);
        UNSAFE.putByte(data, byteArrayBaseOffset + 5, (byte) 5);
        System.out.println(Arrays.toString(data));

        Class clazz = Target.class;
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("fieldName:fieldOffset");
        for (Field f : fields) {
            // ��ȡ����ƫ����������ͨ�����ƫ��������������
            System.out.println(f.getName() + ":" + UNSAFE.objectFieldOffset(f));
        }
        Target target = new Target();
        Field intFiled = clazz.getDeclaredField("intParam");
        int a = (Integer) intFiled.get(target);
        System.out.println("ԭʼֵ��:" + a);
        // intParam���ֶ�ƫ����16 ԭʼֵ��3 ����Ҫ��Ϊ10
        System.out.println(UNSAFE.compareAndSwapInt(target, 16, 3, 10));
        int b = (Integer) intFiled.get(target);
        System.out.println("�ı�֮���ֵ��:" + b);

        // ���ʱ���Ѿ���Ϊ10��,���Ի᷵��false
        System.out.println(UNSAFE.compareAndSwapInt(target, 16, 3, 10));

        System.out.println(UNSAFE.compareAndSwapObject(target, 24, null, "5"));
        Field strParam2 = clazz.getDeclaredField("strParam2");
        System.out.println("�ı�֮���strParam2ֵ��:" + strParam2.get(target));
    }

}
