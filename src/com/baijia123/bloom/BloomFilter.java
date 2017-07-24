package com.baijia123.bloom;

import java.util.BitSet;

public class BloomFilter {
    private static final int DEFAULT_SIZE = 2 << 24;// ��¡�������ı��س���
    private static final int[] seeds = { 3, 5, 7, 11, 13, 31, 37, 61 };// ����Ҫѡȡ�������ܺܺõĽ��ʹ�����
    private static BitSet bits = new BitSet(DEFAULT_SIZE);
    private static SimpleHash[] func = new SimpleHash[seeds.length];

    public static void addValue(String value) {

        for (SimpleHash f : func) {
            bits.set(f.hash(value), true);
        }
    }

    public static void add(String value) {
        if (value != null)
            addValue(value);
    }

    public static boolean contains(String value) {
        if (value == null)
            return false;
        boolean ret = true;
        for (SimpleHash f : func)
            // ������ʵû��Ҫȫ�����ֻ꣬Ҫһ��ret==false��ô�Ͳ���������ַ���
            ret = ret && bits.get(f.hash(value));
        return ret;
    }
    
    public static void main(String[] args) {  
        String value = "xkeyideal@gmail.com";  
        for (int i = 0; i < seeds.length; i++) {  
            func[i] = new SimpleHash(DEFAULT_SIZE, seeds[i]);  
        }  
        add(value);  
        System.out.println(contains(value));  
    }  
}
