package com.baijia123.bit;

/**
 * >>��ʾ���ƣ��������Ϊ�������λ��0����Ϊ���������λ��1��
 * >>>��ʾ�޷������ƣ�Ҳ���߼����ƣ���������Ϊ�������λ��0����������Ϊ�����������ƺ��λͬ����0��
 * ����û��<<<�������
 * @author WangFuCai
 *
 */
public class BitTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int res = 20 >> 2;
        System.out.println("20�����ƶ���λ�������" + res);
        
        res = -20 >> 2;
        System.out.println("-20�����ƶ���λ�������" + res);
        
        // ʮ����ת��Ϊ������,-20�Ķ�����Ϊ�������Ĳ����1
        System.out.println(Integer.toBinaryString(-20));
        
        res = 20 >>> 2;
        System.out.println("20�޷������������ƶ���λ�������" + res);
        
        /**
         * Integer.toBinaryString(int i) ת������
         * Integer.toHexString(int i)תʮ������
         * Integer.toOctalString(int i)ת�˽���
         */
        System.out.println(Integer.toHexString(0xec));
        //20��8λ�����Ʊ�ʾ��0x00010100,-20�������ķ����1��Ҳ���ǲ��룬����0x11101011+1=0x11101100=0xec;
        res = 0xec >>> 2;
        System.out.println(Integer.toHexString(res));
        // >>>��ʾ�޷�������,Ҳ���߼����ƣ���������Ϊ�������λ��0����������Ϊ�����������ƺ��λͬ����0��
        System.out.println("-20�޷������������ƶ���λ�������" + res);
        
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
