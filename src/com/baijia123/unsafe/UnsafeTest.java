package com.baijia123.unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

import sun.misc.Unsafe;

/**
 * 获取字节对象中非静态方法的偏移量 public native long objectFieldOffset(java.lang.reflect.Field field); 获取数组中第一个元素的偏移量 public native int
 * arrayBaseOffset(java.lang.Class aClass); 获取数组中一个元素的大小 public native int arrayIndexScale(java.lang.Class aClass); 获取JVM中的地址值 public native int
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
            // 获取属性偏移量，可以通过这个偏移量给属性设置
            System.out.println(f.getName() + ":" + UNSAFE.objectFieldOffset(f));
        }
        Target target = new Target();
        Field intFiled = clazz.getDeclaredField("intParam");
        int a = (Integer) intFiled.get(target);
        System.out.println("原始值是:" + a);
        // intParam的字段偏移是16 原始值是3 我们要改为10
        System.out.println(UNSAFE.compareAndSwapInt(target, 16, 3, 10));
        int b = (Integer) intFiled.get(target);
        System.out.println("改变之后的值是:" + b);

        // 这个时候已经改为10了,所以会返回false
        System.out.println(UNSAFE.compareAndSwapInt(target, 16, 3, 10));

        System.out.println(UNSAFE.compareAndSwapObject(target, 24, null, "5"));
        Field strParam2 = clazz.getDeclaredField("strParam2");
        System.out.println("改变之后的strParam2值是:" + strParam2.get(target));
    }

}
