package com.baijia123.bit;

/**
 * >>表示右移，如果该数为正，则高位补0，若为负数，则高位补1；
 * >>>表示无符号右移，也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0。
 * 左移没有<<<运算符！
 * @author WangFuCai
 *
 */
public class BitTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int res = 20 >> 2;
        System.out.println("20向右移动两位，结果是" + res);
        
        res = -20 >> 2;
        System.out.println("-20向右移动两位，结果是" + res);
        
        // 十进制转换为二进制,-20的二进制为其正数的补码加1
        System.out.println(Integer.toBinaryString(-20));
        
        res = 20 >>> 2;
        System.out.println("20无符号右移向右移动两位，结果是" + res);
        
        /**
         * Integer.toBinaryString(int i) 转二进制
         * Integer.toHexString(int i)转十六进制
         * Integer.toOctalString(int i)转八进制
         */
        System.out.println(Integer.toHexString(0xec));
        //20的8位二进制表示是0x00010100,-20是正数的反码加1，也就是补码，等于0x11101011+1=0x11101100=0xec;
        res = 0xec >>> 2;
        System.out.println(Integer.toHexString(res));
        // >>>表示无符号右移,也叫逻辑右移，即若该数为正，则高位补0，而若该数为负数，则右移后高位同样补0。
        System.out.println("-20无符号右移向右移动两位，结果是" + res);
        
        final int DONE_MASK   = 0xf0000000;  // mask out non-completion bits
        final int NORMAL      = 0xf0000000;  // must be negative
        final int CANCELLED   = 0xc0000000;  // must be < NORMAL
        final int EXCEPTIONAL = 0x80000000;  // must be < CANCELLED
        final int SIGNAL      = 0x00010000;  // must be >= 1 << 16
        final int SMASK       = 0x0000ffff;  // short bits for tags
        System.out.println("0xf0000000 = " + DONE_MASK);
        System.out.println("0xc0000000 = " + EXCEPTIONAL);
        System.out.println("0x0000ffff = " + SMASK);
        
        final int SHORTTEST   = 0x0001ffff;
        System.out.println("0x0001ffff = " + SHORTTEST);
        System.out.println("short 0x0000ffff = " + (short)SHORTTEST);
        
        int registrations = 1;
        int  PARTIES_SHIFT   = 16;
        long adjust = ((long)registrations << PARTIES_SHIFT) | registrations;
        System.out.println("adjust = " + adjust);
    }

}
