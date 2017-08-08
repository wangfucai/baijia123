package com.baijia123.unsafe;

import java.io.Serializable;
import java.lang.reflect.Field;

import sun.misc.Unsafe;

/**
 * 在这个类中，应该能够重建和设置final字段，但如果你不得不调用构造函数时，它就可能做一些和反序列化无关的事情。
 * 比如在构造函数中打印文本 
 * 有了这些原因，很多库使用Unsafe创建实例而不是调用构造函数。
 * 
 * Unsafe的另外一个用途是线程安全的获取非堆内存。
 * ByteBuffer函数也能使你安全的获取非堆内存或是DirectMemory，
 * 但它不会提供任何线程安全的操作。你在进程间共享数据时使用Unsafe尤其有用
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
        //调用allocateInstance函数避免了在我们不需要构造函数的时候却调用它。
        A a = (A) unsafe.allocateInstance(clazz);
        
        Field[] fields = clazz.getDeclaredFields();
        System.out.println("fieldName:fieldOffset");
        for (Field f : fields) {
            // 获取属性偏移量，可以通过这个偏移量给属性设置
            System.out.println(f.getName() + ":" + unsafe.objectFieldOffset(f));
        }
        long numOffset = unsafe.objectFieldOffset(clazz.getDeclaredField("num"));
        System.out.println("num = " + numOffset);
        unsafe.compareAndSwapInt(a, numOffset, 0, 5);
        System.out.println(a.getNum());
        
    }
}
